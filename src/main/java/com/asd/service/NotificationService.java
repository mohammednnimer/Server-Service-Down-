package com.asd.service;

import com.asd.dto.EmailNotification;
import com.asd.dto.ReciveAlert;
import com.asd.dto.sub.HarddiskUsage;
import com.asd.logic.servicedown.loaders.Loaders;
import com.asd.logic.servicedown.models.EmailConfigModel;
import com.asd.repository.EmailRepo;
import com.db.entitie.EmailParticipants;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

@ApplicationScoped
public class NotificationService {

    @Inject
    EmailRepo emailRepo;

    @Inject
    Loaders loaders;

    private void sendEmail(EmailNotification notification) {
        final EmailConfigModel emailConfig = loaders.loadEmailConfig();

        if (emailConfig == null) {
            System.err.println("Email configuration not loaded");
            return;
        }

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", emailConfig.getHost());
        props.put("mail.smtp.port", emailConfig.getPort());

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailConfig.getUsername(), emailConfig.getPassword());
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailConfig.getFrom()));

            for (EmailParticipants recipient : emailRepo.getAllParticipants()) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient.getEmail().trim()));
            }

            message.setSubject("System Alert Notification from " + notification.getClientIp());

            StringBuilder emailBody = new StringBuilder();
            emailBody.append("<html><body>")
                    .append("<h2>Alert Notification</h2>")
                    .append("<p><b>Client IP:</b> ").append(notification.getClientIp()).append("</p>")
                    .append("<p><b>CPU Utilization:</b> ")
                    .append(String.format("%.2f", notification.getCpuUtilization())).append("%</p>")
                    .append("<p><b>RAM Utilization:</b> ")
                    .append(String.format("%.2f", notification.getRamUtilization())).append("%</p>");

            for (HarddiskUsage disk : notification.getHarddiskUtilization()) {
                emailBody.append("<p><b>Hard Disk (").append(disk.getName())
                        .append(") Utilization:</b>     ")
                        .append(String.format("%.2f", disk.getHarddiskUtilization())).append("%</p>");
            }

            emailBody.append("<pre>").append(notification.getMessage()).append("</pre>")
                    .append("</body></html>");

            message.setContent(emailBody.toString(), "text/html; charset=utf-8");
            Transport.send(message);

            System.out.println("Alert email sent for client IP: " + notification.getClientIp());

            System.out.println(emailBody);


        } catch (MessagingException e) {
            System.err.println("Failed to send alert email: " + e.getMessage());
        }


    }

    public void SendEmail(String ip, ReciveAlert reciveAlert) {
        EmailNotification emailNotification = new EmailNotification();

        double cpuUtil = reciveAlert.getClientUtilization().getCpuUtilzation().getUtilization();
        double ramUtil = reciveAlert.getClientUtilization().getRamUtilzation().getUtilization();

        List<HarddiskUsage> harddisk = new ArrayList<>();

        reciveAlert.getClientUtilization().getHarddiskUtilization().getPartitions().forEach(partition -> {
            if (partition.getUtilization() > 85) {
                harddisk.add(new HarddiskUsage(partition.getPath(), partition.getUtilization()));
            }
        });

        boolean alertNeeded = false;
        StringBuilder alertMsg = new StringBuilder("Alert triggered for client IP: ").append(ip).append("\n");

        if (cpuUtil > 150) {
            alertNeeded = true;
            alertMsg.append("CPU usage is high: ")
                    .append(String.format("%.2f", cpuUtil)).append("%\n");
        }

        if (ramUtil > 90) {
            alertNeeded = true;
            alertMsg.append("RAM usage is high: ")
                    .append(String.format("%.2f", ramUtil)).append("%\n");
        }

        if (!harddisk.isEmpty()) {
            alertNeeded = true;
            for (HarddiskUsage disk : harddisk) {
                alertMsg.append("Hard disk ( ").append(disk.getName())
                        .append(" ) usage is high: ")
                        .append(String.format("%.2f", disk.getHarddiskUtilization())).append("%\n");
            }
        }
        if (alertNeeded) {
            emailNotification.setClientIp(ip);
            emailNotification.setMessage(alertMsg.toString());
            emailNotification.setHarddiskUtilization(harddisk);
            emailNotification.setCpuUtilization(cpuUtil);
            emailNotification.setRamUtilization(ramUtil);

            sendEmail(emailNotification);
        }
    }
}

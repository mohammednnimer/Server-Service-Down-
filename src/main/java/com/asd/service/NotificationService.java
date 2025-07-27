package com.asd.service;

import com.asd.dto.EmailNotification;
import com.asd.dto.ReciveAlert;
import com.asd.logic.servicedown.loaders.Loaders;
import com.asd.logic.servicedown.models.EmailConfigModel;
import com.asd.repository.EmailRepo;
import com.db.entitie.EmailParticipants;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Iterator;
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

            Iterator<EmailParticipants> iterator = emailRepo.getAllParticipants().iterator();
            while (iterator.hasNext()) {
                EmailParticipants recipient = iterator.next();
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient.getEmail().trim()));
            }

            message.setSubject("System Alert Notification from " + notification.getClientIp());

            String emailBody = "<html><body>" +
                    "<h2>Alert Notification</h2>" +
                    "<p><b>Client IP:</b> " + notification.getClientIp() + "</p>" +
                    "<p><b>CPU Utilization:</b> " + notification.getCpuUtilization() + "%</p>" +
                    "<p><b>RAM Utilization:</b> " + notification.getRamUtilization() + "%</p>" +
                    "<p><b>Hard Disk Utilization:</b> " + notification.getHarddiskUtilization() + "%</p>" +
                    "<pre>" + notification.getMessage() + "</pre>" +
                    "</body></html>";

            message.setContent(emailBody, "text/html; charset=utf-8");

            Transport.send(message);
            System.out.println("Alert email sent for client IP: " + notification.getClientIp());

        } catch (MessagingException e) {
            System.err.println("Failed to send alert email: " + e.getMessage());
        }
    }


    public void SendEmail(String ip, ReciveAlert reciveAlert) {

        EmailNotification emailNotification = new EmailNotification();
       double cpuUtil = reciveAlert.getClientUtilization().getCpuUtilzation().getUtilization();
        double ramUtil = reciveAlert.getClientUtilization().getRamUtilzation().getUtilization();

        double harddisk=0;
        for(int i=0;i<reciveAlert.getClientUtilization().getHarddiskUtilization().getPartitions().size();i++)
        {
            if(reciveAlert.getClientUtilization().getHarddiskUtilization().getPartitions().get(i).getUtilization()>85)
            {

                harddisk=reciveAlert.getClientUtilization().getHarddiskUtilization().getPartitions().get(i).getUtilization();
            }


        }
        boolean alertNeeded = false;
        StringBuilder alertMsg = new StringBuilder("Alert triggered for client IP: " + ip + "\n");

        if (cpuUtil > 150) {
            alertNeeded = true;
            alertMsg.append("CPU usage is high: ").append(cpuUtil).append("%\n");

        }
        if (ramUtil > 90) {
            alertNeeded = true;
            alertMsg.append("RAM usage is high: ").append(ramUtil).append("%\n");
        }
        if (harddisk>85)
        {alertNeeded = true;
            alertMsg.append("harddisk usage is high: ").append(harddisk).append("%\n");
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

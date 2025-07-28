package com.asd.logic.servicedown.services;

import com.asd.logic.servicedown.loaders.Loaders;
import com.asd.logic.servicedown.models.*;
import com.asd.logic.servicedown.utils.HttpClientUtil;
import com.asd.repository.EmailRepo;
import com.asd.repository.WhatsappRepo;
import com.db.entitie.EmailParticipants;
import com.db.entitie.WhatsappParticipants;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;


import java.util.Properties;

import static com.asd.logic.servicedown.services.CertificateMessageGeneratorSafe.generateCertificateMessage;

@ApplicationScoped
public class NotificationServices {

    @Inject
    Loaders loaders;

    @Inject
    EmailRepo emailRepo;

    @Inject
    WhatsappRepo whatsappRepo;

    @Inject
    WhatsAppConfigModel whatsAppConfigModel;

    public boolean sendWhatsAppMsg(ServiceModel serviceModel) {

        try {
            for (WhatsappParticipants participants : whatsappRepo.getAllParticipants()){
                ReceiverModel receiverModel = new ReceiverModel();
                receiverModel.setMsg(getMsg(serviceModel));
                receiverModel.setMobNum(participants.getPhoneNumber());
                TypeReference<String> stringTypeReference = new TypeReference<String>() {
                };

                HttpClientUtil.sendPostRequest(whatsAppConfigModel.getUrl() , receiverModel , stringTypeReference) ;

                System.out.println("Message Sent to this Mobile Sucesfuly  " + receiverModel.toString());
            }
            return true ;

        }
        catch (Exception e ) {
            e.printStackTrace();
            return false ;

        }


    }
    public boolean sendWhatsAppMsg(CertificateStatusModel certificateStatusModel) {

        try {
            for (WhatsappParticipants participants : whatsappRepo.getAllParticipants()){
                ReceiverModel receiverModel = new ReceiverModel();
                receiverModel.setMsg(generateCertificateMessage(certificateStatusModel));
                receiverModel.setMobNum(participants.getPhoneNumber());
                TypeReference<String> stringTypeReference = new TypeReference<String>() {
                };

                HttpClientUtil.sendPostRequest(whatsAppConfigModel.getUrl() , receiverModel , stringTypeReference) ;

                System.out.println("Message Sent to this Mobile Sucesfuly  " + receiverModel.toString());
            }
            return true ;

        }
        catch (Exception e ) {
            e.printStackTrace();
            return false ;

        }


    }
    public void sendEmail(CertificateStatusModel certificateStatus) {
        EmailConfigModel emailConfig = loaders.loadEmailConfig();
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
                return new PasswordAuthentication(
                        emailConfig.getUsername(),
                        emailConfig.getPassword()
                );
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailConfig.getFrom()));

            // Add all recipients
            for (EmailParticipants recipient : emailRepo.getAllParticipants()) {
                message.addRecipient(
                        Message.RecipientType.TO,
                        new InternetAddress(recipient.getEmail().trim())
                );
            }

            message.setSubject("Certificate Expiration Alert: " +
                    certificateStatus.getServiceURL());
            String emailBody = """
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Certificate Expiration Alert</title>
<style>
  body {
    font-family: Arial, sans-serif;
    background-color: #f9f9f9;
    color: #333333;
    padding: 20px;
  }
  .container {
    background-color: #ffffff;
    border: 1px solid #ddd;
    border-radius: 8px;
    padding: 20px;
    max-width: 600px;
    margin: auto;
  }
  h1 {
    color: #cc0000;
  }
  table.info-table {
    width: 100%%;  /* Escaped percent sign */
    border-collapse: collapse;
    margin-top: 15px;
  }
  table.info-table th, table.info-table td {
    border: 1px solid #ddd;
    padding: 10px;
    text-align: left;
  }
  table.info-table th {
    background-color: #f2f2f2;
  }
  .status-expired {
    color: #cc0000;
    font-weight: bold;
  }
  .footer {
    margin-top: 25px;
    font-size: 12px;
    color: #999999;
    text-align: center;
  }
  .icon {
    vertical-align: middle;
    margin-right: 8px;
  }
</style>
</head>
<body>
  <div class="container">
    <h1><img class="icon" src="https://img.icons8.com/emoji/48/000000/warning-emoji.png" alt="Warning Icon"/> ALARM: CERTIFICATE EXPIRED!</h1>
    <p>The following certificate has expired or has an issue:</p>
    <table class="info-table">
      <tr>
        <th>Service URL</th>
        <td>%s</td>
      </tr>
      <tr>
        <th>Expiration Date</th>
        <td>%s</td>
      </tr>
      <tr>
        <th>Status</th>
        <td class="status-expired">%s</td>
      </tr>
      <tr>
        <th>Message</th>
        <td>%s</td>
      </tr>
    </table>
    <div class="footer">
      This is an automated message. Please do not reply.
    </div>
  </div>
</body>
</html>
""".formatted(
                    certificateStatus.getServiceURL(),
                    certificateStatus.getExpirationDate(),
                    certificateStatus.getStatus(),
                    certificateStatus.getMessage() == null ? "" : certificateStatus.getMessage()
            );


            message.setText(emailBody);
            message.setContent(emailBody, "text/html; charset=utf-8");

            Transport.send(message);
            System.out.println("Expiration email sent for: " +
                    certificateStatus.getServiceURL());
        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }

    private String getMsg(ServiceModel serviceModel) {
        return "Alert !! : --> This Server : " + serviceModel.getServiceUrl()  + "   --> IS DOWN !! " ;

    }

}

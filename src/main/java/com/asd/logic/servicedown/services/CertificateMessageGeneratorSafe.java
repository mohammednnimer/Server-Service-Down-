package com.asd.logic.servicedown.services;

import com.asd.logic.servicedown.models.CertificateStatusModel;

public class CertificateMessageGeneratorSafe {

    public static String generateCertificateMessage(CertificateStatusModel statusModel) {
        StringBuilder message = new StringBuilder();

        message.append("[SERVICE] ").append(statusModel.getServiceName()).append(" ");
        message.append("[EXPIRATION DATE] ").append(statusModel.getExpirationDate()).append(" ");

        switch (statusModel.getStatus()) {
            case "EXPIRED":
                message.append("[STATUS] EXPIRED ");
                message.append("[WARNING] The certificate expired ")
                        .append(Math.abs(statusModel.getDaysUntilExpiry()))
                        .append(" day(s) ago. ");
                break;

            case "EXPIRING_SOON":
                message.append("[STATUS] EXPIRING SOON ");
                message.append("[NOTICE] Only ")
                        .append(statusModel.getDaysUntilExpiry())
                        .append(" day(s) remaining until expiration. ");
                break;

            case "VALID":
                message.append("[STATUS] VALID ");
                message.append("[INFO] ")
                        .append(statusModel.getDaysUntilExpiry())
                        .append(" day(s) remaining until expiration. ");
                break;

            case "ERROR":
                message.append("[STATUS] ERROR ");
                message.append("[DETAILS] ").append(statusModel.getMessage()).append(" ");
                break;

            default:
                message.append("[STATUS] UNKNOWN ");
                break;
        }

        message.append("[ACTION] Please take appropriate action to renew the certificate if needed.");

        return message.toString();
    }
}

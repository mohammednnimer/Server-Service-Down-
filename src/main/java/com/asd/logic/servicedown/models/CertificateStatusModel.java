package com.asd.logic.servicedown.models;


import com.asd.enums.CertificateStatus;

import java.util.Date;

public class CertificateStatusModel {

    private String serviceName;
    private CertificateStatus status; // VALID, EXPIRING_SOON, EXPIRED, ERROR
    private long daysUntilExpiry;
    private Date expirationDate;
    private String message;

    public String getServiceURL() {
        return serviceURL;
    }

    public void setServiceURL(String serviceURL) {
        this.serviceURL = serviceURL;
    }

    private String serviceURL ;
    

    // Getters and setters
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public CertificateStatus getStatus() {
        return status;
    }
    public void setStatus(CertificateStatus status) {
        this.status = status;
    }
    public long getDaysUntilExpiry() {
        return daysUntilExpiry;
    }
    public void setDaysUntilExpiry(long daysUntilExpiry) {
        this.daysUntilExpiry = daysUntilExpiry;
    }
    public Date getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}

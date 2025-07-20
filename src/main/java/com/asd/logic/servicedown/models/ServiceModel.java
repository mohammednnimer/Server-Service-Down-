package com.asd.logic.servicedown.models;


import com.asd.enums.CertificateStatus;
import com.asd.enums.ServiceStatus;

import java.time.LocalDateTime;

public class ServiceModel {

    private String serviceUrl ;
    private boolean isBlocked = false;
    private boolean isHttps;
    private LocalDateTime lastShutdown;
    private CertificateStatus certificateStatus;
    private ServiceStatus serviceStatus;

    public boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public boolean getHttps() {
        return isHttps;
    }

    public void setHttps(boolean https) {
        isHttps = https;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        if (serviceUrl.contains("https")){
            isHttps = true;
        }

        this.serviceUrl = serviceUrl;
    }



    @Override
    public String toString() {
        return "ServiceModel{" +
                "serviceUrl='" + serviceUrl + '\'' +
                '}';
    }

    public LocalDateTime getLastShutdown() {
        return lastShutdown;
    }

    public void setLastShutdown(LocalDateTime lastShutdown) {
        this.lastShutdown = lastShutdown;
    }

    public CertificateStatus getCertificateStatus() {
        return certificateStatus;
    }

    public void setCertificateStatus(CertificateStatus certificateStatus) {
        this.certificateStatus = certificateStatus;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}

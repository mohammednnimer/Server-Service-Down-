package com.asd.dto;

import jakarta.persistence.Column;

public class UpdateService {
        private String id;

        private String serviceDns;

        private String serviceName;
        private Integer servicePort;
        private String updatedBy;
        private boolean isBlocked;

    public String getWorkDirPath() {
        return workDirPath;
    }

    public void setWorkDirPath(String workDirPath) {
        this.workDirPath = workDirPath;
    }

    private String workDirPath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceDns() {
        return serviceDns;
    }

    public void setServiceDns(String serviceDns) {
        this.serviceDns = serviceDns;
    }



    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getServicePort() {
        return servicePort;
    }

    public void setServicePort(Integer servicePort) {
        this.servicePort = servicePort;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
}

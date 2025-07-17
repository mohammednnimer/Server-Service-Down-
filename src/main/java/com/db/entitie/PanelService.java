
package com.db.entitie;

import jakarta.persistence.*;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name = "panel_services_tbl")
public class PanelService {

    @Id
    @Column(name = "ID")
    private String id;


    @Column(name = "ServerID", nullable = false)
    private String server;

    @Column(name = "service_name", nullable = false)
    private String serviceName;

    @Column(name = "service_port", nullable = false)
    private Integer servicePort;

    @Column(name = "service_dns", nullable = false)
    private String serviceDns;

    @Column(name = "work_dir_path")
    private String workDirPath;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "deleted" )
    private boolean isdeleted=false;


    public boolean isIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(boolean isdeleted) {
        this.isdeleted = isdeleted;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
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

    public String getServiceDns() {
        return serviceDns;
    }

    public void setServiceDns(String serviceDns) {
        this.serviceDns = serviceDns;
    }

    public String getWorkDirPath() {
        return workDirPath;
    }

    public void setWorkDirPath(String workDirPath) {
        this.workDirPath = workDirPath;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}

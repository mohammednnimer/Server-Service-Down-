package com.asd.dto;



import com.asd.dto.sub.Ports;
import jakarta.persistence.*;

import java.util.List;

public class AgentDto {

   private String token;


    private String manager_ip;

    public String getManager_ip() {
        return manager_ip;
    }

    public void setManager_ip(String manager_ip) {
        this.manager_ip = manager_ip;
    }

     private List<String> keywords;

     private List<Ports> ports;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<Ports> getPorts() {
        return ports;
    }

    public void setPorts(List<Ports> ports) {
        this.ports = ports;
    }

}

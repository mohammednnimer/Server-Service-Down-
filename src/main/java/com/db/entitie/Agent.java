package com.db.entitie;

import com.asd.dto.sub.Ports;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Agent")
public class Agent {

    @Id
    @Column(length = 1024, unique = true)
    private String token;


    @Column(name = "ManagerIp")
    private String manager_ip;

    public String getManager_ip() {
        return manager_ip;
    }


    public void setManager_ip(String manager_ip) {
        this.manager_ip = manager_ip;
    }


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "agent_keywords", joinColumns = @JoinColumn(name = "agent_token"))
    @Column(name = "keyword")
    private List<String> keywords;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "agent_ports", joinColumns = @JoinColumn(name = "agent_token"))
    private List<Ports> ports;

    @Column(name = "server_id")
    private String server;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

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

// com.asd.models.EmailConfigModel.java
package com.asd.logic.servicedown.models;

import java.util.List;

public class EmailConfigModel {
    private String host;
    private int port;
    private String username;
    private String password;
    private String from;
    private List<String> to;

    // Getters and Setters
    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }

    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public List<String> getTo() { return to; }
    public void setTo(List<String> to) { this.to = to; }
}
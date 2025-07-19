package com.asd.dto.sub;

public class EmailSettings {

    private String host;
    private String port;
    private String username;
    private String password;
    private String from;

    public EmailSettings(String host, String port, String username, String password, String from) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.from = from;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFrom() {
        return from;
    }
}

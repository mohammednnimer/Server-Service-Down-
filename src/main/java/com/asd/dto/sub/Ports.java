package com.asd.dto.sub;

import jakarta.persistence.Embeddable;

@Embeddable
public class Ports {
    private int fromPort;
    private int toPort;

    public Ports() {}

    public Ports(int fromPort, int toPort) {
        this.fromPort = fromPort;
        this.toPort = toPort;
    }

    public int getFromPort() {
        return fromPort;
    }

    public void setFromPort(int fromPort) {
        this.fromPort = fromPort;
    }

    public int getToPort() {
        return toPort;
    }

    public void setToPort(int toPort) {
        this.toPort = toPort;
    }
}

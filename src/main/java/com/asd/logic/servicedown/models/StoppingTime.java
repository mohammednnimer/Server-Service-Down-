package com.asd.logic.servicedown.models;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class StoppingTime {

    private LocalTime stopTimeFrom, stopTimeTo;
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public LocalTime getStopTimeFrom() {
        return stopTimeFrom;
    }

    public void setStopTimeFrom(String stopTimeFrom) {
        if (stopTimeFrom == null || stopTimeFrom.isEmpty()) return;

        this.stopTimeFrom = LocalTime.parse(stopTimeFrom, timeFormatter);
    }

    public LocalTime getStopTimeTo() {
        return stopTimeTo;
    }

    public void setStopTimeTo(String stopTimeTo) {
        if (stopTimeTo == null || stopTimeTo.isEmpty()) return;

        this.stopTimeTo = LocalTime.parse(stopTimeTo, timeFormatter);
    }
}

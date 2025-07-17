package com.asd.logic.servicedown.models;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StoppingTime {

    private LocalTime  stopTimeFrom , stopTimeTo ;
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");


    public LocalTime  getStopTimeFrom() {
        return stopTimeFrom;
    }

    public void setStopTimeFrom(String stopTimeFrom) {
        LocalTime fromTime = LocalTime.parse(stopTimeFrom, timeFormatter);

        this.stopTimeFrom = fromTime;
    }

    public LocalTime getStopTimeTo() {
        return stopTimeTo;
    }

    public void setStopTimeTo(String stopTimeTo) {
        LocalTime toTime = LocalTime.parse(stopTimeTo, timeFormatter);

        this.stopTimeTo = toTime;
    }
}

package com.asd.dto.sub;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class SystemSettings {

    private LocalTime stopTimeFrom;
    private LocalTime stopTimeTo;

    public SystemSettings(LocalTime stopTimeFrom, LocalTime stopTimeTo) {
        this.stopTimeFrom = stopTimeFrom;
        this.stopTimeTo = stopTimeTo;
    }

    public LocalTime getStopTimeFrom() {
        return stopTimeFrom;
    }

    public LocalTime getStopTimeTo() {
        return stopTimeTo;
    }

}

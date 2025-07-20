package com.asd.logic.servicedown.services;

import com.asd.logic.servicedown.loaders.Loaders;
import com.asd.logic.servicedown.models.StoppingTime;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public  class ServicesUtil {

    @Inject
    Loaders loaders;

    private StoppingTime stoppingTime;
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @PostConstruct
    void init() {
        stoppingTime = loaders.loadIntervalTime();
    }

public boolean withIntervalStopTime() {
    LocalTime currentTime = LocalTime.now();
    String currentTimeStr = currentTime.format(timeFormatter);
    LocalTime formattedCurrentTime = LocalTime.parse(currentTimeStr, timeFormatter);

    if (stoppingTime.getStopTimeFrom() == null || stoppingTime.getStopTimeTo() == null) return false;
    if (!formattedCurrentTime.isBefore(stoppingTime.getStopTimeFrom()) && !formattedCurrentTime.isAfter(stoppingTime.getStopTimeTo())) {
        System.out.println("The current time is within the interval.");
        return true ;
    } else {
        System.out.println("The current time is outside the interval.");
        return false;
    }
}

}

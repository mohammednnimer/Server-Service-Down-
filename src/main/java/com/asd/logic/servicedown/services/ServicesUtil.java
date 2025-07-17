package com.asd.logic.servicedown.services;

import com.asd.logic.servicedown.loaders.Loaders;
import com.asd.logic.servicedown.models.StoppingTime;

import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract  class ServicesUtil {
    private static StoppingTime stoppingTime;
    private  static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");


    static {
        try {
            stoppingTime = Loaders.loadIntervalTime();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

public  static boolean withIntervalStopTime() {

    LocalTime currentTime = LocalTime.now();
    String currentTimeStr = currentTime.format(timeFormatter);
    LocalTime formattedCurrentTime = LocalTime.parse(currentTimeStr, timeFormatter);


    if (!formattedCurrentTime.isBefore(stoppingTime.getStopTimeFrom()) && !formattedCurrentTime.isAfter(stoppingTime.getStopTimeTo())) {
        System.out.println("The current time is within the interval.");
        return true ;
    } else {
        System.out.println("The current time is outside the interval.");
        return false;
    }
}

}

package com.asd.logic.servicedown.controller;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CheckerBean {

    private ControllerManager controllerManager = new ControllerManager();

    public ControllerManager get() {
        return controllerManager;
    }

    @Scheduled(every ="10m")
    public void check() {
        controllerManager.mainFun();
    }
}

package com.asd.logic.servicedown.controller;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CheckerBean {

    @Inject
    ControllerManager controllerManager;

    public ControllerManager get() {
        return controllerManager;
    }

    @Scheduled(every ="10m")
    public void check() {
        controllerManager.mainFun();
    }
}

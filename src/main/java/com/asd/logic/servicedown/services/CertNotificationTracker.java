package com.asd.logic.servicedown.services;

import com.asd.repository.ServiceRepo;
import com.db.entitie.PanelService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.file.FileSystems;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@ApplicationScoped
public class CertNotificationTracker {

    private static final Duration COOLDOWN_DURATION = Duration.ofHours(8);
    @Inject
    ServiceRepo serviceRepo;

    public boolean shouldNotify(String serviceUrl) {
        LocalDateTime lastSent = serviceRepo.getDownServiceByURL(serviceUrl).getLastShutdown();
        if (lastSent == null) {
            return true; // first time
        }
        return Duration.between(lastSent, LocalDateTime.now()).compareTo(COOLDOWN_DURATION) > 0;
    }

    public void updateLastSent(String serviceUrl) {
        PanelService service = serviceRepo.getServiceByURL(serviceUrl);
        service.setLastShutdown(LocalDateTime.now());
    }
}

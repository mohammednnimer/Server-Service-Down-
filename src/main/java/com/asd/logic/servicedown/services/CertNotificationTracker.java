package com.asd.logic.servicedown.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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

public class CertNotificationTracker {

    private static final String CERT_LOG_FILE = "config"+ FileSystems.getDefault().getSeparator()+"cert_status_log.json";
    private static final Duration COOLDOWN_DURATION = Duration.ofHours(8);
    private static final Map<String, LocalDateTime> lastSentMap = new HashMap<>();

    static {
        load();
    }

    private static void load() {
        File file = new File(CERT_LOG_FILE);
        if (!file.exists()) {
            // First time use: create an empty file
            save();
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            StringBuilder json = new StringBuilder();
            while (scanner.hasNextLine()) {
                json.append(scanner.nextLine());
            }
            if (!json.toString().isEmpty()) {
                Gson gson = new Gson();
                Type type = new TypeToken<Map<String, String>>() {}.getType();
                Map<String, String> map = gson.fromJson(json.toString(), type);
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    lastSentMap.put(entry.getKey(), LocalDateTime.parse(entry.getValue()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean shouldNotify(String serviceUrl) {
        LocalDateTime lastSent = lastSentMap.get(serviceUrl);
        if (lastSent == null) {
            return true; // first time
        }
        return Duration.between(lastSent, LocalDateTime.now()).compareTo(COOLDOWN_DURATION) > 0;
    }

    public static void updateLastSent(String serviceUrl) {
        lastSentMap.put(serviceUrl, LocalDateTime.now());
        save();
    }

    private static void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CERT_LOG_FILE))) {
            Map<String, String> mapToSave = new HashMap<>();
            for (Map.Entry<String, LocalDateTime> entry : lastSentMap.entrySet()) {
                mapToSave.put(entry.getKey(), entry.getValue().toString());
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.println(gson.toJson(mapToSave));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.asd.logic.servicedown.services;

import com.asd.logic.servicedown.models.ServiceModel;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebsiteChecker {
    private static final  Integer MAX_RETRIES = 3 ;
    public boolean isWebsiteUp(ServiceModel serviceModel) {
        int attempt = 0;
        while (attempt < MAX_RETRIES) {
            HttpURLConnection connection = null;
            try {
                URL urlObj = new URL(serviceModel.getServiceUrl());
                connection = (HttpURLConnection) urlObj.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.connect();
                int responseCode = connection.getResponseCode();
                String r = connection.getResponseMessage();
                System.out.println("response : " + r);
                if (responseCode == 200) {
                    return true;
                } else {
                    System.err.println("Response code: " + responseCode);
                }
            } catch (IOException e) {
                System.err.println("Attempt " + (attempt + 1) + ": IOException: " + e.getMessage());
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }
            attempt++;
        }
        return false;
    }


//    public boolean isWebsiteUp(ServiceModel serviceModel) {
//        try (Socket socket = new Socket()) {
//            socket.connect(new InetSocketAddress(serviceModel.getServiceUrl(), serviceModel.getPortNum()), 5000); // timeout in milliseconds
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.err.println("IOException: " + e.getMessage());
//            return false;
//        }
//    }
    }


package com.asd.logic.servicedown.loaders;

import com.asd.logic.servicedown.models.*;
import com.asd.logic.servicedown.utils.ConstantValues;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public abstract class Loaders {
private static WhatsAppConfigModel whatsAppConfigModel =  null ;
    public  static WhatsAppConfigModel loadWhatsAppConfig() {
        if (whatsAppConfigModel == null) {
            whatsAppConfigModel = loadWhatsAppConfigUtil() ;
            return  whatsAppConfigModel ;
        }
        return  whatsAppConfigModel ;
    }

    private static WhatsAppConfigModel loadWhatsAppConfigUtil() {
        try {
            Scanner scanner = new Scanner(new File(ConstantValues.WHATS_APP_CONF_FILE)) ;
            List<String> mobilesPhone = new LinkedList<>() ;
            String scope_whats_app_service = "null" ;
            while (scanner.hasNext()){
                String line = scanner.nextLine() ;
                if (line.startsWith("whats.url=")){
                    scope_whats_app_service = line.replaceAll("whats.url=" , "");
                    scope_whats_app_service = scope_whats_app_service.trim().replaceAll(" " ,"");
                }else if (line.startsWith("mobile")){
                    mobilesPhone.add(line.trim().replaceAll("mobile\\d=" , "")) ;
                }
            }
            WhatsAppConfigModel whatsAppConfigModel = new WhatsAppConfigModel();
            whatsAppConfigModel.setUrl(scope_whats_app_service);
            whatsAppConfigModel.setMobiles(mobilesPhone);
            return whatsAppConfigModel ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null ;
    }

    public static List<ServiceModel> loadServices() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(ConstantValues.SERVICE_CONF_FILE)) ;
        List<ServiceModel> serviceModels = new LinkedList<>() ;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.startsWith("http://") || line.startsWith("https://") ){
                ServiceModel serviceModel = new ServiceModel() ;

               serviceModel.setServiceUrl(line.trim());
                serviceModels.add(serviceModel) ;
            }
            else {
                System.err.println("Your Line Must Start With Http protocol : "  + line);
            }

        }
        scanner.close();
        return serviceModels ;
    }

    public static StoppingTime loadIntervalTime() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(ConstantValues.SETTING_CONF_FILE));
        StoppingTime  stoppingTime = new StoppingTime();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.startsWith("stopTimeFrom=")){
                stoppingTime.setStopTimeFrom(line.trim().replaceAll("stopTimeFrom=" , ""));
            }else if (line.startsWith("stopTimeTo=")){
                stoppingTime.setStopTimeTo(line.trim().replaceAll("stopTimeTo=" , ""));

            }
        }
        scanner.close();
        return stoppingTime ;
    }


    private static EmailConfigModel emailConfigModel = null;

    public static EmailConfigModel loadEmailConfig() {
        if (emailConfigModel == null) {
            emailConfigModel = loadEmailConfigUtil();
        }
        return emailConfigModel;
    }

    private static EmailConfigModel loadEmailConfigUtil() {
        try {
            Scanner scanner = new Scanner(new File(ConstantValues.EMAIL_CONF_FILE));
            EmailConfigModel config = new EmailConfigModel();
            List<String> toList = new LinkedList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("host=")) {
                    config.setHost(line.substring(5));
                } else if (line.startsWith("port=")) {
                    config.setPort(Integer.parseInt(line.substring(5)));
                } else if (line.startsWith("username=")) {
                    config.setUsername(line.substring(9));
                } else if (line.startsWith("password=")) {
                    config.setPassword(line.substring(9));
                } else if (line.startsWith("from=")) {
                    config.setFrom(line.substring(5));
                } else if (line.startsWith("to=")) {
                    String[] emails = line.substring(3).split(",");
                    toList.addAll(Arrays.asList(emails));
                }
            }
            config.setTo(toList);
            scanner.close();
            return config;
        } catch (FileNotFoundException e) {
            System.err.println("Email config file not found: " + e.getMessage());
            return null;
        }
    }



}

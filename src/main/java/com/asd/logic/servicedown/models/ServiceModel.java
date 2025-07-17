package com.asd.logic.servicedown.models;


import com.asd.logic.servicedown.utils.ConstantValues;

public class ServiceModel {

    private String serviceUrl ;
    private Boolean isBlocked = false;
    private Boolean isHttps ;

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public Boolean getHttps() {
        return isHttps;
    }

    public void setHttps(Boolean https) {
        isHttps = https;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {

        String [] strings = serviceUrl.split(";");
        if (strings.length > 1 ) {
            if (strings[1].startsWith(ConstantValues.BLOCKED)){
                setIsBlocked(true);
            }
        }
        serviceUrl =    serviceUrl.replaceAll(";"+ ConstantValues.BLOCKED, "" );

        if (serviceUrl.contains("https")){
            isHttps = true;
        }

        this.serviceUrl = serviceUrl;
    }



    @Override
    public String toString() {
        return "ServiceModel{" +
                "serviceUrl='" + serviceUrl + '\'' +
                '}';
    }
}

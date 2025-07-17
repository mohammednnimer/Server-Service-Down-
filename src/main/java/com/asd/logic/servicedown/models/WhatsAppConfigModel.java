package com.asd.logic.servicedown.models;

import java.util.List;

public class WhatsAppConfigModel {
    private String url ;
    private List<String> mobiles ;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getMobiles() {
        return mobiles;
    }

    public void setMobiles(List<String> mobiles) {
        this.mobiles = mobiles;
    }
}

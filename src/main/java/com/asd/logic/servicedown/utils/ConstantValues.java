package com.asd.logic.servicedown.utils;

import java.nio.file.FileSystems;

public interface ConstantValues {
    public static final  String SERVICE_CONF_FILE = "config" + FileSystems.getDefault().getSeparator() + "services.conf" ;
    public static final String WHATS_APP_CONF_FILE = "config" + FileSystems.getDefault().getSeparator()+"whatsapp.conf" ;
    public static final String PREFIX_HTTP = "http://" ;

    public static final String SETTING_CONF_FILE = "config" + FileSystems.getDefault().getSeparator()+"settings.conf" ;

    String BLOCKED = "blocked" ;
String VALID = "VALID";
    String EXPIRED = "EXPIRED";

    public static final String EMAIL_CONF_FILE = "config/email.conf";
}

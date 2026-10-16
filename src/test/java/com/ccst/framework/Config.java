package com.ccst.framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties PROPS = new Properties();

    static {
        try(InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties")){
            if(in == null){
                throw new IllegalStateException("config.properties not found on the classpath");
            }
            PROPS.load(in);
        }
        catch (IOException e){
            throw new IllegalStateException("could not read config.properties" ,e);
        }
    }

    private Config(){}

    public static String get(String key){
        String value = System.getProperty(key);
        if (value == null) {
            value = System.getenv(key);
        }
        if (value == null) {
            value = PROPS.getProperty(key);
        }
        if(value == null){
            throw new IllegalStateException("Missing key in config.properties:" + key);
        }
        return value.trim();
    }
}

package com.goodsshop.properties;

import java.io.IOException;
import java.util.Properties;

public class Env {
	static Properties prop = new Properties();
	
	static {
        try {
            prop.load(Env.class.getResourceAsStream("Env.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // DB Properties
    public static String getEnvContext() {
    	return prop.getProperty("envContext");
    }
    
    public static String getDataSource() {
    	return prop.getProperty("DataSource");
    }
    
    // member
    public static String getSendEmail() {
    	return prop.getProperty("getSendEmail");
    }
    
    public static String getEmailPwd() {
    	return prop.getProperty("getEmailPwd");
    }
    
    // goods
    
    // admin
}

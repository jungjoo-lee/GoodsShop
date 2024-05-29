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
    
    // goods
    
    // admin
    public static String getQnaList() {
    	return prop.getProperty("getQnaList");
    }
    
    public static String getQnaTotal() {
    	return prop.getProperty("getQnaTotal");
    }
    
    public static String getQna() {
    	return prop.getProperty("getQna");
    }
}

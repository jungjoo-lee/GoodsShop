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
    
    // notice
    public static String getMainNoticeList() {
    	return prop.getProperty("getMainNoticeList");
    }
    
    public static String getNoticeList() {
    	return prop.getProperty("getNoticeList");
    }
    
    public static String getNoticeTotal() {
    	return prop.getProperty("getNoticeTotal");
	}
    
    // qna
    public static String getMainQnaList() {
    	return prop.getProperty("getMainQnaList");
    }
    
    public static String getQnaList() {
    	return prop.getProperty("getQnaList");
    }
    
    public static String getMyQnaList() {
		return prop.getProperty("getMyQnaList");
	}
    
    public static String getQnaTotal() {
    	return prop.getProperty("getQnaTotal");
    }
    
    public static String getMyQnaTotal() {
    	return prop.getProperty("getMyQnaTotal");
    }
    
    public static String getQna() {
    	return prop.getProperty("getQna");
    }
    
    // review
    public static String getMainReviewList() {
    	return prop.getProperty("getMainReviewList");
    }
    
    public static String getReviewList() {
    	return prop.getProperty("getReviewList");
    }
    
    public static String getMyReviewList() {
    	return prop.getProperty("getMyReviewList");
    }
    
    public static String getReviewTotal() {
    	return prop.getProperty("getReviewTotal");
    }
    
    public static String getMyReviewTotal() {
    	return prop.getProperty("getMyReviewTotal");
    }
    
    public static String getReview() {
    	return prop.getProperty("getReview");
    }
    
    // admin
    public static String getAdmin() {
    	return prop.getProperty("getAdmin");
    }
    
    public static String writeUpdateReply() {
    	return prop.getProperty("writeUpdateReply");
    }
    
    public static String deleteReply() {
    	return prop.getProperty("deleteReply");
    }
}

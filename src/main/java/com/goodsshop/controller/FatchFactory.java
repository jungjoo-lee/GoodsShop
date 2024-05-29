package com.goodsshop.controller;

import org.json.JSONObject;

import com.goodsshop.controller.action.FatchAction;

public class FatchFactory {
	private FatchFactory() {}
	private static FatchFactory itc = new FatchFactory();
	public static FatchFactory getInstance() { return itc; }
	
	public FatchAction getAction(String command, JSONObject json) {
		FatchAction fa = null;
		
		
		
		return fa;
	}
}

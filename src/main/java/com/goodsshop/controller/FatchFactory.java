package com.goodsshop.controller;

import org.json.JSONObject;

import com.goodsshop.controller.action.FatchAction;
import com.goodsshop.controller.action.admin.PageInfoAction;

public class FatchFactory {
	private FatchFactory() {}
	private static FatchFactory itc = new FatchFactory();
	public static FatchFactory getInstance() { return itc; }
	
	public FatchAction getAction(String command, JSONObject json) {
		FatchAction fa = null;
		
		if( command.equals("pageInfo") ) fa = new PageInfoAction(json);
		
		return fa;
	}
}

package com.goodsshop.controller;

import com.goodsshop.controller.action.FatchAction;
import com.goodsshop.controller.action.admin.GetContentAction;
import com.goodsshop.controller.action.admin.PageInfoAction;

public class FatchFactory {
	private FatchFactory() {}
	private static FatchFactory itc = new FatchFactory();
	public static FatchFactory getInstance() { return itc; }
	
	public FatchAction getAction(String command) {
		FatchAction fa = null;
		
		if (command.equals("pageInfo")) fa = new PageInfoAction();
		else if (command.equals("getContent")) fa = new GetContentAction();
		else if (command.equals("reviewWrite")) fa = new reviewWriteAction();
		else if (command.equals("reviewUpdate")) fa = new reviewUpdateAction();
		else if (command.equals("reviewDelete")) fa = new reviewDeleteAction();
		
		return fa;
	}
}

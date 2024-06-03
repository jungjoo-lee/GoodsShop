package com.goodsshop.controller;

import com.goodsshop.controller.action.FatchAction;
import com.goodsshop.controller.action.admin.GetContentAction;
import com.goodsshop.controller.action.admin.PageInfoAction;
import com.goodsshop.controller.action.review.ReviewDeleteAction;
import com.goodsshop.controller.action.review.ReviewUpdateAction;
import com.goodsshop.controller.action.review.ReviewWriteAction;

public class FatchFactory {
	private FatchFactory() {}
	private static FatchFactory itc = new FatchFactory();
	public static FatchFactory getInstance() { return itc; }
	
	public FatchAction getAction(String command) {
		FatchAction fa = null;
		
		if (command.equals("pageInfo")) fa = new PageInfoAction();
		else if (command.equals("getContent")) fa = new GetContentAction();
		else if (command.equals("reviewWrite")) fa = new ReviewWriteAction();
		else if (command.equals("reviewUpdate")) fa = new ReviewUpdateAction();
		else if (command.equals("reviewDelete")) fa = new ReviewDeleteAction();
		
		return fa;
	}
}

package com.goodsshop.controller;

import com.goodsshop.controller.action.Action;
import com.goodsshop.controller.action.IndexAction;
import com.goodsshop.controller.action.admin.AdminIndexAction;
import com.goodsshop.controller.action.admin.AdminLoginAction;
import com.goodsshop.controller.action.admin.AdminLoginFormAction;
import com.goodsshop.controller.action.admin.QnaAction;
import com.goodsshop.controller.action.admin.QnaReplyDeleteAction;
import com.goodsshop.controller.action.admin.QnaReplyUpdateAction;
import com.goodsshop.controller.action.admin.QnaReplyWriteAction;
import com.goodsshop.controller.action.admin.QnaViewAction;

public class ActionFactory {
	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; }
	
	public Action getAction(String command) {
		Action ac = null;
		
		if( command.equals("index") ) ac = new IndexAction();
		else if (command.equals("adminLoginForm")) ac = new AdminLoginFormAction();
		else if (command.equals("adminLogin")) ac = new AdminLoginAction();
		else if (command.equals("adminIndex")) ac = new AdminIndexAction();
		else if (command.equals("qna")) ac = new QnaAction();
		else if (command.equals("qnaView")) ac = new QnaViewAction();
		else if (command.equals("qnaReplyWrite")) ac = new QnaReplyWriteAction();
		else if (command.equals("qnaReplyUpdate")) ac = new QnaReplyUpdateAction();
		else if (command.equals("qnaReplyDelete")) ac = new QnaReplyDeleteAction();
		
		return ac;
	}
}

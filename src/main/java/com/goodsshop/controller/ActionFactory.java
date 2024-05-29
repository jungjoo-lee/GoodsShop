package com.goodsshop.controller;

import com.goodsshop.controller.action.Action;
import com.goodsshop.controller.action.IndexAction;
import com.goodsshop.controller.action.admin.AdminIndexAction;
import com.goodsshop.controller.action.admin.QnaAction;
import com.goodsshop.controller.action.admin.QnaViewAction;
import com.goodsshop.controller.action.admin.QnaWriteAction;
import com.goodsshop.controller.action.admin.QnaWriteFormAction;

public class ActionFactory {
	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; }
	
	public Action getAction(String command) {
		Action ac = null;
		
		if( command.equals("index") ) ac = new IndexAction();
		else if (command.equals("adminIndex")) ac = new AdminIndexAction();
		else if (command.equals("qna")) ac = new QnaAction();
		else if (command.equals("qnaView")) ac = new QnaViewAction();
		else if (command.equals("qnaWriteFrom")) ac = new QnaWriteFormAction();
		else if (command.equals("qnaWrite")) ac = new QnaWriteAction();
		
		return ac;
	}
}

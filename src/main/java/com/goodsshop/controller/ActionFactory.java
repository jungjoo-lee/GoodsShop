package com.goodsshop.controller;

import com.goodsshop.controller.action.Action;
import com.goodsshop.controller.action.IndexAction;
import com.goodsshop.controller.action.goods.GoodsCategoryAction;


public class ActionFactory {
	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; }
	
	public Action getAction(String command) {
		Action ac = null;
		
		if( command.equals("index") ) ac = new IndexAction();	
		else if (command.equals("goodsCategory")) ac = new GoodsCategoryAction();
		
		return ac;
	}
}

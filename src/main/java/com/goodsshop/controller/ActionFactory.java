package com.goodsshop.controller;

import com.goodsshop.controller.action.Action;

public class ActionFactory {
	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; }
	
	public Action getAction(String command) {
		Action ac = null;
		
		return ac;
	}
}

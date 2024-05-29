package com.goodsshop.controller;

import com.goodsshop.controller.action.Action;
import com.goodsshop.controller.action.IndexAction;
import com.goodsshop.controller.action.member.IDCheckAction;
import com.goodsshop.controller.action.member.JoinAction;
import com.goodsshop.controller.action.member.JoinPageAction;
import com.goodsshop.controller.action.mypage.DeleteMemberAction;

public class ActionFactory {
	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; }
	
	public Action getAction(String command) {
		Action ac = null;
		
		if( command.equals("index") ) 								ac = new IndexAction();
		
		//member
		if( command.equals("join") ) 									ac = new JoinAction();
		if( command.equals("joinPage") ) 							ac = new JoinPageAction();
		if( command.equals("IDCheck") ) 							ac = new IDCheckAction();
		
		//mypage
		if( command.equals("deleteMember") ) 					ac = new DeleteMemberAction();

		
		
		return ac;
	}
}

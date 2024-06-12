package com.goodsshop.controller;

import com.goodsshop.controller.action.FatchAction;
import com.goodsshop.controller.action.admin.CheckDeleteAction;
import com.goodsshop.controller.action.admin.DiscardMemberAction;
import com.goodsshop.controller.action.admin.GetContentAction;
import com.goodsshop.controller.action.admin.PageInfoAction;
import com.goodsshop.controller.action.admin.SwitchYNAction;
import com.goodsshop.controller.action.qna.QnaDeleteAction;
import com.goodsshop.controller.action.qna.QnaUpdateAction;
import com.goodsshop.controller.action.qna.QnaWriteAction;
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
		// member
		else if (command.equals("switchYN")) fa = new SwitchYNAction();
		else if (command.equals("discardMember")) fa = new DiscardMemberAction();
		// util
		else if (command.equals("checkDelete")) fa = new CheckDeleteAction();
		// qna
		else if (command.equals("qnaWrite")) fa = new QnaWriteAction();
		else if (command.equals("qnaUpdate")) fa = new QnaUpdateAction();
		else if (command.equals("qnaDelete")) fa = new QnaDeleteAction();
		// review
		else if (command.equals("reviewWrite")) fa = new ReviewWriteAction();
		else if (command.equals("reviewUpdate")) fa = new ReviewUpdateAction();
		else if (command.equals("reviewDelete")) fa = new ReviewDeleteAction();
		
		return fa;
	}
}
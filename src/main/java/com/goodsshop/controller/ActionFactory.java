package com.goodsshop.controller;

import com.goodsshop.controller.action.Action;
import com.goodsshop.controller.action.ImageWriteAction;
import com.goodsshop.controller.action.IndexAction;
import com.goodsshop.controller.action.admin.AdminIndexAction;
import com.goodsshop.controller.action.admin.AdminLoginAction;
import com.goodsshop.controller.action.admin.AdminLoginFormAction;
import com.goodsshop.controller.action.admin.AdminQnaListAction;
import com.goodsshop.controller.action.admin.QnaReplyDeleteAction;
import com.goodsshop.controller.action.admin.QnaReplyUpdateAction;
import com.goodsshop.controller.action.admin.QnaReplyWriteAction;
import com.goodsshop.controller.action.admin.AdminQnaViewAction;
import com.goodsshop.controller.action.admin.QnaViewAction;
import com.goodsshop.controller.action.goods.GoodsCategoryAction;
import com.goodsshop.controller.action.email.FindIdOKAction;
import com.goodsshop.controller.action.member.IDCheckAction;
import com.goodsshop.controller.action.member.JoinAction;
import com.goodsshop.controller.action.member.JoinPageAction;
import com.goodsshop.controller.action.member.SearchIdAction;
import com.goodsshop.controller.action.member.SearchPwdAction;
import com.goodsshop.controller.action.mypage.DeleteMemberAction;
import com.goodsshop.controller.action.notice.NoticeListAction;
import com.goodsshop.controller.action.qna.QnaListAction;
import com.goodsshop.controller.action.qna.QnaViewAction;
import com.goodsshop.controller.action.review.ReviewListAction;
import com.goodsshop.controller.action.review.ReviewViewAction;
import com.goodsshop.controller.cart.DeleteCartAction;
import com.goodsshop.controller.cart.GetPaymentAction;
import com.goodsshop.controller.cart.GoOrderAction;
import com.goodsshop.controller.cart.InsertCartAction;
import com.goodsshop.controller.cart.InsertWishAction;
import com.goodsshop.controller.cart.OrderCartAction;
import com.goodsshop.controller.cart.ViewCartAction;
import com.goodsshop.controller.cart.ViewWishAction;
import com.goodsshop.controller.cart.WishToCartAction;
import com.goodsshop.controller.goods.GoodsCategoryAction;
import com.goodsshop.controller.goods.GoodsDetailViewAction;
import com.goodsshop.controller.goods.OrderNowAction;
import com.goodsshop.controller.member.login.FindIdAction;
import com.goodsshop.controller.member.login.FindIdFormAction;
import com.goodsshop.controller.member.login.FindPwdAction;
import com.goodsshop.controller.member.login.FindPwdFormAction;
import com.goodsshop.controller.member.login.FindZipnumAction;
import com.goodsshop.controller.member.login.LoginAction;
import com.goodsshop.controller.member.login.LoginFormAction;
import com.goodsshop.controller.member.login.LogoutAction;
import com.goodsshop.controller.member.update.UpdateMemberAction;
import com.goodsshop.controller.member.update.UpdateMemberFormAction;


public class ActionFactory {
	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; }
	
	public Action getAction(String command) {
		Action ac = null;
		
		if( command.equals("index") ) ac = new IndexAction();	
		else if (command.equals("goodsCategory")) ac = new GoodsCategoryAction();
		//notice
		else if (command.equals("noticeList")) ac = new NoticeListAction();
		
		// qna
		else if (command.equals("qnaList")) ac = new QnaListAction();
		else if (command.equals("qnaView")) ac = new QnaViewAction();
		
		// review
		else if (command.equals("reviewList")) ac = new ReviewListAction();
		else if (command.equals("reviewView")) ac = new ReviewViewAction();
		
		// admin
		else if (command.equals("adminLoginForm")) ac = new AdminLoginFormAction();
		else if (command.equals("adminLogin")) ac = new AdminLoginAction();
		else if (command.equals("adminIndex")) ac = new AdminIndexAction();
		else if (command.equals("adminQnaList")) ac = new AdminQnaListAction();
		else if (command.equals("adminQnaView")) ac = new AdminQnaViewAction();
		else if (command.equals("qnaReplyWrite")) ac = new QnaReplyWriteAction();
		else if (command.equals("qnaReplyUpdate")) ac = new QnaReplyUpdateAction();
		else if (command.equals("qnaReplyDelete")) ac = new QnaReplyDeleteAction();
		
		//멤버
		else if (command.equals("loginForm")) ac = new LoginFormAction();
		else if (command.equals("login")) ac = new LoginAction();
		else if (command.equals("logout")) ac = new LogoutAction();
		else if (command.equals("findIdForm")) ac = new FindIdFormAction();
		else if (command.equals("findId")) ac = new FindIdAction();
		else if (command.equals("searchId")) ac = new SearchIdAction();
		else if (command.equals("findPwdForm")) ac = new FindPwdFormAction();
		else if (command.equals("findPwd")) ac = new FindPwdAction();
		else if (command.equals("searchPwd")) ac = new SearchPwdAction();

		// 멤버 페이지
		else if (command.equals("updateMemberForm")) ac = new UpdateMemberFormAction();
		else if (command.equals("updateMember")) ac = new UpdateMemberAction();
		else if (command.equals("findZipnum")) ac = new FindZipnumAction();
		
		else if (command.equals("imageWrite")) ac = new ImageWriteAction();
		//email
		else if (command.equals("findIdOK")) ac = new FindIdOKAction();
		
		//member
		if( command.equals("join") ) ac = new JoinAction();
		if( command.equals("joinPage") ) 	ac = new JoinPageAction();
		if( command.equals("IDCheck") ) 	ac = new IDCheckAction();
		
		//mypage
		if( command.equals("deleteMember") ) ac = new DeleteMemberAction();
		

		//goods
		if(command.equals("goodsDetailView")) ac = new GoodsDetailViewAction();
		if(command.equals("viewCartlist")) ac = new ViewCartAction();
		if(command.equals("viewWishlist")) ac = new ViewWishAction();
		if(command.equals("addCart")) ac = new InsertCartAction();
		if(command.equals("addWish")) ac = new InsertWishAction();
		if(command.equals("wishToCart")) ac = new WishToCartAction();
		if(command.equals("deleteWish")) ac = new DeleteWishAction();
		if(command.equals("deleteCart")) ac = new DeleteCartAction();
		if(command.equals("orderNow")) ac = new OrderNowAction();
		if(command.equals("orderFromCart")) ac = new OrderCartAction();
		if(command.equals("getPayment")) ac = new GetPaymentAction();
		if(command.equals("goOrder")) ac = new GoOrderAction();
		
		
		
		
		return ac;
	}
}

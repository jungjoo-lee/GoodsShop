package com.goodsshop.controller;

import com.goodsshop.controller.action.Action;
import com.goodsshop.controller.action.ImageWriteAction;
import com.goodsshop.controller.action.IndexAction;
import com.goodsshop.controller.action.admin.AdminIndexAction;
import com.goodsshop.controller.action.admin.AdminLoginAction;
import com.goodsshop.controller.action.admin.AdminLoginFormAction;
import com.goodsshop.controller.action.admin.AdminNoticeListAction;
import com.goodsshop.controller.action.admin.AdminNoticeViewAction;
import com.goodsshop.controller.action.admin.AdminQnaListAction;
import com.goodsshop.controller.action.admin.AdminQnaViewAction;
import com.goodsshop.controller.action.admin.AdminReviewListAction;
import com.goodsshop.controller.action.admin.DiscardMemberAction;
import com.goodsshop.controller.action.admin.NoticeDeleteAction;
import com.goodsshop.controller.action.admin.NoticeUpdateAction;
import com.goodsshop.controller.action.admin.QnaReplyDeleteAction;
import com.goodsshop.controller.action.admin.QnaReplyUpdateAction;
import com.goodsshop.controller.action.admin.QnaReplyWriteAction;
import com.goodsshop.controller.action.admin.SwitchYNAction;
import com.goodsshop.controller.action.admin.goods.AdminBestGoodsAction;
import com.goodsshop.controller.action.admin.goods.AdminCategoryViewAction;
import com.goodsshop.controller.action.admin.goods.AdminDeleteGoodsAction;
import com.goodsshop.controller.action.admin.goods.AdminGoodsSearchAction;
import com.goodsshop.controller.action.admin.goods.AdminGoodsViewAction;
import com.goodsshop.controller.action.admin.goods.AdminInsertGoodsAction;
import com.goodsshop.controller.action.admin.goods.AdminInsertGoodsFormAction;
import com.goodsshop.controller.action.admin.goods.AdminUpdateGoodsAction;
import com.goodsshop.controller.action.admin.goods.AdminUpdateGoodsFormAction;
import com.goodsshop.controller.action.admin.goods.AdminUseYNGoodsAction;
import com.goodsshop.controller.action.admin.order.AdminOrderDetailViewAction;
import com.goodsshop.controller.action.admin.order.AdminOrderListAction;
import com.goodsshop.controller.action.admin.order.AdminOrderSearchAction;
import com.goodsshop.controller.action.admin.order.AdminUpdateOrderStatAction;
import com.goodsshop.controller.action.cart.DeleteCartAction;
import com.goodsshop.controller.action.cart.DeleteWishAction;
import com.goodsshop.controller.action.cart.GetPaymentAction;
import com.goodsshop.controller.action.cart.GoOrderAction;
import com.goodsshop.controller.action.cart.InsertCartAction;
import com.goodsshop.controller.action.cart.InsertWishAction;
import com.goodsshop.controller.action.cart.OrderCartAction;
import com.goodsshop.controller.action.cart.OrderNowAction;
import com.goodsshop.controller.action.cart.ViewCartAction;
import com.goodsshop.controller.action.cart.ViewWishAction;
import com.goodsshop.controller.action.cart.WishToCartAction;
import com.goodsshop.controller.action.email.FindIdOKAction;
import com.goodsshop.controller.action.goods.GoodsDetailViewAction;
import com.goodsshop.controller.action.goods.SearchGoodsAction;
import com.goodsshop.controller.action.goods.ViewCategoryAction;
import com.goodsshop.controller.action.member.GetEmailAction;
import com.goodsshop.controller.action.member.IDCheckAction;
import com.goodsshop.controller.action.member.JoinAction;
import com.goodsshop.controller.action.member.JoinPageAction;
import com.goodsshop.controller.action.member.SearchIdAction;
import com.goodsshop.controller.action.member.SearchPwdAction;
import com.goodsshop.controller.action.mypage.DeleteMemberAction;
import com.goodsshop.controller.action.mypage.OrderDetailViewAction;
import com.goodsshop.controller.action.mypage.ViewOrderAction;
import com.goodsshop.controller.action.notice.NoticeInsertAction;
import com.goodsshop.controller.action.notice.NoticeUpdateFormAction;
import com.goodsshop.controller.action.notice.insertNoticeFormAction;
import com.goodsshop.controller.action.qna.QnaListAction;
import com.goodsshop.controller.action.qna.QnaViewAction;
import com.goodsshop.controller.action.qna.QnaWriteFormAction;
import com.goodsshop.controller.action.review.ReviewListAction;
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
		//notice
		else if (command.equals("noticeInsert")) ac = new NoticeInsertAction();
		else if (command.equals("noticeDelete")) ac = new NoticeDeleteAction();
		else if (command.equals("noticeUpdate")) ac = new NoticeUpdateAction();
		else if (command.equals("noticeUpdateForm")) ac = new NoticeUpdateFormAction();
		else if (command.equals("insertNoticeForm")) ac = new insertNoticeFormAction();

		// qna
		else if (command.equals("qnaList")) ac = new QnaListAction();
		else if (command.equals("qnaView")) ac = new QnaViewAction();
		else if (command.equals("qnaWriteForm")) ac = new QnaWriteFormAction();
		
		// review
		else if (command.equals("reviewList")) ac = new ReviewListAction();
		
		// admin
		else if (command.equals("adminLoginForm")) ac = new AdminLoginFormAction();
		else if (command.equals("adminLogin")) ac = new AdminLoginAction();
		else if (command.equals("adminIndex")) ac = new AdminIndexAction();
		else if (command.equals("adminQnaList")) ac = new AdminQnaListAction();
		else if (command.equals("adminQnaView")) ac = new AdminQnaViewAction();
		else if (command.equals("qnaReplyWrite")) ac = new QnaReplyWriteAction();
		else if (command.equals("qnaReplyUpdate")) ac = new QnaReplyUpdateAction();
		else if (command.equals("qnaReplyDelete")) ac = new QnaReplyDeleteAction();
		else if (command.equals("adminNoticeList")) ac = new AdminNoticeListAction();
		else if (command.equals("adminNoticeView")) ac = new AdminNoticeViewAction();

		//멤버 로그인
		else if (command.equals("switchYN")) ac = new SwitchYNAction();
		else if (command.equals("discardMember")) ac = new DiscardMemberAction();
		else if (command.equals("adminReviewList")) ac = new AdminReviewListAction();
		
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

		//마이페이지
		else if (command.equals("updateMemberForm")) ac = new UpdateMemberFormAction();
		else if (command.equals("updateMember")) ac = new UpdateMemberAction();
		else if( command.equals("deleteMember") ) ac = new DeleteMemberAction();
		else if (command.equals("findZipnum")) ac = new FindZipnumAction();

		else if (command.equals("imageWrite")) ac = new ImageWriteAction();
		
		//email
		else if (command.equals("findIdOK")) ac = new FindIdOKAction();
		
		//member
		else if( command.equals("join") ) 									ac = new JoinAction();
		else if( command.equals("joinPage") ) 							ac = new JoinPageAction();
		else if( command.equals("IDCheck") ) 							ac = new IDCheckAction();
		else if( command.equals("getEmail") ) 							ac = new GetEmailAction();
		
		
		//mypage
		else if( command.equals("deleteMember") ) ac = new DeleteMemberAction();
		

		//goods
		else if(command.equals("goodsDetailView")) ac = new GoodsDetailViewAction();
		else if(command.equals("viewCartlist")) ac = new ViewCartAction();
		else if(command.equals("addCart")) ac = new InsertCartAction();
		else if(command.equals("deleteCart")) ac = new DeleteCartAction();
		
		else if(command.equals("viewWishlist")) ac = new ViewWishAction();
		else if(command.equals("addWish")) ac = new InsertWishAction();
		else if(command.equals("wishToCart")) ac = new WishToCartAction();
		else if(command.equals("deleteWish")) ac = new DeleteWishAction();
		
		else if(command.equals("viewOrderList")) ac = new ViewOrderAction();
		else if(command.equals("orderFromCart")) ac = new OrderCartAction();
		else if(command.equals("orderNow")) ac = new OrderNowAction();
		else if(command.equals("getPayment")) ac = new GetPaymentAction();
		else if(command.equals("goOrder")) ac = new GoOrderAction();
		else if(command.equals("orderDetailView")) ac = new OrderDetailViewAction();
		
		else if(command.equals("viewCategory")) ac = new ViewCategoryAction();
		else if(command.equals("searchGoods")) ac = new SearchGoodsAction();
		
		
		//admin-goods		
		else if(command.equals("adminGoodsView")) ac = new AdminGoodsViewAction();
		else if(command.equals("adminGoodsUpdateForm")) ac = new AdminUpdateGoodsFormAction();
		else if(command.equals("adminUpdateGoods")) ac = new AdminUpdateGoodsAction();
		else if(command.equals("adminInsertGoodsForm")) ac = new AdminInsertGoodsFormAction();
		else if(command.equals("adminInsertGoods")) ac = new AdminInsertGoodsAction();
		else if(command.equals("adminGoodsDelete")) ac = new AdminDeleteGoodsAction();
		else if(command.equals("adminBestToggle")) ac = new AdminBestGoodsAction();
		else if(command.equals("adminUseYnToggle")) ac = new AdminUseYNGoodsAction();
		else if(command.equals("adminCategoryView")) ac = new AdminCategoryViewAction();
		else if(command.equals("adminGoodsSearch")) ac = new AdminGoodsSearchAction();
		
		//admin-order
		else if(command.equals("adminOrderView")) ac = new AdminOrderListAction();
		else if(command.equals("adminOrderDetailView")) ac = new AdminOrderDetailViewAction();
		else if(command.equals("adminUpdateOrderStatus")) ac = new AdminUpdateOrderStatAction();
		else if(command.equals("adminSearchOrder")) ac = new AdminOrderSearchAction();

		
		
		
		
		
		return ac;
	}
}

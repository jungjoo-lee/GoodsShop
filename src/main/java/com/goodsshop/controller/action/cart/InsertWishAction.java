package com.goodsshop.controller.action.cart;

import java.io.IOException;
import java.io.PrintWriter;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.CartDAO;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dto.GoodsVO;
import com.goodsshop.dto.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class InsertWishAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		String userid = loginUser.getUserid();

		int gseq = Integer.parseInt(request.getParameter("gseq"));
		
		CartDAO cdao = new CartDAO();
		GoodsDAO gdao = new GoodsDAO();
		GoodsVO gvo = gdao.getGoods(gseq);
		
		//if userid, gvo로 조회해서 레코드가 있다면 > alert ("이미 찜목록에 담긴 상품입니다")
		//else > 레코드 추가
		
		int cnt = cdao.selectWish(userid, gvo);
		
		if( cnt != 0 ) {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('이미 찜목록에 동일한 상품이 존재합니다.');</script>");
			out.print("<script>history.go(-1);</script>");

		} else {		
			cdao.insertWish(userid, gvo);		
			response.sendRedirect("gshop.do?command=viewWishlist");
		}
		



	}

}

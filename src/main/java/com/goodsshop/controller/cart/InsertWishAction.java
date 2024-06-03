package com.goodsshop.controller.cart;

import java.io.IOException;

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

		if (loginUser == null) {
			response.sendRedirect("gshop.do?command=index");
		} else {
			
			CartDAO cdao = new CartDAO();
			GoodsDAO gdao = new GoodsDAO();
			GoodsVO gvo = gdao.getGoods(gseq);

			cdao.insertWish(userid, gvo);

			response.sendRedirect("gshop.do?command=index");

		}

	}

}

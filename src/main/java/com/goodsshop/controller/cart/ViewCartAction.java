package com.goodsshop.controller.cart;

import java.io.IOException;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dto.CartVO;
import com.goodsshop.dto.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ViewCartAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		List<CartVO> cartlist = (List<CartVO>)session.getAttribute("cartlist");


		if (loginUser == null) {
			request.getRequestDispatcher("gshop.do?command=index").forward(request, response);
		} else {

			System.out.println("장바구니 비었음");
			request.getRequestDispatcher("jsp/goods/cartlistView.jsp").forward(request, response);

		}
	}
}

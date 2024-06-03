package com.goodsshop.controller.cart;

import java.io.IOException;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.controller.member.MemberVO;
import com.goodsshop.dao.OrderDAO;
import com.goodsshop.dto.OrderVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ViewOrderAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		OrderDAO odao = new OrderDAO();
		List<OrderVO> orderList = odao.selectOrderList(mvo.getUserid());
		
		
		request.setAttribute("orderList", orderList);
		request.getRequestDispatcher("jsp/mypage/orderlistView.jsp").forward(request, response);
	}
}

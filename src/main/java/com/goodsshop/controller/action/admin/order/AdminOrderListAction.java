package com.goodsshop.controller.action.admin.order;

import java.io.IOException;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.OrderDAO;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.dto.OrderVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminOrderListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		OrderDAO odao = new OrderDAO();
		List<OrderVO> orderList = odao.getAllOrderList("");
		
		
		request.setAttribute("orderList", orderList);
		request.getRequestDispatcher("WEB-INF/jsp/admin/adminOrderView.jsp").forward(request, response);

	}

}

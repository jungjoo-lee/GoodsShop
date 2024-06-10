package com.goodsshop.controller.action.mypage;

import java.io.IOException;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.OrderDAO;
import com.goodsshop.dto.OrderVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OrderDetailViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int oseq = Integer.parseInt(request.getParameter("oseq"));
		
		OrderDAO odao = new OrderDAO();
		List<OrderVO> orderDetailList = odao.selectOrderDetail(oseq);
		
		request.setAttribute("orderDetailList", orderDetailList);	
		request.getRequestDispatcher("WEB-INF/jsp/mypage/orderDetailView.jsp").forward(request, response);
	}

}

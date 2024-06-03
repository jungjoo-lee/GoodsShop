package com.goodsshop.controller.cart;

import java.io.IOException;

import com.goodsshop.controller.action.Action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetPaymentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numberOfGoods = Integer.parseInt(request.getParameter("numberOfGoods"));
		int totalPrice = Integer.parseInt(request.getParameter("orderTotalPrice"));
		
		request.getRequestDispatcher("jsp/goods/paymentPage.jsp").forward(request, response);
	}
}

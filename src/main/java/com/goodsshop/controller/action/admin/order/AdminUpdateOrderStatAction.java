package com.goodsshop.controller.action.admin.order;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.OrderDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminUpdateOrderStatAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] oseqs = request.getParameterValues("oseq");
		String osseq = request.getParameter("osseq");
		OrderDAO odao = new OrderDAO();
		
		for(String oseq : oseqs) {
			odao.updateOrderStatus(oseq, osseq);
		}
		
		response.sendRedirect("gshop.do?command=adminOrderView");
		
	}
}

package com.goodsshop.controller.action.admin.order;

import java.io.IOException;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dao.OrderDAO;
import com.goodsshop.dto.OrderVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminOrderDetailViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int oseq = Integer.parseInt(request.getParameter("oseq"));
		
		OrderDAO odao = new OrderDAO();
		List<OrderVO> orderDetailList = odao.selectOrderDetail(oseq);
		
		for (OrderVO ovo : orderDetailList) {
			GoodsDAO gdao = new GoodsDAO();
			ovo.setThum(gdao.getThumbnail(ovo.getGseq()));
		}
		
		request.setAttribute("orderDetailList", orderDetailList);	
		request.getRequestDispatcher("WEB-INF/jsp/admin/adminOrderDetail.jsp").forward(request, response);
		
	}

}

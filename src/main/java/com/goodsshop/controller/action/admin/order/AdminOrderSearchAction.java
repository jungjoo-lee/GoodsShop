package com.goodsshop.controller.action.admin.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.OrderDAO;
import com.goodsshop.dto.OrderVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminOrderSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchBy = request.getParameter("searchBy");
		String key = request.getParameter("searchKey");
		OrderDAO odao = new OrderDAO();
		
		List<OrderVO> orderList = new ArrayList<OrderVO>();
		
		System.out.println();
		
		if (searchBy.equals("gname")) {
			orderList = odao.getAllOrderList(key);
		} else if (searchBy.equals("userid")) {
			orderList = odao.getAllOrderListById(key);
		} else if (searchBy.equals("name")) {
			orderList = odao.getAllOrderListByName(key);
		}
		
		
		

		request.setAttribute("orderList", orderList);
		request.getRequestDispatcher("WEB-INF/jsp/admin/adminOrderView.jsp").forward(request, response);		
	}

}

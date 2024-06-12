package com.goodsshop.controller.action.admin.order;

import java.io.IOException;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.controller.action.goods.MPaging;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dao.OrderDAO;
import com.goodsshop.dto.OrderVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminOrderListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String key = "";
		
		if (request.getParameter("key") != null) {
			key = request.getParameter("key");
			session.setAttribute("key", key);
		} else if (session.getAttribute("key") != null) {
			key = (String) session.getAttribute("key");
		} else {
			session.removeAttribute("key");
		}
		
		int page = 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
		} else if(session.getAttribute("page") != null) {
			page = (Integer)session.getAttribute("page");
		} else {
			session.removeAttribute("page");
		}
		
		MPaging paging = new MPaging();
		paging.setPage(page);
		
		OrderDAO odao = new OrderDAO();
		int count = odao.getAllCount("", "oseq");
		paging.setTotalCount(count);
		
		List<OrderVO> orderList = odao.getAllOrderList("", paging);
		
		
		request.setAttribute("orderList", orderList);
		request.setAttribute("url", "gshop.do?command=adminOrderView");		
		request.setAttribute("paging", paging);		
		request.getRequestDispatcher("WEB-INF/jsp/admin/adminOrderView.jsp").forward(request, response);

	}

}

package com.goodsshop.controller.action.admin.order;

import java.io.IOException;
import java.util.ArrayList;
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

public class AdminOrderSearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String searchBy = request.getParameter("searchBy");
		String key = request.getParameter("searchKey");
		OrderDAO odao = new OrderDAO();
		
		List<OrderVO> orderList = new ArrayList<OrderVO>();
		
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
		

		int count = 0;
		
		String url = "gshop.do?command=adminSearchOrder";
		
		 if (searchBy.equals("gname")) {
			
			count = odao.getAllCount(key, "gname");
			paging.setTotalCount(count);
			orderList = odao.getAllOrderList(key, paging);
			
		} else if (searchBy.equals("userid")) {
			
			count = odao.getAllCount(key, "userid");
			paging.setTotalCount(count);
			orderList = odao.getAllOrderListById(key, paging);		
			
		} else if (searchBy.equals("name")) {
			
			count = odao.getAllCount(key, "name");
			paging.setTotalCount(count);			
			orderList = odao.getAllOrderListByName(key, paging);
			
		}
		

		request.setAttribute("orderList", orderList);
		request.setAttribute("searchBy", searchBy);
		request.setAttribute("key", key);
		request.setAttribute("url", url);
		request.setAttribute("paging", paging);		
		request.getRequestDispatcher("WEB-INF/jsp/admin/adminOrderView.jsp").forward(request, response);		
	}

}

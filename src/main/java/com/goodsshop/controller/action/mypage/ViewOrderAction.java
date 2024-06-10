package com.goodsshop.controller.action.mypage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.controller.action.goods.MPaging;
import com.goodsshop.dao.OrderDAO;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.dto.OrderVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ViewOrderAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");

		if (mvo == null) {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('로그인을 먼저 진행해주세요');</script>");
			out.print("<script>location.href='gshop.do?command=loginForm';</script>");
		} else {

			int page = 1;

			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if (session.getAttribute("page") != null) {
				page = (Integer) session.getAttribute("page");
			} else {
				session.removeAttribute("page");
			}

			MPaging paging = new MPaging();
			paging.setPage(page);

			OrderDAO odao = new OrderDAO();
			int count = odao.getAllCount(mvo.getUserid(), "userid");
			paging.setTotalCount(count);

			List<OrderVO> orderList = odao.selectOrderList(mvo.getUserid(), paging);

			request.setAttribute("orderList", orderList);
			request.setAttribute("paging", paging);
			request.getRequestDispatcher("jsp/mypage/orderlistView.jsp").forward(request, response);
		}
	}
}

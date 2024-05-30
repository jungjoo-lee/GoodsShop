package com.goodsshop.controller.cart;

import java.io.IOException;
import java.io.PrintWriter;

import com.goodsshop.controller.action.Action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ViewCartAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		PrintWriter out = response.getWriter();
		
		if (session.getAttribute("loginUser") == null) {
			out.print("<script> alert('로그인 정보가 만료되었습니다') </script>");
			request.getRequestDispatcher("gshop.do?command=index").forward(request, response);
		} else {
			if (session.getAttribute("cartlist") == null) {
				request.getRequestDispatcher("gshop.do?command=index").forward(request, response);
			} else {
				request.getRequestDispatcher("jsp/goods/cartlistView.jsp").forward(request, response);
			}
			
		}
		

	}

}

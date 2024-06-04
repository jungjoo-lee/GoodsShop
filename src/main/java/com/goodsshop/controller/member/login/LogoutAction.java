package com.goodsshop.controller.member.login;

import java.io.IOException;

import com.goodsshop.controller.action.Action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");
		request.getRequestDispatcher("WEB-INF/jsp/index/main.jsp").forward(request, response); // 경로

	}

}

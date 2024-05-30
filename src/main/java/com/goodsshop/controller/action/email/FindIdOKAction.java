package com.goodsshop.controller.action.email;

import java.io.IOException;
import java.io.PrintWriter;

import com.goodsshop.controller.action.Action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class FindIdOKAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String userSecurityCode = request.getParameter("securityCodeInput"); // 이메일로 온 인증번호를 친값
	      HttpSession session = request.getSession();
	      String verificationCode = (String) session.getAttribute("verificationCode"); // 이메일로 보낼때 세션에 저장한 값
	      PrintWriter out = response.getWriter();
	      
	      System.out.println(request.getParameter("securityCodeInput"));
	      
	      if (userSecurityCode.equals(verificationCode)) {
	         out.print("<script>alert('인증되었습니다.');</script>");
	         request.getRequestDispatcher("jsp/member/searchLogin.jsp").forward(request, response);
	      } else {
	    	  System.out.println(userSecurityCode);
	    	  System.out.println(verificationCode);
	         out.print("<script>alert('복붙도 못하냐?');</script>");
	         request.getRequestDispatcher("jsp/member/searchLogin.jsp").forward(request, response);
	      }
	      out.close();
	}

}

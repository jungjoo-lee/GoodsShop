package com.goodsshop.controller.action.admin;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class EmailAuthNumAction {
	public void emailAuthNum(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int recAuthNum = Integer.parseInt(request.getParameter("recAuthNum")); // 이메일로 온 인증번호를 친값
		HttpSession session = request.getSession();
		int authNum = (Integer)session.getAttribute("authNum"); // 이메일로 보낼때 세션에 저장한 값
		PrintWriter out = response.getWriter();
		
		if (recAuthNum == authNum) {
			out.print("<script>alert('인증되었습니다.');</script>");
			out.print("<script>onwer.뭐시기.뭐시기.value = '인증되었습니다.';</script>");
			out.print("<script>this.close();</script>");
		} else {
			out.print("<script>alert('복붙도 못하냐?');</script>");
		}
		out.close();
	}
}

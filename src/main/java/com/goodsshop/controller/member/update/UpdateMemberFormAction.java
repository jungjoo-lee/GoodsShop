package com.goodsshop.controller.member.update;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dto.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UpdateMemberFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");

		if (mvo != null) {
			request.getRequestDispatcher("/WEB-INF/jsp/member/updateMember.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/jsp/member/loginForm.jsp").forward(request, response);
		}

	}
}

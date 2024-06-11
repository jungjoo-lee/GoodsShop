package com.goodsshop.controller.action.mypage;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.dao.MemberDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteMemberAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		
		if( mvo==null ) {
			response.sendRedirect("gshop.do?command=loginForm");
		}else {
			MemberDAO mdao = MemberDAO.getInstance();
			mdao.deleteMember(mvo.getUserid());
			
			session.removeAttribute("loginUser");
			
			//session.setAttribute("message", "정상 탈퇴 처리되었습니다.");
			response.sendRedirect("gshop.do?command=loginForm");
		}

	}

}

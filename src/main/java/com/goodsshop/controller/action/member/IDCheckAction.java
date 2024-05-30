package com.goodsshop.controller.action.member;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.MemberDao;
import com.goodsshop.dto.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IDCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		MemberDao mdao = MemberDao.getInstance();
		MemberVO mvo = mdao.getMember(userid);
		
		if( mvo == null ) request.setAttribute("result", -1);		// 사용 가능
		else request.setAttribute("result", 1); 							// 사용 중
		
		request.setAttribute("userid", userid);
		request.getRequestDispatcher("jsp/member/IDCheckPage.jsp").forward(request, response);
//		request.getRequestDispatcher("jsp/member/asd.jsp").forward(request, response);
	}

}

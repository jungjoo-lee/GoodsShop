package com.goodsshop.controller.action.member;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.MemberDao;
import com.goodsshop.dto.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberDao mdao = MemberDao.getInstance();
		MemberVO mvo = new MemberVO();
		
		mvo.setUserid(request.getParameter("userid"));
		mvo.setPwd(request.getParameter("pwd"));
		//mvo.setGseq(Integer.parseInt(request.getParameter("gseq")));
		mvo.setName(request.getParameter("name"));
		mvo.setEmail(request.getParameter("email"));
		mvo.setZip_code(request.getParameter("zip_code"));
		mvo.setAddress(request.getParameter("address"));
		mvo.setD_address(request.getParameter("d_address"));
		mvo.setPhone(request.getParameter("phone"));
		
		int result = mdao.insertMember(mvo);
		HttpSession session = request.getSession();
		if( result == 1 ) session.setAttribute("message", "회원가입이 완료되었습니다. 로그인 하세요");
		else session.setAttribute("message", "관리자에게 문의하세요");
		
		response.sendRedirect("gshop.do?command=loginForm");
	}

}

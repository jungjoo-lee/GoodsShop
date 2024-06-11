package com.goodsshop.controller.member.login;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.controller.member.MemberDao;
import com.goodsshop.dto.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		
		MemberDao mdao = MemberDao.getInstance();
		MemberVO mvo = mdao.getMember(userid);
		
		String url = "gshop.do?command=loginForm";
		if(mvo==null) 
			request.setAttribute("message", "아이디 혹은 패스워드가 틀립니다");
		else if (!mvo.getPwd().equals(pwd)) {
			request.setAttribute("message", "아이디 혹은 패스워드가 틀립니다");}
		 else if (mvo.getPwd().equals(pwd)) {
			 if(mvo.getIs_login() == 1) {
			    url = "gshop.do?command=index";
			    HttpSession session = request.getSession();
			    session.setAttribute("loginUser", mvo);
			    
			    session.removeAttribute("loginAdmin");
			 } else {
				 request.setAttribute("message", "해당 계정은 휴면상태이거나 탈퇴상태입니다");
			 }
		 } else {
			request.setAttribute("message", "관리자에게 문의하세요");	
		
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}


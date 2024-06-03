package com.goodsshop.controller.action.admin;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.AdminDAO;
import com.goodsshop.dto.AdminVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminLoginAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO dao = AdminDAO.getInstance();
		AdminVO vo = dao.getAdmin(request.getParameter("adminID"));
		String url = "gshop.do?command=adminLoginForm";
		
		if(vo == null) 
			request.setAttribute("message", "실패");
		else if(!vo.getPwd().equals(request.getParameter("pwd"))) 
			request.setAttribute("message", "실패");
		else if( vo.getPwd().equals(request.getParameter("pwd")) ) {
			url = "gshop.do?command=adminIndex";
			HttpSession session = request.getSession();
			session.setAttribute("loginAdmin", vo);
		}
		
		request.getRequestDispatcher(url).forward(request, response);;
	}
}
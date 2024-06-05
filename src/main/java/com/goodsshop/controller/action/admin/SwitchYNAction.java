package com.goodsshop.controller.action.admin;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.AdminDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SwitchYNAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminDAO dao = AdminDAO.getInstance();
		String [] YNlist = request.getParameterValues("YN");
		System.out.println(YNlist);

		
		if(YNlist==null) {
		response.sendRedirect("gshop.do?command=adminIndex");
		}else {
		for (String userid : YNlist) {
			dao.switchYN(userid);
		}
		response.sendRedirect("gshop.do?command=adminIndex");
		}
	}
}

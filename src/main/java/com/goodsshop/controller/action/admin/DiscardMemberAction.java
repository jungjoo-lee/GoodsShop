package com.goodsshop.controller.action.admin;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.AdminDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DiscardMemberAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO dao = AdminDAO.getInstance();
		String [] YNlist = request.getParameterValues("YN");
		
		if(YNlist==null) {
		response.sendRedirect("gshop.do?command=adminIndex");
		}else {
		for (String userid : YNlist) {
			dao.여기 마저 써라(userid);
		}
		response.sendRedirect("gshop.do?command=adminIndex");
		}
	}

}

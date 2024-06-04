package com.goodsshop.controller.action.admin;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.AdminDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NoticeDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO dao = AdminDAO.getInstance();
		int nseq = Integer.parseInt(request.getParameter("nseq"));
		
		dao.deleteNotice(nseq);
		
		response.sendRedirect("gshop.do?command=adminNoticeList");
	}

}

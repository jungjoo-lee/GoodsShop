package com.goodsshop.controller.action.admin;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.AdminDAO;
import com.goodsshop.dao.NoticeDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminNoticeViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDAO dao = NoticeDAO.getInstance();
		
		request.setAttribute("vo", dao.getNotice(Integer.parseInt(request.getParameter("nseq"))));
		request.getRequestDispatcher("/WEB-INF/jsp/notice/noticeView.jsp").forward(request, response);

	}

}

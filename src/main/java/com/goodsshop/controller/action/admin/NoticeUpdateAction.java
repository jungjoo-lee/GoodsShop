package com.goodsshop.controller.action.admin;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.AdminDAO;
import com.goodsshop.dto.NoticeVO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class NoticeUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO dao = AdminDAO.getInstance();
		NoticeVO vo = new NoticeVO();
		vo.setAdminId(request.getParameter("adminId"));
		vo.setSubject(request.getParameter("subject"));
		vo.setContent(request.getParameter("content"));
		HttpSession session = request.getSession();
	    ServletContext context = session.getServletContext();
	      
		dao.updateNotice(vo);

		request.getRequestDispatcher("/WEB-INF/jsp/notice/noticeUpdate.jsp").forward(request, response);

	}

}

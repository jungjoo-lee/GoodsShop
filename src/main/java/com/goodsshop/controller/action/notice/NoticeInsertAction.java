package com.goodsshop.controller.action.notice;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.NoticeDAO;
import com.goodsshop.dto.AdminVO;
import com.goodsshop.dto.NoticeVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class NoticeInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDAO nDao = NoticeDAO.getInstance();
		NoticeVO vo =  new NoticeVO();
		HttpSession session = request.getSession();
		AdminVO  avo = new AdminVO();
		avo = (AdminVO)session.getAttribute("loginAdmin");
		vo.setSubject(request.getParameter("subject"));
		vo.setContent(request.getParameter("content"));
		vo.setAdminId(avo.getAdminId());

		
		nDao.insertNotice(vo);
		
		 response.sendRedirect(request.getContextPath() + "/gshop.do?command=adminNoticeList");

	}

}

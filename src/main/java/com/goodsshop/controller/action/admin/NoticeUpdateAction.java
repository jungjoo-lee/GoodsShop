package com.goodsshop.controller.action.admin;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.NoticeDAO;
import com.goodsshop.dto.NoticeVO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class NoticeUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDAO dao = NoticeDAO.getInstance();
		NoticeVO vo = new NoticeVO();
		 int nseq = Integer.parseInt(request.getParameter("nseq")); // nseq를 정수로 변환하여 저장
	     vo.setNseq(nseq);
	     vo.setAdminId(request.getParameter("adminId"));
		vo.setSubject(request.getParameter("subject"));
		vo.setContent(request.getParameter("content"));
		
		   HttpSession session = request.getSession();
		dao.updateNotice(vo);
		session.setAttribute("vo", vo);
		
		 response.sendRedirect(request.getContextPath() + "/gshop.do?command=adminNoticeList");

	}

}

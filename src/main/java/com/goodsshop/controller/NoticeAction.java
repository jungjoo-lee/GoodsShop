package com.goodsshop.controller;

import java.io.IOException;

import com.goodsshop.dao.AdminDAO;
import com.goodsshop.dao.NoticeDAO;
import com.goodsshop.dto.AdminVO;
import com.goodsshop.dto.NoticeVO;
import com.goodsshop.util.Paging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class NoticeAction {
	public String noticeList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		NoticeDAO dao = NoticeDAO.getInstance();

		int total = dao.getTotalNotice();
		int currentPage = 1;
		int amount = 10;
		if (session.getAttribute("currentPage") != null) {
			currentPage = (Integer) session.getAttribute("currentPage");
		}
		if (session.getAttribute("amount") != null) {
			amount = (Integer) session.getAttribute("amount");
		}
		Paging paging = new Paging(currentPage, amount, total);
		request.setAttribute("noticeList", dao.getNoticeList(paging.getAmount(), paging.getCurrentPage()));
		request.setAttribute("paging", paging);

		return "/notice/noticeList.jsp";
	}

	public void noticeInsert(HttpServletRequest request, HttpServletResponse response) throws IOException {
		NoticeDAO nDao = NoticeDAO.getInstance();
		NoticeVO vo = new NoticeVO();
		HttpSession session = request.getSession();
		AdminVO avo = new AdminVO();
		avo = (AdminVO) session.getAttribute("loginAdmin");
		vo.setSubject(request.getParameter("subject"));
		vo.setContent(request.getParameter("content"));
		vo.setAdminId(avo.getAdminId());

		nDao.insertNotice(vo);
		response.sendRedirect("/adminNoticeList.do");
	}

	public void noticeDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AdminDAO dao = AdminDAO.getInstance();
		int nseq = Integer.parseInt(request.getParameter("nseq"));

		dao.deleteNotice(nseq);

		response.sendRedirect("/adminNoticeList.do");
	}

	public void noticeUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		NoticeDAO dao = NoticeDAO.getInstance();
		NoticeVO vo = new NoticeVO();
		int nseq = Integer.parseInt(request.getParameter("nseq")); // nseq를 정수로 변환하여 저장
		vo.setNseq(nseq);
		vo.setAdminId(request.getParameter("adminId"));
		vo.setSubject(request.getParameter("subject"));
		vo.setContent(request.getParameter("content"));
	
		dao.updateNotice(vo);
	
		response.sendRedirect("/adminNoticeView.do&nseq=" + vo.getNseq());
	}

	public String noticeUpdateForm(HttpServletRequest request, HttpServletResponse response) {
		int nseq = Integer.parseInt(request.getParameter("nseq"));
		NoticeDAO ndao= NoticeDAO.getInstance();
		
		// 게시물 상세 조회
		NoticeVO vo = ndao.getNotice(nseq);
		
		request.setAttribute("vo", vo);
		return "/notice/noticeUpdateForm.jsp";
	}

	public String noticeInsertForm(HttpServletRequest request, HttpServletResponse response) {
		return "/notice/noticeInsertForm.jsp";
	}

	public String noticeView(HttpServletRequest request, HttpServletResponse response) {
		NoticeDAO dao = NoticeDAO.getInstance();
		
		request.setAttribute("vo", dao.getNotice(Integer.parseInt(request.getParameter("nseq"))));
		return "/notice/noticeView.jsp";
	}
}

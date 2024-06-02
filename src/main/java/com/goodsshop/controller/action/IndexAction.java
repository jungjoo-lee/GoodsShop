package com.goodsshop.controller.action;

import java.io.IOException;

import com.goodsshop.dao.NoticeDAO;
import com.goodsshop.dao.QnaDAO;
import com.goodsshop.dao.ReviewDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IndexAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDAO nDAO = NoticeDAO.getInstance();
		QnaDAO qDAO = QnaDAO.getInstance();
		ReviewDAO rDAO = ReviewDAO.getInstance();
		
		request.setAttribute("noticeList", nDAO.getMainNoticeList());
		request.setAttribute("qnaList", qDAO.getMainQnaList());
		request.setAttribute("reviewList", rDAO.getMainReviewList());
		
		request.getRequestDispatcher("WEB-INF/jsp/index/index.jsp").forward(request, response);
	}
}
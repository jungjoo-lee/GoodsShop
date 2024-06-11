package com.goodsshop.controller.action.notice;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.AdminDAO;
import com.goodsshop.dao.NoticeDAO;
import com.goodsshop.dto.NoticeVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NoticeUpdateFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nseq = Integer.parseInt(request.getParameter("nseq"));
		NoticeDAO ndao= NoticeDAO.getInstance();
		
		// 게시물 상세 조회
		NoticeVO vo = ndao.getNotice(nseq);
		
		request.setAttribute("vo", vo);
		request.getRequestDispatcher("/WEB-INF/jsp/notice/noticeUpdateForm.jsp").forward(request, response);
	}
}

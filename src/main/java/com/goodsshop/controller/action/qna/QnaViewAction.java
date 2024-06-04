package com.goodsshop.controller.action.qna;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.QnaDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaViewAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qseq = Integer.parseInt(request.getParameter("qseq"));
		QnaDAO dao = QnaDAO.getInstance();
		
		request.setAttribute("vo", dao.getQna(qseq));
		
		request.getRequestDispatcher("WEB-INF/jsp/qna/qnaView.jsp").forward(request, response);
	}
}
package com.goodsshop.controller.action.qna;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.QnaDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaWriteFormAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameterMap().containsKey("qseq")) {
			QnaDAO dao = QnaDAO.getInstance();
			request.setAttribute("vo", dao.getQna(Integer.parseInt(request.getParameter("qseq"))));
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/qna/qnaWriteFom.jsp").forward(request, response);
	}
}
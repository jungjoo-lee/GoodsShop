package com.goodsshop.controller.action.qna;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.QnaDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QnaDAO dao = QnaDAO.getInstance();
		
		request.setAttribute("qnaList", dao.getQnaList());
		
		request.getRequestDispatcher("/WEB-INF/jsp/qna/qnaList.jsp").forward(request, response);
	}
}
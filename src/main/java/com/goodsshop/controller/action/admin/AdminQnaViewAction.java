package com.goodsshop.controller.action.admin;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.QnaDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminQnaViewAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QnaDAO dao = QnaDAO.getInstance();
		
		request.setAttribute("vo", dao.getQna(Integer.parseInt(request.getParameter("qseq"))));
		request.getRequestDispatcher("/WEB-INF/jsp/admin/qnaView.jsp").forward(request, response);
	}
}
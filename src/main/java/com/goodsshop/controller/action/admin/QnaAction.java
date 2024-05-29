package com.goodsshop.controller.action.admin;

import java.io.IOException;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.AdminDAO;
import com.goodsshop.dto.QnaVO;
import com.goodsshop.util.Paging;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<QnaVO> qnaList = null;
		AdminDAO dao = AdminDAO.getInstance();
		
		int total = dao.getTotalQna();
		int currentPage = 1;
		int amount = 10;
		Paging paging = new Paging(currentPage, amount, total);
		
		request.setAttribute("qnaList", dao.getQnaList(paging.getAmount(), paging.getCurrentPage()));
		request.setAttribute("paging", paging);
		request.getRequestDispatcher("/WEB-INF/jsp/admin/qna.jsp").forward(request, response);
	}
}
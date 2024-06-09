package com.goodsshop.controller.action.qna;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.QnaDAO;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.util.Paging;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		QnaDAO dao = QnaDAO.getInstance();
		
		int total = dao.getTotalQna();
		int currentPage = 1;
		int amount = 10;
		if(session.getAttribute("currentPage") != null) {
			currentPage = (Integer)session.getAttribute("currentPage");
		}
		if(session.getAttribute("amount") != null) {
			amount = (Integer)session.getAttribute("amount");
		}
		Paging paging = new Paging(currentPage, amount, total);
		request.setAttribute("qnaList", dao.getQnaList(paging.getAmount(), paging.getCurrentPage()));
		request.setAttribute("paging", paging);
		
		if (session.getAttribute("loginUser") != null) {
			MemberVO vo = (MemberVO)session.getAttribute("loginUser");
			
			int myCurrentPage = 1;
			int myTotal = dao.getTotalMyQna(vo.getUserid());
			if(session.getAttribute("currentPage") != null) {
				currentPage = (Integer)session.getAttribute("currentPage");
			}
			Paging myPaging = new Paging(myCurrentPage, amount, myTotal);
			request.setAttribute("qnaMyList", dao.getMyQnaList(myPaging.getAmount(), myPaging.getCurrentPage(), vo.getUserid()));
			request.setAttribute("myPaging", myPaging);
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/qna/qnaList.jsp").forward(request, response);
	}
}
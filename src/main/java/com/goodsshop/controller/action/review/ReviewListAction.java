package com.goodsshop.controller.action.review;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.ReviewDAO;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.util.Paging;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ReviewListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ReviewDAO dao = ReviewDAO.getInstance();
		MemberVO vo = (MemberVO)session.getAttribute("loginUser");
		
		int total = dao.getTotalReview();
		int currentPage = 1;
		int amount = 5;
		if(session.getAttribute("currentPage") != null) {
			currentPage = (Integer)session.getAttribute("currentPage");
		}
		if(session.getAttribute("amount") != null) {
			amount = (Integer)session.getAttribute("amount");
		}
		Paging paging = new Paging(currentPage, amount, total);
		request.setAttribute("reviewList", dao.getReviewList(paging.getAmount(), paging.getCurrentPage()));
		request.setAttribute("paging", paging);
		
		int myTotal = dao.getTotalMyReview(vo.getUserid());
		int myCurrentPage = 1;
		if(session.getAttribute("myCurrentPage") != null) {
			myCurrentPage = (Integer)session.getAttribute("myCurrentPage");
		}
		Paging myPaging = new Paging(myCurrentPage, amount, myTotal);
		request.setAttribute("reviewMyList", dao.getReviewMyList(myPaging.getAmount(), myPaging.getCurrentPage(), vo.getUserid()));
		request.setAttribute("myPaging", myPaging);
		
		request.getRequestDispatcher("/WEB-INF/jsp/review/reviewList.jsp").forward(request, response);
	}
}
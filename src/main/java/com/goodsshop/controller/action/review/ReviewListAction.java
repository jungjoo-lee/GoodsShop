package com.goodsshop.controller.action.review;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.ReviewDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReviewListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewDAO dao = ReviewDAO.getInstance();
		
		request.setAttribute("reviewList", dao.getReviewList());
		
		request.getRequestDispatcher("/WEB-INF/jsp/review/reviewList.jsp").forward(request, response);
	}
}
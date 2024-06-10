package com.goodsshop.controller.action.goods;

import java.io.IOException;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dao.ReviewDAO;
import com.goodsshop.dto.GoodsImageVO;
import com.goodsshop.dto.GoodsVO;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.dto.ReviewVO;
import com.goodsshop.util.Paging;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoodsDetailViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		int gseq = Integer.parseInt(request.getParameter("gseq"));
		
		GoodsDAO gdao = new GoodsDAO();
		GoodsVO gvo = gdao.getGoods(gseq);
		List<GoodsImageVO> image = gdao.getImageList(gseq);
		gvo.setImageList(image);
		String thum = gdao.getThumbnail(gseq);
		gvo.setThum(thum);
		
		if (loginUser != null) {
			int oldPrice = gvo.getSprice();		
			int newPrice = 0;
			
			newPrice = (int)Math.ceil(oldPrice - (oldPrice * loginUser.getSale()));
			
			gvo.setSprice(newPrice);
		}	
		
		ReviewDAO rDAO = ReviewDAO.getInstance();
		int total = rDAO.getGoodsReviewTotal(gseq);
		int currentPage = 1;
		
		Paging paging = new Paging(currentPage, 10, total);
		List<ReviewVO> reviewList =  rDAO.getGoodsReviewList(gseq, 10, paging.getCurrentPage());

		request.setAttribute("goodsDetail", gvo);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("paging", paging);
		request.getRequestDispatcher("/WEB-INF/jsp/goods/goodsDetail.jsp").forward(request, response);
	}
}

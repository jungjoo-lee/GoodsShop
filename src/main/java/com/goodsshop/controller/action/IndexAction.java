package com.goodsshop.controller.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.goodsshop.dao.NoticeDAO;
import com.goodsshop.dao.QnaDAO;
import com.goodsshop.dao.ReviewDAO;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dto.CartVO;
import com.goodsshop.dto.GoodsImageVO;
import com.goodsshop.dto.GoodsVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class IndexAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDAO nDAO = NoticeDAO.getInstance();
		QnaDAO qDAO = QnaDAO.getInstance();
		ReviewDAO rDAO = ReviewDAO.getInstance();
		
		request.setAttribute("noticeList", nDAO.getMainNoticeList());
		request.setAttribute("qnaList", qDAO.getMainQnaList());
		request.setAttribute("reviewList", rDAO.getMainReviewList());
		
		HttpSession session = request.getSession();
		
		
		for(GoodsVO vo : bestlist) {
			GoodsDAO gdao1 = new GoodsDAO();
			List<GoodsImageVO> bestImageList = gdao1.getImageList(vo.getGseq());
			vo.setImageList(bestImageList);
			String thum = gdao1.getThumbnail(vo.getGseq());
			vo.setThum(thum);		
		}
		
		List<GoodsVO> newlist = gdao.getNewList();
		
		for(GoodsVO vo : newlist) {
			GoodsDAO gdao1 = new GoodsDAO();
			List<GoodsImageVO> newImageList = gdao1.getImageList(vo.getGseq());
			vo.setImageList(newImageList);
			String thum = gdao1.getThumbnail(vo.getGseq());
			vo.setThum(thum);		
		}
		
		List<CartVO> cartlist = new ArrayList<CartVO>();
		
		
		
		request.setAttribute("bestlist", bestlist);
		request.setAttribute("newlist", newlist);
		
		if(request.getAttribute("loginUser") != null) {
			session.setAttribute("cartlist", cartlist);			
		}
		request.getRequestDispatcher("WEB-INF/jsp/index/index.jsp").forward(request, response);
	}
}
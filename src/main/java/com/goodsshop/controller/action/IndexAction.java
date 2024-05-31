package com.goodsshop.controller.action;

import java.io.IOException;
import java.util.List;

import com.goodsshop.controller.action.goods.dao.GoodsDAO;
import com.goodsshop.controller.action.goods.dto.GoodsImageVO;
import com.goodsshop.controller.action.goods.dto.GoodsVO;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IndexAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GoodsDAO gdao = new GoodsDAO();	
		List<GoodsVO> bestlist = gdao.getBestList();
		
		
		for(GoodsVO vo : bestlist) {
			GoodsDAO gdao2 = new GoodsDAO();
			List<GoodsImageVO> bestImageList = gdao2.getImageList(vo.getGseq());
			vo.setImageList(bestImageList);
			
			System.out.println(vo);
		}
		
		List<GoodsVO> newlist = gdao.getNewList();
		
		for(GoodsVO vo : newlist) {
			GoodsDAO gdao2 = new GoodsDAO();
			List<GoodsImageVO> newImageList = gdao2.getImageList(vo.getGseq());
			vo.setImageList(newImageList);
		}
		
		
		
		request.setAttribute("bestlist", bestlist);
		request.setAttribute("newlist", newlist);
		request.getRequestDispatcher("WEB-INF/jsp/main.jsp").forward(request, response);
		
	}
}
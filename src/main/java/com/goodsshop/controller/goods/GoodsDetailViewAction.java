package com.goodsshop.controller.goods;

import java.io.IOException;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dto.GoodsImageVO;
import com.goodsshop.dto.GoodsVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoodsDetailViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int gseq = Integer.parseInt(request.getParameter("gseq"));
		
		GoodsDAO gdao = new GoodsDAO();
		GoodsVO gvo = gdao.getGoods(gseq);
		List<GoodsImageVO> image = gdao.getImageList(gseq);
		gvo.setImageList(image);
		String thum = gdao.getThumbnail(gseq);
		gvo.setThum(thum);
		System.out.println("thum : "+thum);
		
		
		request.setAttribute("goodsDetail", gvo);
		request.getRequestDispatcher("jsp/goods/goodsDetail.jsp").forward(request, response);
	}

}

package com.goodsshop.controller.action.admin.goods;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.GoodsDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminBestGoodsAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] gseqs = request.getParameterValues("gseq");
		GoodsDAO gdao = new GoodsDAO();
		
		for (String gseq : gseqs) {
			gdao.bestToggle(gseq);
		}
		
		response.sendRedirect("gshop.do?command=adminGoodsView");

	}

}

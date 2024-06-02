package com.goodsshop.controller;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.controller.member.MemberVO;
import com.goodsshop.dao.CartDAO;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dto.CartVO;
import com.goodsshop.dto.GoodsVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteWishAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String [] gseqs = request.getParameterValues("gseq");
				
		for(String gseq : gseqs) {
			int gseqInt = Integer.parseInt(gseq);
			
			CartDAO cdao = new CartDAO();
			cdao.deleteWish(gseqInt);
			
		}			

		response.sendRedirect("gshop.do?command=viewWishlist");
		
	}
}

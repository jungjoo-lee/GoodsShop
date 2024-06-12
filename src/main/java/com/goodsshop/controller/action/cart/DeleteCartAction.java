package com.goodsshop.controller.action.cart;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dto.CartVO;
import com.goodsshop.dto.GoodsVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteCartAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		List<CartVO> cartlist = (List<CartVO>)session.getAttribute("cartlist");
		
		String [] gseqs = request.getParameterValues("gseq");
		
		
		for(String gseq : gseqs) {
			int gseqInt = Integer.parseInt(gseq);
			
			CartVO deleteTo = cartlist.stream()
					.filter(obj -> obj.getGseq() == gseqInt)
					.findFirst().orElse(null);
			
			cartlist.remove(deleteTo);
			
		}	
		
		
		session.setAttribute("cartlist", cartlist);
		response.sendRedirect("gshop.do?command=viewCartlist");	

	}

}

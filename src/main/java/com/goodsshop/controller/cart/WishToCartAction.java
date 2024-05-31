package com.goodsshop.controller.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class WishToCartAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		List<CartVO> cartlist = new ArrayList<CartVO>();
		
		
		String [] gseqs = request.getParameterValues("gseq");
		
		for(String gseq : gseqs) {
			
			int gseqInt = Integer.parseInt(gseq);
			
			GoodsDAO gdao = new GoodsDAO();
			GoodsVO gvo = gdao.getGoods(gseqInt);

			CartVO cvo = new CartVO();
			cvo.setUserid(mvo.getUserid());
			cvo.setUsername(mvo.getName());
			cvo.setQuantity(1);
			cvo.setGseq(gvo.getGseq());
			cvo.setGoodsname(gvo.getGname());
			cvo.setSprice(gvo.getSprice());
			cvo.setTotalprice(gvo.getSprice());
			cvo.setThum(gdao.getThumbnail(gseqInt));
			
			cartlist.add(cvo);		
			
			CartDAO cdao = new CartDAO();
			cdao.deleteFromWish(gseqInt);
			
		}	
		
		
		session.setAttribute("cartlist", cartlist);
		response.sendRedirect("gshop.do?command=viewCartlist");
	}

}

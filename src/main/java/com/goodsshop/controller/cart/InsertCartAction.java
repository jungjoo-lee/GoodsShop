package com.goodsshop.controller.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.controller.member.MemberVO;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dto.CartVO;
import com.goodsshop.dto.GoodsVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class InsertCartAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
	
		HttpSession session = request.getSession();
		List<CartVO> cartlist = null;
		
		if (session.getAttribute("cartlist") == null) {
			cartlist = new ArrayList<CartVO>();
		} else {
			cartlist = (List<CartVO>)session.getAttribute("cartlist");
		}
		
		
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		int quantity = Integer.parseInt(request.getParameter("input_quantity")) ;
		int gseq = Integer.parseInt(request.getParameter("gseq"));
		
		GoodsDAO gdao = new GoodsDAO();
		GoodsVO gvo = gdao.getGoods(gseq);

		CartVO cvo = new CartVO();
		cvo.setUserid(mvo.getUserid());
		cvo.setUsername(mvo.getName());
		cvo.setQuantity(quantity);
		cvo.setGseq(gvo.getGseq());
		cvo.setGoodsname(gvo.getGname());
		cvo.setSprice(gvo.getSprice());
		cvo.setTotalprice(gvo.getSprice() * quantity);
		cvo.setThum(gdao.getThumbnail(gseq));
		

		cartlist.add(cvo);

		session.setAttribute("cartlist", cartlist);
		
		response.sendRedirect("gshop.do?command=index");
	}
}

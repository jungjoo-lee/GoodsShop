package com.goodsshop.controller.action.cart;

import java.io.IOException;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dao.OrderDAO;
import com.goodsshop.dto.CartVO;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.dto.OrderVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoOrderAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		List<OrderVO> orderProductList = (List<OrderVO>)session.getAttribute("orderProductList");
		List<CartVO> cartlist = (List<CartVO>)session.getAttribute("cartlist");
		
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		OrderDAO odao = new OrderDAO();
		
		odao.insertOrder(mvo.getUserid());
		
		int oseq = odao.lookupMaxOseq(mvo.getUserid());
		
		
		for(OrderVO ovo : orderProductList) {
			OrderDAO odao1 = new OrderDAO();
			GoodsDAO gdao = new GoodsDAO();
			odao1.insertOrderDetail(ovo, oseq);
			
			
			if(cartlist != null) {
				CartVO deleteTo = cartlist.stream()
						.filter(obj -> obj.getGseq() == ovo.getGseq())
						.findFirst().orElse(null);
				
				cartlist.remove(deleteTo);	
			}
	
		}
		

		session.setAttribute("cartlist", cartlist);
		session.removeAttribute("orderProductList");
		response.sendRedirect("gshop.do?command=viewOrderList");

	}
}

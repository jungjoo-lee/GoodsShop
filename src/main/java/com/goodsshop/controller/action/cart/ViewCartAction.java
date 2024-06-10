package com.goodsshop.controller.action.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dto.CartVO;
import com.goodsshop.dto.GoodsVO;
import com.goodsshop.dto.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ViewCartAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		List<CartVO> cartlist = (List<CartVO>)session.getAttribute("cartlist");
		PrintWriter out = response.getWriter();

		if (loginUser == null) {		
			out.print("<script>alert('로그인을 먼저 진행해주세요');</script>");
			out.print("<script>location.href='gshop.do?command=loginForm';</script>");
		} else {
			
			if(cartlist != null) {
				for(CartVO cvo : cartlist) {
					int oldPrice = cvo.getSprice();		
					int newPrice = 0;
					newPrice = (int)Math.ceil(oldPrice - (oldPrice * loginUser.getSale()));
					
					cvo.setSprice(newPrice);
					cvo.setTotalprice(cvo.getQuantity() * cvo.getSprice());
					
					GoodsDAO gdao = new GoodsDAO();
					GoodsVO gvo = gdao.getGoods(cvo.getGseq());
					
					cvo.setRealname(gvo.getRealname());
					
				}
				session.setAttribute("cartlist", cartlist);
			}
			request.getRequestDispatcher("WEB-INF/jsp/goods/cartlistView.jsp").forward(request, response);
		}
	}
}

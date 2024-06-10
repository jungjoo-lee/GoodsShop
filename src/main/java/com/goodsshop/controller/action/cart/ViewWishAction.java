package com.goodsshop.controller.action.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.CartDAO;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dto.CartVO;
import com.goodsshop.dto.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ViewWishAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();	
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		CartDAO cdao = new CartDAO();

		if (loginUser == null) {
			PrintWriter out = response.getWriter();			
			out.print("<script>alert('로그인을 먼저 진행해주세요');</script>");
			out.print("<script>location.href='gshop.do?command=loginForm';</script>");
		} else {
			
			List<CartVO> wishlist = null;
			String userid = loginUser.getUserid();
			
			wishlist = cdao.getWishList(userid);
			
			for( CartVO cvo : wishlist) {				
				int oldPrice = cvo.getSprice();		
				int newPrice = 0;
				newPrice = (int)Math.ceil(oldPrice - (oldPrice * loginUser.getSale()));
				
				cvo.setSprice(newPrice);
			}
			
			request.setAttribute("wishlist", wishlist);
			request.getRequestDispatcher("/WEB-INF/jsp/goods/wishlistView.jsp").forward(request, response);
						
		}

	}

}

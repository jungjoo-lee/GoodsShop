package com.goodsshop.controller.action.cart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dto.GoodsVO;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.dto.OrderVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class OrderNowAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		if (mvo == null) {
			PrintWriter out = response.getWriter();			
			out.print("<script>alert('로그인을 먼저 진행해주세요');</script>");
			out.print("<script>location.href='gshop.do?command=loginForm';</script>");
		} else {
		
			int gseq = Integer.parseInt(request.getParameter("gseq")); 
			GoodsDAO gdao = new GoodsDAO();
			GoodsVO gvo = gdao.getGoods(gseq);
			gvo.setThum(gdao.getThumbnail(gseq));
			
			
			
			List<OrderVO> list = new ArrayList<OrderVO>();
			
			OrderVO ovo = new OrderVO();
	
			ovo.setUserid(mvo.getUserid());
			ovo.setName(mvo.getName());
			ovo.setZipcode(mvo.getZip_code());
			ovo.setAddress(mvo.getAddress());
			ovo.setDaddress(mvo.getD_address());
			ovo.setPhone(mvo.getPhone());
			ovo.setGseq(gseq);
			ovo.setGname(gvo.getGname());
			ovo.setQuantity(1);
			ovo.setRealname(gvo.getRealname());
			
			int oldPrice = gvo.getSprice();		
			int newPrice = 0;
			
			newPrice = (int)Math.ceil(oldPrice - (oldPrice * mvo.getSale()));
			
			gvo.setSprice(newPrice);
			
			ovo.setTotalprice(gvo.getSprice() * 1);
			
			
			list.add(ovo);
			
			session.setAttribute("orderProductList", list);
			request.getRequestDispatcher("/WEB-INF/jsp/goods/orderPage.jsp").forward(request, response);
		}

	}

}

package com.goodsshop.controller.action.cart;

import java.io.IOException;
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

public class OrderCartAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		String[] gseqs = request.getParameterValues("gseq");
		String[] quantity = request.getParameterValues("quantity");		
		
		List<OrderVO> list = new ArrayList<OrderVO>();
		
		
		for(int i = 0; i<gseqs.length; i++) {
			int gseqInt = Integer.parseInt(gseqs[i]);
			
			GoodsDAO gdao = new GoodsDAO();
			GoodsVO gvo = gdao.getGoods(gseqInt);
			gvo.setThum(gdao.getThumbnail(gseqInt));		
			
			OrderVO ovo = new OrderVO();
			ovo.setUserid(mvo.getUserid());
			ovo.setName(mvo.getName());
			ovo.setZipcode(mvo.getZip_code());
			ovo.setAddress(mvo.getAddress());
			ovo.setDaddress(mvo.getD_address());
			ovo.setPhone(mvo.getPhone());
			ovo.setGseq(gseqInt);
			ovo.setGname(gvo.getGname());
			ovo.setQuantity(Integer.parseInt(quantity[i]));
			
			int oldPrice = gvo.getSprice();		
			int newPrice = 0;
			
			newPrice = (int)Math.ceil(oldPrice - (oldPrice * mvo.getSale()));
			
			gvo.setSprice(newPrice);
			
			ovo.setTotalprice(gvo.getSprice() * 1);
			ovo.setThum(gdao.getThumbnail(gseqInt));
			
			list.add(ovo);		
		}
	
		session.setAttribute("orderProductList", list);
		request.getRequestDispatcher("jsp/goods/orderPage.jsp").forward(request, response);
		
		
	}
}

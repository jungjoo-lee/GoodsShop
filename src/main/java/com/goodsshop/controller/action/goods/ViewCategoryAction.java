package com.goodsshop.controller.action.goods;

import java.io.IOException;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dto.GoodsImageVO;
import com.goodsshop.dto.GoodsVO;
import com.goodsshop.dto.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ViewCategoryAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cgseq = Integer.parseInt(request.getParameter("cgseq"));
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		

		GoodsDAO gdao = new GoodsDAO();
		List<GoodsVO> categoryList = gdao.getCategoryList(cgseq);
		
		
		for(GoodsVO vo : categoryList) {
			GoodsDAO gdao1 = new GoodsDAO();
			
			List<GoodsImageVO> categoryImageList = gdao1.getImageList(vo.getGseq());
			vo.setImageList(categoryImageList);
			String thum = gdao1.getThumbnail(vo.getGseq());
			vo.setThum(thum);
			int oldPrice = vo.getSprice();		
			int newPrice = 0;
			
			newPrice = (int)Math.ceil(oldPrice - (oldPrice * loginUser.getSale()));
			
			vo.setSprice(newPrice);
		}
		
		
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("WEB-INF/jsp/goods/categoryView.jsp").forward(request, response);
		
	}

}

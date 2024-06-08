package com.goodsshop.controller.action.goods;

import java.io.IOException;
import java.util.ArrayList;
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
		
		String cgseq = request.getParameter("cgseq");
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		int page = 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
		} else if(session.getAttribute("page") != null) {
			page = (Integer)session.getAttribute("page");
		} else {
			session.removeAttribute("page");
		}
		
		MPaging paging = new MPaging();
		paging.setPage(page);
		paging.setDisplayRow(20);
		
		GoodsDAO gdao = new GoodsDAO();
		int count = gdao.getAllCount("cgseq", cgseq);
		paging.setTotalCount(count);
		
		List<GoodsVO> categoryList = new ArrayList<GoodsVO>();
		
		if(cgseq.equals("0")) {
			//best 50
			categoryList = gdao.getBestList();					
		} else {
			//나머지 카테고리들
			categoryList = gdao.getCategoryList(cgseq, paging);			
		}
				
		if (loginUser != null) {
		
			for (GoodsVO vo : categoryList) {
				GoodsDAO gdao1 = new GoodsDAO();

				List<GoodsImageVO> categoryImageList = gdao1.getImageList(vo.getGseq());
				vo.setImageList(categoryImageList);

				// 사용자별 가격표시
				int oldPrice = vo.getSprice();
				int newPrice = 0;

				newPrice = (int) Math.ceil(oldPrice - (oldPrice * loginUser.getSale()));

				vo.setSprice(newPrice);
			}
		}
		
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("paging", paging);			
		request.getRequestDispatcher("WEB-INF/jsp/goods/categoryView.jsp").forward(request, response);
		
	}

}

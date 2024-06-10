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

public class SearchGoodsAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String key = request.getParameter("key");
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
		int count = gdao.getAllCount("gseq", "");
		paging.setTotalCount(count);
		
		List<GoodsVO> searchList = new ArrayList<GoodsVO>();
		searchList = gdao.getAllGoods(key, paging);
				

		
		for (GoodsVO vo : searchList) {
				GoodsDAO gdao1 = new GoodsDAO();

				List<GoodsImageVO> searchImageList = gdao1.getImageList(vo.getGseq());
				vo.setImageList(searchImageList);

				// 사용자별 가격표시
				if (loginUser != null) {
					int oldPrice = vo.getSprice();		
					int newPrice = 0;
					
					newPrice = (int)Math.ceil(oldPrice - (oldPrice * loginUser.getSale()));
					
					vo.setSprice(newPrice);
				}
		}

		
		request.setAttribute("categoryList", searchList);
		request.setAttribute("paging", paging);			
		request.getRequestDispatcher("WEB-INF/jsp/goods/categoryView.jsp").forward(request, response);		

	}

}

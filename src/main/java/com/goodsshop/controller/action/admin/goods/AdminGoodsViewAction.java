package com.goodsshop.controller.action.admin.goods;

import java.io.IOException;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dto.AdminVO;
import com.goodsshop.dto.GoodsImageVO;
import com.goodsshop.dto.GoodsVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminGoodsViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminVO loginAdmin = (AdminVO)session.getAttribute("loginAdmin");
		String url = "";
		
		if(loginAdmin == null) {
			url = "gshop.do?command=adminLoginForm";
		} else {
			
			GoodsDAO gdao = new GoodsDAO();
			List<GoodsVO> adminGoodsList = gdao.getAllGoods("");
			
			for (GoodsVO gvo : adminGoodsList) {
				GoodsDAO gdao1 = new GoodsDAO();
				List<GoodsImageVO> bestImageList = gdao1.getImageList(gvo.getGseq());
				gvo.setImageList(bestImageList);
			}
			
			request.setAttribute("adminGoodsList", adminGoodsList);	
			request.setAttribute("loginAdmin", loginAdmin);
			
			url = "WEB-INF/jsp/admin/adminGoodsView.jsp";			
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}

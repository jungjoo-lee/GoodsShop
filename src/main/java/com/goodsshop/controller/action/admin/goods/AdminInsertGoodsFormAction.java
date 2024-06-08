package com.goodsshop.controller.action.admin.goods;

import java.io.IOException;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dto.AdminVO;
import com.goodsshop.dto.GoodsVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminInsertGoodsFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminVO loginAdmin = (AdminVO)session.getAttribute("loginAdmin");
		String url = "";
		
		if(loginAdmin == null) {
			url = "gshop.do?command=adminLoginForm";		
		} else {		
			
			GoodsDAO gdao = new GoodsDAO();
			List<GoodsVO> categoryList = gdao.getAllCategories();
			request.setAttribute("categoryList", categoryList);			
			
			url = "WEB-INF/jsp/admin/adminGoodsWriteForm.jsp";
		}	
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}

package com.goodsshop.controller.action.admin;

import org.json.JSONObject;

import com.goodsshop.dao.AdminDAO;
import com.goodsshop.util.Paging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PageInfoAction {
	public JSONObject pageInfo(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonResult = new JSONObject();
		AdminDAO dao = AdminDAO.getInstance();
		
		int total = dao.getTotalQna();
		int currentPage = 1;
		int amount = 10;;
		Paging paging = new Paging(currentPage, amount, total);
		
		ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(pageDTO);
		
		
		return jsonResult;
	}
}

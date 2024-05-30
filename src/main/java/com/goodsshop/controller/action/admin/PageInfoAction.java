package com.goodsshop.controller.action.admin;

import java.io.IOException;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodsshop.controller.action.FatchAction;
import com.goodsshop.dao.AdminDAO;
import com.goodsshop.util.Paging;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PageInfoAction implements FatchAction {
	@Override
	public JSONObject execute(HttpServletRequest request, HttpServletResponse response, JSONObject json)	throws ServletException, IOException {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		AdminDAO dao = AdminDAO.getInstance();

		int total = dao.getTotalQna();
		int currentPage = 1;
		int amount = 10;
		
		if(session.getAttribute("currentPage") != null) {
			currentPage = (Integer)session.getAttribute("currentPage");
		}
		if(session.getAttribute("amount") != null) {
			amount = (Integer)session.getAttribute("amount");
		}
		
		Paging paging = new Paging(currentPage, amount, total);
		
		ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(paging);
		
        try {			
			jsonResult.put("status", true);
			jsonResult.put("paging", new JSONObject(jsonString));
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}
		
		return jsonResult;
	}	
}
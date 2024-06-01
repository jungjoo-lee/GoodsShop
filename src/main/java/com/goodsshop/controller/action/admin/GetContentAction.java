package com.goodsshop.controller.action.admin;

import java.io.IOException;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodsshop.controller.action.FatchAction;
import com.goodsshop.dao.AdminDAO;
import com.goodsshop.util.Paging;
import com.goodsshop.util.SqlBuilder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GetContentAction implements FatchAction {
	@Override
	public JSONObject execute(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws ServletException, IOException {
		HttpSession session = request.getSession();
		json.remove("command");
		
		AdminDAO dao = AdminDAO.getInstance();
		SqlBuilder sb = SqlBuilder.getInstance();
		StringBuilder sql = sb.build(0, json);

		int total = dao.getTotalQna(sql.toString());
		System.out.println(total);
		int currentPage = json.getInt("page");
		int amount = json.getInt("amount");
		Paging paging = new Paging(currentPage, amount, total);
		
		session.setAttribute("currentPage", paging.getCurrentPage());
		session.setAttribute("amount", paging.getAmount());
		
		json.put("page", paging.getCurrentPage());
		json.put("amount", paging.getAmount());
		
		ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(paging);
        JSONObject jsonResult = new JSONObject();
        sql = sb.build(1, json);
        
        try {			
			jsonResult.put("status", true);
			jsonResult.put("content", dao.getQnaList(sql.toString()));
			jsonResult.put("paging", new JSONObject(jsonString));
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}
		
		return jsonResult;
	}
}
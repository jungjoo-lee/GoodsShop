package com.goodsshop.controller.action.admin;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodsshop.controller.action.FatchAction;
import com.goodsshop.dao.AdminDAO;
import com.goodsshop.dao.ReviewDAO;
import com.goodsshop.util.Paging;
import com.goodsshop.util.SqlBuilder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GetContentAction implements FatchAction {
	@Override
	public JSONObject execute(HttpServletRequest request, HttpServletResponse response, JSONObject json) {
		json.remove("command");
		JSONObject jsonResult = new JSONObject();
		
		switch ((String)json.get("table")) {
			case "qna" -> jsonResult = returnQna(request, json);
			case "review_view" -> jsonResult = returnReview(request, json);
			case "notice" -> jsonResult = returnNotice(request, json);
			case "member" -> jsonResult = returnMember(request, json);
		}
		
		return jsonResult;
	}
	
	private JSONObject returnQna(HttpServletRequest request, JSONObject json) {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		SqlBuilder sb = SqlBuilder.getInstance();
		AdminDAO dao = AdminDAO.getInstance();
		
		StringBuilder sql = null;
		String jsonString = null;
		
		try {
			sql = sb.build(0, json);
			int total = dao.getTotalQna(sql.toString());
			int currentPage = json.getInt("page");
			int amount = json.getInt("amount");
			Paging paging = new Paging(currentPage, amount, total);
			
			session.setAttribute("currentPage", paging.getCurrentPage());
			session.setAttribute("amount", paging.getAmount());
			
			json.put("page", paging.getCurrentPage());
			json.put("amount", paging.getAmount());
			
			ObjectMapper mapper = new ObjectMapper();
	        jsonString = mapper.writeValueAsString(paging);
	        sql = sb.build(1, json);
	        
	        jsonResult.put("status", true);
			jsonResult.put("content", dao.getQnaList(sql.toString()));
			jsonResult.put("paging", new JSONObject(jsonString));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}
        
		return jsonResult;
	}
	
	private JSONObject returnReview(HttpServletRequest request, JSONObject json) {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		SqlBuilder sb = SqlBuilder.getInstance();
		ReviewDAO dao = ReviewDAO.getInstance();
		
		StringBuilder sql = null;
		String jsonString = null;
		
		try {
			sql = sb.build(0, json);
			int total = dao.getTotalReview(sql.toString());
			int currentPage = json.getInt("page");
			int amount = json.getInt("amount");
			Paging paging = new Paging(currentPage, amount, total);
			
			session.setAttribute("currentPage", paging.getCurrentPage());
			session.setAttribute("amount", paging.getAmount());
			
			json.put("page", paging.getCurrentPage());
			json.put("amount", paging.getAmount());
			
			ObjectMapper mapper = new ObjectMapper();
	        jsonString = mapper.writeValueAsString(paging);
	        sql = sb.build(1, json);
	        
	        jsonResult.put("status", true);
			jsonResult.put("content", dao.getReviewList(sql.toString()));
			jsonResult.put("paging", new JSONObject(jsonString));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}
		
		return jsonResult;
	}
	
	private JSONObject returnNotice(HttpServletRequest request, JSONObject json) {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		SqlBuilder sb = SqlBuilder.getInstance();
		AdminDAO dao = AdminDAO.getInstance();
		
		StringBuilder sql = null;
		String jsonString = null;
		
		try {
			sql = sb.build(0, json);
			int total = dao.getTotalQna(sql.toString());
			int currentPage = json.getInt("page");
			int amount = json.getInt("amount");
			Paging paging = new Paging(currentPage, amount, total);
			
			session.setAttribute("currentPage", paging.getCurrentPage());
			session.setAttribute("amount", paging.getAmount());
			
			json.put("page", paging.getCurrentPage());
			json.put("amount", paging.getAmount());
			
			ObjectMapper mapper = new ObjectMapper();
	        jsonString = mapper.writeValueAsString(paging);
	        sql = sb.build(1, json);
	        
	        jsonResult.put("status", true);
			jsonResult.put("content", dao.getQnaList(sql.toString()));
			jsonResult.put("paging", new JSONObject(jsonString));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}
		
		return jsonResult;
	}
	
	private JSONObject returnMember(HttpServletRequest request, JSONObject json) {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		SqlBuilder sb = SqlBuilder.getInstance();
		AdminDAO dao = AdminDAO.getInstance();
		
		StringBuilder sql = null;
		String jsonString = null;
		
		try {
			sql = sb.build(0, json);
			int total = dao.getTotalQna(sql.toString());
			int currentPage = json.getInt("page");
			int amount = json.getInt("amount");
			Paging paging = new Paging(currentPage, amount, total);
			
			session.setAttribute("currentPage", paging.getCurrentPage());
			session.setAttribute("amount", paging.getAmount());
			
			json.put("page", paging.getCurrentPage());
			json.put("amount", paging.getAmount());
			
			ObjectMapper mapper = new ObjectMapper();
	        jsonString = mapper.writeValueAsString(paging);
	        sql = sb.build(1, json);
	        
	        jsonResult.put("status", true);
			jsonResult.put("content", dao.getQnaList(sql.toString()));
			jsonResult.put("paging", new JSONObject(jsonString));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}
		
		return jsonResult;
	}
}
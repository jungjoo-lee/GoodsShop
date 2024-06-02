package com.goodsshop.controller.action.admin;

import java.io.IOException;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodsshop.controller.action.FatchAction;
import com.goodsshop.controller.member.MemberVO;
import com.goodsshop.dao.AdminDAO;
import com.goodsshop.dao.ReviewDAO;
import com.goodsshop.util.Paging;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PageInfoAction implements FatchAction {
	@Override
	public JSONObject execute(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws ServletException, IOException {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		AdminDAO dao = AdminDAO.getInstance();

		switch ((String)json.getString("table")) {
			case "qna" -> jsonResult = totalQna(request, jsonResult);
			case "review_view" -> jsonResult = totalReview(request, jsonResult);
			case "notice" -> jsonResult = totalNotice(request, jsonResult);
			case "member" -> jsonResult = totalMember(request, jsonResult);
		}
		
		return jsonResult;
	}
	
	private JSONObject totalQna(HttpServletRequest request, JSONObject json) {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		AdminDAO dao = AdminDAO.getInstance();

		try {
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
	        String jsonString;
	        
			jsonString = mapper.writeValueAsString(paging);
			jsonResult.put("status", true);
			jsonResult.put("paging", new JSONObject(jsonString));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}
		
		return jsonResult;
	}
	
	private JSONObject totalReview(HttpServletRequest request, JSONObject json) {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		ReviewDAO dao = ReviewDAO.getInstance();
		MemberVO vo = (MemberVO)session.getAttribute("loginUser");

		try {
			int total = dao.getTotalReview();
			int currentPage = 1;
			int amount = 5;
			
			if(session.getAttribute("currentPage") != null) {
				currentPage = (Integer)session.getAttribute("currentPage");
			}
			if(session.getAttribute("amount") != null) {
				amount = (Integer)session.getAttribute("amount");
			}
			Paging paging = new Paging(currentPage, amount, total);
			
			int myTotal = dao.getTotalMyReview(vo.getUserid());
			int myCurrentPage = 1;
			if(session.getAttribute("myCurrentPage") != null) {
				myCurrentPage = (Integer)session.getAttribute("myCurrentPage");
			}
			Paging myPaging = new Paging(myCurrentPage, amount, myTotal);
			
			ObjectMapper mapper = new ObjectMapper();
	        String jsonString;
	        
			jsonString = mapper.writeValueAsString(paging);
			jsonResult.put("status", true);
			jsonResult.put("paging", new JSONObject(jsonString));
			jsonString = mapper.writeValueAsString(myPaging);
			jsonResult.put("myPaging", new JSONObject(jsonString));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}
		
		return jsonResult;
	}
	
	private JSONObject totalNotice(HttpServletRequest request, JSONObject json) {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		AdminDAO dao = AdminDAO.getInstance();

		try {
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
	        String jsonString;
	        
			jsonString = mapper.writeValueAsString(paging);
			jsonResult.put("status", true);
			jsonResult.put("paging", new JSONObject(jsonString));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}
		
		return jsonResult;
	}
	
	private JSONObject totalMember(HttpServletRequest request, JSONObject json) {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		AdminDAO dao = AdminDAO.getInstance();

		try {
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
	        String jsonString;
	        
			jsonString = mapper.writeValueAsString(paging);
			jsonResult.put("status", true);
			jsonResult.put("paging", new JSONObject(jsonString));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}
		
		return jsonResult;
	}
}
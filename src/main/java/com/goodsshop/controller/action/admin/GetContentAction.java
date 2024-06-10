package com.goodsshop.controller.action.admin;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodsshop.controller.action.FatchAction;
import com.goodsshop.dao.AdminDAO;
import com.goodsshop.dao.NoticeDAO;
import com.goodsshop.dao.QnaDAO;
import com.goodsshop.dao.ReviewDAO;
import com.goodsshop.dto.MemberVO;
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
			case "member_view" -> jsonResult = returnMember(request, json);
		}
		
		return jsonResult;
	}
	
	private JSONObject returnQna(HttpServletRequest request, JSONObject json) {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		SqlBuilder sb = SqlBuilder.getInstance();
		QnaDAO dao = new QnaDAO();
		
		MemberVO vo = (MemberVO)session.getAttribute("loginUser");
		
		StringBuilder sql = null;
		String jsonString = null;
		
		int total = 0;
		int currentPage = 0;
		int amount = 0;
		Paging paging = null;
		
		try {
			if (json.has("my")) {
				json.remove("my");
				json.put("userid", vo.getUserid());
				sql = sb.build(0, json);
				total = dao.getTotalMyQna(sql);
				currentPage = json.getInt("page");
				amount = json.getInt("amount");
				paging = new Paging(currentPage, amount, total);
				sql = sb.build(1, json);
			} else {
				sql = sb.build(0, json);
				total = dao.getTotalQna(sql.toString());
				currentPage = json.getInt("page");
				amount = json.getInt("amount");
				paging = new Paging(currentPage, amount, total);
				sql = sb.build(1, json);
			}			
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
		ReviewDAO dao = new ReviewDAO();
		
		MemberVO vo = (MemberVO)session.getAttribute("loginUser");
		
		StringBuilder sql = null;
		String jsonString = null;
		
		int total = 0;
		int currentPage = 0;
		int amount = 0;
		Paging paging = null;
		
		try {
			if (json.has("my")) {
				json.remove("my");
				json.put("userid", vo.getUserid());
				sql = sb.build(0, json);
				total = dao.getTotalMyReview(sql);
				currentPage = json.getInt("page");
				amount = json.getInt("amount");
				paging = new Paging(currentPage, amount, total);
				sql = sb.build(1, json);
			} else {
				sql = sb.build(0, json);
				total = dao.getTotalReview(sql.toString());
				currentPage = json.getInt("page");
				amount = json.getInt("amount");
				paging = new Paging(currentPage, amount, total);
				sql = sb.build(1, json);
			}			
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
		NoticeDAO dao = NoticeDAO.getInstance();
		
		StringBuilder sql = null;
		String jsonString = null;
		
		try {
			sql = sb.build(0, json);
			int total = dao.getTotalNotice(sql.toString());
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
			jsonResult.put("content", dao.getNoticeList(sql.toString()));
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
			int total = dao.getTotalMember(sql.toString());
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
			jsonResult.put("content", dao.getMemberList(sql.toString()));
			jsonResult.put("paging", new JSONObject(jsonString));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}
		
		return jsonResult;
	}
}
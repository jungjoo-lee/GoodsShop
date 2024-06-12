package com.goodsshop.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodsshop.dao.ReviewDAO;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.dto.ReviewVO;
import com.goodsshop.util.Paging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ReviewAction {
	public String reviewList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ReviewDAO dao = ReviewDAO.getInstance();
		
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
		request.setAttribute("reviewList", dao.getReviewList(paging.getAmount(), paging.getCurrentPage()));
		request.setAttribute("paging", paging);
		
		if (session.getAttribute("loginUser") != null) {
			MemberVO vo = (MemberVO)session.getAttribute("loginUser");
			
			int myTotal = dao.getTotalMyReview(vo.getUserid());
			int myCurrentPage = 1;
			if(session.getAttribute("myCurrentPage") != null) {
				myCurrentPage = (Integer)session.getAttribute("myCurrentPage");
			}
			Paging myPaging = new Paging(myCurrentPage, amount, myTotal);
			request.setAttribute("reviewMyList", dao.getReviewMyList(myPaging.getAmount(), myPaging.getCurrentPage(), vo.getUserid()));
			request.setAttribute("myPaging", myPaging);
		}
		
		return "/review/reviewList.jsp";
	}
	
	public JSONObject reviewWrite(HttpServletRequest request, HttpServletResponse response)  throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonStr = in.readLine();
		JSONObject jsonObj = new JSONObject(jsonStr);
		
		HttpSession session = request.getSession();
		ReviewDAO dao = ReviewDAO.getInstance();
		MemberVO mVO = (MemberVO)session.getAttribute("loginUser");
		ReviewVO rVO = ReviewVO.builder().userid(mVO.getUserid()).grade(mVO.getGseq()).gseq(jsonObj.getInt("gseq")).subject(jsonObj.getString("subject")).content(jsonObj.getString("content")).build();
		
		JSONObject json = new JSONObject();

		try {
			dao.reviewWrite(rVO);
			rVO.setRseq(dao.lastID());
			ObjectMapper mapper = new ObjectMapper();
	        String jsonRvo = mapper.writeValueAsString(rVO);
	        
	        int total = dao.getGoodsReviewTotal(rVO.getGseq());
	        Paging paging = new Paging(1, 10, total);
	        String jsonPaging = mapper.writeValueAsString(paging);
	        
			json.put("status", true);
			json.put("vo", new JSONObject(jsonRvo));
			json.put("paging", new JSONObject(jsonPaging));
		} catch (Exception e) {
			e.printStackTrace();
			json.put("status", false);
			json.put("message", "실패");
		}
		
		return json;
	}

	public JSONObject reviewUpdate(HttpServletRequest request, HttpServletResponse response)  throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonStr = in.readLine();
		JSONObject jsonObj = new JSONObject(jsonStr);
		
		JSONObject json = new JSONObject();
		ReviewDAO dao = ReviewDAO.getInstance();
		// member 확인
		ReviewVO rVO = ReviewVO.builder().rseq(jsonObj.getInt("rseq")).subject(jsonObj.getString("subject")).content(jsonObj.getString("content")).build();
		
		try {
			dao.reviewUpdate(rVO);	        
			json.put("status", true);
			json.put("message", "수정되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			json.put("status", false);
			json.put("message", "실패");
		}
		
		return json;
	}

	public JSONObject reviewDelete(HttpServletRequest request, HttpServletResponse response)  throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonStr = in.readLine();
		JSONObject jsonObj = new JSONObject(jsonStr);
		
		JSONObject jsonResult = new JSONObject();
		ReviewDAO dao = ReviewDAO.getInstance();
		
		int rseq = jsonObj.getInt("rseq");
		
		try {
			dao.reviewDelete(rseq);
			jsonResult.put("status", true);
			jsonResult.put("message", "삭제되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}
		
		return jsonResult;
	}
}

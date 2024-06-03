package com.goodsshop.controller.action.review;

import java.io.IOException;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodsshop.controller.action.FatchAction;
import com.goodsshop.dao.ReviewDAO;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.dto.ReviewVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ReviewWriteAction implements FatchAction {
	@Override
	public JSONObject execute(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObj) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ReviewDAO dao = ReviewDAO.getInstance();
		MemberVO mVO = (MemberVO)session.getAttribute("loginUser");
		ReviewVO rVO = ReviewVO.builder().userid(mVO.getUserid()).grade(mVO.getGseq()).gseq(jsonObj.getInt("gseq")).subject(jsonObj.getString("subject")).content(jsonObj.getString("content")).build();
		
		JSONObject json = new JSONObject();

		try {
			dao.reviewWrite(rVO);
			rVO.setRseq(dao.lastID());
			ObjectMapper mapper = new ObjectMapper();
	        String jsonString = mapper.writeValueAsString(rVO);
	        
			json.put("status", true);
			json.put("message", "리뷰 작성 성공");
			json.put("vo", new JSONObject(jsonString));
		} catch (Exception e) {
			e.printStackTrace();
			json.put("status", false);
			json.put("message", "실패");
		}
		
		return json;
	}
}
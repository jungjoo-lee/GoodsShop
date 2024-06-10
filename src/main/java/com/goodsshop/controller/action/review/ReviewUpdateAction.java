package com.goodsshop.controller.action.review;

import java.io.IOException;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodsshop.controller.action.FatchAction;
import com.goodsshop.dao.ReviewDAO;
import com.goodsshop.dto.ReviewVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReviewUpdateAction implements FatchAction {
	@Override
	public JSONObject execute(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObj) throws ServletException, IOException {
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
}
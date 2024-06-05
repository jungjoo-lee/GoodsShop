package com.goodsshop.controller.action.review;

import java.io.IOException;

import org.json.JSONObject;

import com.goodsshop.controller.action.FatchAction;
import com.goodsshop.dao.ReviewDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReviewDeleteAction implements FatchAction {
	@Override
	public JSONObject execute(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObj) throws ServletException, IOException {
		JSONObject jsonResult = new JSONObject();
		ReviewDAO dao = ReviewDAO.getInstance();
		
		int rseq = jsonObj.getInt("rseq");
		
		try {
			dao.reviewDelete(rseq);
			jsonResult.put("status", true);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("status", false);
		}
		
		return jsonResult;
	}
}
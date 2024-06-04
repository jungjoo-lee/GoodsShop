package com.goodsshop.controller.action.review;

import java.io.IOException;

import org.json.JSONObject;

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
		
		ReviewVO vo = new ReviewVO();
		
		dao.reviewUpdate(vo);
		
		return json;
	}
}
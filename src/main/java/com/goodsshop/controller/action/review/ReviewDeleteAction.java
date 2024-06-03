package com.goodsshop.controller.action.review;

import java.io.IOException;

import org.json.JSONObject;

import com.goodsshop.controller.action.FatchAction;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReviewDeleteAction implements FatchAction {
	@Override
	public JSONObject execute(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObj) throws ServletException, IOException {
		JSONObject json = new JSONObject();
		
		return json;
	}
}
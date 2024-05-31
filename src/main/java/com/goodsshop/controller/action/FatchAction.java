package com.goodsshop.controller.action;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface FatchAction {
	public JSONObject execute(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObj) throws ServletException, IOException;
}

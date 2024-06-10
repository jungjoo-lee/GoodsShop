package com.goodsshop.controller.action.admin;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import com.goodsshop.controller.action.FatchAction;
import com.goodsshop.dao.AdminDAO;
import com.goodsshop.util.ParseList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SwitchYNAction implements FatchAction {
	@Override
	public JSONObject execute(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObj) throws ServletException, IOException {
		AdminDAO dao = AdminDAO.getInstance();
		JSONObject jsonResult = new JSONObject();
		
		ParseList parse = new ParseList();
		List<String> useridList = parse.parseStringList(jsonObj);
		
		try {
			dao.switchYN(useridList);
			jsonResult.put("status", true);
			jsonResult.put("message", "처리되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "실패");
		}
		
		return jsonResult;
	}
}

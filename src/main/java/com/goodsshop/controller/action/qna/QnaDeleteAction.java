package com.goodsshop.controller.action.qna;

import java.io.IOException;

import org.json.JSONObject;

import com.goodsshop.controller.action.FatchAction;
import com.goodsshop.dao.QnaDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaDeleteAction implements FatchAction {
	@Override
	public JSONObject execute(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObj) throws ServletException, IOException {
		JSONObject jsonResult = new JSONObject();
		QnaDAO dao = QnaDAO.getInstance();
		
		try {
			dao.deleteQna(jsonObj.getInt("qseq"));
			jsonResult.put("status", true);
			jsonResult.put("message", "삭제 성공");
			jsonResult.put("url", "/GoodsShop/gshop.do?command=qnaList");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "실패");
		}
		
		return jsonResult;
	}
}
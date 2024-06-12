package com.goodsshop.controller.action.qna;

import java.io.IOException;

import org.json.JSONObject;

import com.goodsshop.controller.action.FatchAction;
import com.goodsshop.dao.QnaDAO;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.dto.QnaVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaWriteAction implements FatchAction {
	@Override
	public JSONObject execute(HttpServletRequest request, HttpServletResponse response, JSONObject jsonObj) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO mVO = (MemberVO)session.getAttribute("loginUser");
		JSONObject jsonResult = new JSONObject();
		QnaDAO dao = QnaDAO.getInstance();
		QnaVO qVO = QnaVO.builder().userid(mVO.getUserid()).subject(jsonObj.getString("subject")).content(jsonObj.getString("content")).build();
		
		try {
			dao.writeQna(qVO);
			jsonResult.put("status", true);
			jsonResult.put("message", "Q&A 작성 성공");
			int qseq = dao.lastID();
			jsonResult.put("url", "/GoodsShop/qnaView.do&qseq=" + qseq);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "실패");
		}
		
		return jsonResult;
	}
}
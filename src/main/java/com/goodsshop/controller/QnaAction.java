package com.goodsshop.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONObject;

import com.goodsshop.dao.QnaDAO;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.dto.QnaVO;
import com.goodsshop.util.Paging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class QnaAction {
	public String qnaList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		QnaDAO dao = QnaDAO.getInstance();

		int total = dao.getTotalQna();
		int currentPage = 1;
		int amount = 10;
		if (session.getAttribute("currentPage") != null) {
			currentPage = (Integer) session.getAttribute("currentPage");
		}
		if (session.getAttribute("amount") != null) {
			amount = (Integer) session.getAttribute("amount");
		}
		Paging paging = new Paging(currentPage, amount, total);
		request.setAttribute("qnaList", dao.getQnaList(paging.getAmount(), paging.getCurrentPage()));
		request.setAttribute("paging", paging);

		if (session.getAttribute("loginUser") != null) {
			MemberVO vo = (MemberVO) session.getAttribute("loginUser");

			int myCurrentPage = 1;
			int myTotal = dao.getTotalMyQna(vo.getUserid());
			if (session.getAttribute("currentPage") != null) {
				currentPage = (Integer) session.getAttribute("currentPage");
			}
			Paging myPaging = new Paging(myCurrentPage, amount, myTotal);
			request.setAttribute("qnaMyList",
					dao.getMyQnaList(myPaging.getAmount(), myPaging.getCurrentPage(), vo.getUserid()));
			request.setAttribute("myPaging", myPaging);
		}

		return "/qna/qnaList.jsp";
	}

	public String qnaView(HttpServletRequest request, HttpServletResponse response) {
		int qseq = Integer.parseInt(request.getParameter("qseq"));
		QnaDAO dao = QnaDAO.getInstance();

		request.setAttribute("vo", dao.getQna(qseq));
		return "/qna/qnaView.jsp";
	}

	public String qnaWriteForm(HttpServletRequest request, HttpServletResponse response) {
		if (request.getParameterMap().containsKey("qseq")) {
			QnaDAO dao = QnaDAO.getInstance();
			request.setAttribute("vo", dao.getQna(Integer.parseInt(request.getParameter("qseq"))));
		}

		return "/qna/qnaWriteFom.jsp";
	}

	public JSONObject qnaWrite(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonStr = in.readLine();
		JSONObject jsonObj = new JSONObject(jsonStr);
		
		HttpSession session = request.getSession();
		MemberVO mVO = (MemberVO) session.getAttribute("loginUser");
		JSONObject jsonResult = new JSONObject();
		QnaDAO dao = QnaDAO.getInstance();
		QnaVO qVO = QnaVO.builder().userid(mVO.getUserid()).subject(jsonObj.getString("subject"))
				.content(jsonObj.getString("content")).build();

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

	public JSONObject qnaUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonStr = in.readLine();
		JSONObject jsonObj = new JSONObject(jsonStr);
		
		HttpSession session = request.getSession();
		MemberVO mVO = (MemberVO) session.getAttribute("loginUser");
		JSONObject jsonResult = new JSONObject();
		QnaDAO dao = QnaDAO.getInstance();
		QnaVO qVO = QnaVO.builder().qseq(jsonObj.getInt("qseq")).userid(mVO.getUserid())
				.subject(jsonObj.getString("subject")).content(jsonObj.getString("content")).build();

		try {
			dao.updateQna(qVO);
			jsonResult.put("status", true);
			jsonResult.put("message", "Q&A 수정 성공");
			jsonResult.put("url", "/GoodsShop/qnaView.do&qseq=" + qVO.getQseq());
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "실패");
		}

		return jsonResult;
	}

	public JSONObject qnaDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonStr = in.readLine();
		JSONObject jsonObj = new JSONObject(jsonStr);
		
		JSONObject jsonResult = new JSONObject();
		QnaDAO dao = QnaDAO.getInstance();

		try {
			dao.deleteQna(jsonObj.getInt("qseq"));
			jsonResult.put("status", true);
			jsonResult.put("message", "삭제 성공");
			jsonResult.put("url", "/GoodsShop/qnaList.do");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "실패");
		}

		return jsonResult;
	}
}

package com.goodsshop.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodsshop.dao.AdminDAO;
import com.goodsshop.dao.NoticeDAO;
import com.goodsshop.dao.QnaDAO;
import com.goodsshop.dao.ReviewDAO;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.util.Paging;
import com.goodsshop.util.SqlBuilder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UtilAction {
	public void imageWrite(HttpServletRequest request, HttpServletResponse response) {
		String folder = request.getParameter("folder");
		String realName = request.getParameter("realName");

		try {
			String imagePath = "c:\\upload\\";
			File imageFile = new File(imagePath + folder + "\\" + realName);

			if (imageFile.exists()) {
				response.setContentType("image/jpeg");
				FileInputStream fis = new FileInputStream(imageFile);
				OutputStream os = response.getOutputStream();

				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = fis.read(buffer)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
				os.close();
				fis.close();
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSONObject pageInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonStr = in.readLine();
		JSONObject json = new JSONObject(jsonStr);
		JSONObject jsonResult = new JSONObject();

		switch ((String) json.getString("table")) {
		case "qna" -> jsonResult = totalQna(request, jsonResult);
		case "review_view" -> jsonResult = totalReview(request, jsonResult);
		case "notice" -> jsonResult = totalNotice(request, jsonResult);
		case "member_view" -> jsonResult = totalMember(request, jsonResult);
		}

		return jsonResult;
	}

	private JSONObject totalQna(HttpServletRequest request, JSONObject json) {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		QnaDAO dao = QnaDAO.getInstance();

		try {
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
			ObjectMapper mapper = new ObjectMapper();
			String jsonString;

			if ((MemberVO) session.getAttribute("loginUser") != null) {
				MemberVO vo = (MemberVO) session.getAttribute("loginUser");
				int myTotal = dao.getTotalMyQna(vo.getUserid());
				int myCurrentPage = 1;
				if (session.getAttribute("myCurrentPage") != null) {
					myCurrentPage = (Integer) session.getAttribute("myCurrentPage");
				}
				Paging myPaging = new Paging(myCurrentPage, amount, myTotal);
				jsonString = mapper.writeValueAsString(myPaging);
				jsonResult.put("myPaging", new JSONObject(jsonString));
			}
			jsonString = mapper.writeValueAsString(paging);
			jsonResult.put("status", true);
			jsonResult.put("paging", new JSONObject(jsonString));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}

		return jsonResult;
	}

	private JSONObject totalReview(HttpServletRequest request, JSONObject json) {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		ReviewDAO dao = ReviewDAO.getInstance();

		try {
			int total = dao.getTotalReview();
			int currentPage = 1;
			int amount = 5;

			if (session.getAttribute("currentPage") != null) {
				currentPage = (Integer) session.getAttribute("currentPage");
			}
			if (session.getAttribute("amount") != null) {
				amount = (Integer) session.getAttribute("amount");
			}
			Paging paging = new Paging(currentPage, amount, total);
			ObjectMapper mapper = new ObjectMapper();
			String jsonString;

			if ((MemberVO) session.getAttribute("loginUser") != null) {
				MemberVO vo = (MemberVO) session.getAttribute("loginUser");
				int myTotal = dao.getTotalMyReview(vo.getUserid());
				int myCurrentPage = 1;
				if (session.getAttribute("myCurrentPage") != null) {
					myCurrentPage = (Integer) session.getAttribute("myCurrentPage");
				}
				Paging myPaging = new Paging(myCurrentPage, amount, myTotal);
				jsonString = mapper.writeValueAsString(myPaging);
				jsonResult.put("myPaging", new JSONObject(jsonString));
			}

			jsonString = mapper.writeValueAsString(paging);
			jsonResult.put("status", true);
			jsonResult.put("paging", new JSONObject(jsonString));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}

		return jsonResult;
	}

	private JSONObject totalNotice(HttpServletRequest request, JSONObject json) {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		ReviewDAO dao = ReviewDAO.getInstance();

		try {
			int total = dao.getTotalReview();
			int currentPage = 1;
			int amount = 10;

			if (session.getAttribute("currentPage") != null) {
				currentPage = (Integer) session.getAttribute("currentPage");
			}
			if (session.getAttribute("amount") != null) {
				amount = (Integer) session.getAttribute("amount");
			}

			Paging paging = new Paging(currentPage, amount, total);

			ObjectMapper mapper = new ObjectMapper();
			String jsonString;

			jsonString = mapper.writeValueAsString(paging);
			jsonResult.put("status", true);
			jsonResult.put("paging", new JSONObject(jsonString));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}

		return jsonResult;
	}

	private JSONObject totalMember(HttpServletRequest request, JSONObject json) {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		AdminDAO dao = AdminDAO.getInstance();

		try {
			int total = dao.getTotalMember();
			int currentPage = 1;
			int amount = 10;

			if (session.getAttribute("currentPage") != null) {
				currentPage = (Integer) session.getAttribute("currentPage");
			}
			if (session.getAttribute("amount") != null) {
				amount = (Integer) session.getAttribute("amount");
			}

			Paging paging = new Paging(currentPage, amount, total);

			ObjectMapper mapper = new ObjectMapper();
			String jsonString;

			jsonString = mapper.writeValueAsString(paging);
			jsonResult.put("status", true);
			jsonResult.put("paging", new JSONObject(jsonString));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}

		return jsonResult;
	}

	public JSONObject getContent(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonStr = in.readLine();
		JSONObject json = new JSONObject(jsonStr);
		JSONObject jsonResult = new JSONObject();

		switch ((String) json.get("table")) {
		case "qna" -> jsonResult = returnQna(request, json);
		case "review_view" -> jsonResult = returnReview(request, json);
		case "notice" -> jsonResult = returnNotice(request, json);
		case "member_view" -> jsonResult = returnMember(request, json);
		}

		return jsonResult;
	}

	private JSONObject returnQna(HttpServletRequest request, JSONObject json) {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		SqlBuilder sb = SqlBuilder.getInstance();
		QnaDAO dao = new QnaDAO();

		MemberVO vo = (MemberVO) session.getAttribute("loginUser");

		StringBuilder sql = null;
		String jsonString = null;

		int total = 0;
		int currentPage = 0;
		int amount = 0;
		Paging paging = null;

		try {
			if (json.has("my")) {
				json.put("my", vo.getUserid());
				sql = sb.build(0, json);
				total = dao.getTotalMyQna(sql);
				currentPage = json.getInt("page");
				amount = json.getInt("amount");
				paging = new Paging(currentPage, amount, total);
				sql = sb.build(1, json);
			} else {
				sql = sb.build(0, json);
				total = dao.getTotalQna(sql.toString());
				currentPage = json.getInt("page");
				amount = json.getInt("amount");
				paging = new Paging(currentPage, amount, total);
				sql = sb.build(1, json);
			}
			session.setAttribute("currentPage", paging.getCurrentPage());
			session.setAttribute("amount", paging.getAmount());

			json.put("page", paging.getCurrentPage());
			json.put("amount", paging.getAmount());

			ObjectMapper mapper = new ObjectMapper();
			jsonString = mapper.writeValueAsString(paging);
			sql = sb.build(1, json);

			jsonResult.put("status", true);
			jsonResult.put("content", dao.getQnaList(sql.toString()));
			jsonResult.put("paging", new JSONObject(jsonString));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}

		return jsonResult;
	}

	private JSONObject returnReview(HttpServletRequest request, JSONObject json) {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		SqlBuilder sb = SqlBuilder.getInstance();
		ReviewDAO dao = new ReviewDAO();

		MemberVO vo = (MemberVO) session.getAttribute("loginUser");

		StringBuilder sql = null;
		String jsonString = null;

		int total = 0;
		int currentPage = 0;
		int amount = 0;
		Paging paging = null;

		try {
			if (json.has("my")) {
				json.put("my", vo.getUserid());
				sql = sb.build(0, json);
				total = dao.getTotalMyReview(sql);
				currentPage = json.getInt("page");
				amount = json.getInt("amount");
				paging = new Paging(currentPage, amount, total);
				sql = sb.build(1, json);
			} else {
				sql = sb.build(0, json);
				total = dao.getTotalReview(sql.toString());
				currentPage = json.getInt("page");
				amount = json.getInt("amount");
				paging = new Paging(currentPage, amount, total);
				sql = sb.build(1, json);
			}
			session.setAttribute("currentPage", paging.getCurrentPage());
			session.setAttribute("amount", paging.getAmount());

			json.put("page", paging.getCurrentPage());
			json.put("amount", paging.getAmount());

			ObjectMapper mapper = new ObjectMapper();
			jsonString = mapper.writeValueAsString(paging);
			sql = sb.build(1, json);

			jsonResult.put("status", true);
			jsonResult.put("content", dao.getReviewList(sql.toString()));
			jsonResult.put("paging", new JSONObject(jsonString));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}

		return jsonResult;
	}

	private JSONObject returnNotice(HttpServletRequest request, JSONObject json) {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		SqlBuilder sb = SqlBuilder.getInstance();
		NoticeDAO dao = NoticeDAO.getInstance();

		StringBuilder sql = null;
		String jsonString = null;

		try {
			sql = sb.build(0, json);
			int total = dao.getTotalNotice(sql.toString());
			int currentPage = json.getInt("page");
			int amount = json.getInt("amount");
			Paging paging = new Paging(currentPage, amount, total);

			session.setAttribute("currentPage", paging.getCurrentPage());
			session.setAttribute("amount", paging.getAmount());

			json.put("page", paging.getCurrentPage());
			json.put("amount", paging.getAmount());

			ObjectMapper mapper = new ObjectMapper();
			jsonString = mapper.writeValueAsString(paging);
			sql = sb.build(1, json);

			jsonResult.put("status", true);
			jsonResult.put("content", dao.getNoticeList(sql.toString()));
			jsonResult.put("paging", new JSONObject(jsonString));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}

		return jsonResult;
	}

	private JSONObject returnMember(HttpServletRequest request, JSONObject json) {
		HttpSession session = request.getSession();
		JSONObject jsonResult = new JSONObject();
		SqlBuilder sb = SqlBuilder.getInstance();
		AdminDAO dao = AdminDAO.getInstance();

		StringBuilder sql = null;
		String jsonString = null;

		try {
			sql = sb.build(0, json);
			int total = dao.getTotalMember(sql.toString());
			int currentPage = json.getInt("page");
			int amount = json.getInt("amount");
			Paging paging = new Paging(currentPage, amount, total);

			session.setAttribute("currentPage", paging.getCurrentPage());
			session.setAttribute("amount", paging.getAmount());

			json.put("page", paging.getCurrentPage());
			json.put("amount", paging.getAmount());

			ObjectMapper mapper = new ObjectMapper();
			jsonString = mapper.writeValueAsString(paging);
			sql = sb.build(1, json);

			jsonResult.put("status", true);
			jsonResult.put("content", dao.getMemberList(sql.toString()));
			jsonResult.put("paging", new JSONObject(jsonString));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "오류");
		}

		return jsonResult;
	}
}

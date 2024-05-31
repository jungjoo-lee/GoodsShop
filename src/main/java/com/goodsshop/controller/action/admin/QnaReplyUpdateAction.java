package com.goodsshop.controller.action.admin;

import java.io.IOException;

import com.goodsshop.controller.action.Action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaReplyUpdateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int qseq = Integer.parseInt(request.getParameter("qseq"));
		System.out.println(request.getParameter("replytext"));
		
		response.sendRedirect("gshop.do?command=qnaView&qseq=" + qseq);
	}
}
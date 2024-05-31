package com.goodsshop.controller.action.admin;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.AdminDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class QnaReplyWriteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		AdminDAO dao = AdminDAO.getInstance();
		int qseq = Integer.parseInt(request.getParameter("qseq"));
		String reply = request.getParameter("reply");
		
		dao.writeReply(reply, qseq);
		
		response.sendRedirect("gshop.do?command=qnaView&qseq=" + qseq);
	}
}
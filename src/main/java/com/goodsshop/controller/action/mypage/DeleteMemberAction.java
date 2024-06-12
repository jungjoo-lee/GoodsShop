package com.goodsshop.controller.action.mypage;

import java.io.IOException;
import java.io.PrintWriter;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.dao.MemberDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DeleteMemberAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		
		if( mvo==null ) {
			response.sendRedirect("gshop.do?command=loginForm");
		}else {
			MemberDAO mdao = MemberDAO.getInstance();
			int result = mdao.deleteMember(mvo.getUserid());
			
			if (result == 1) {
				session.removeAttribute("loginUser");
				PrintWriter out = response.getWriter();
				out.print("<script>alert('정상 탈퇴 처리되었습니다.');</script>");
				out.print("<script>location.href='gshop.do?command=index'</script>");
			}
			
			
			//session.setAttribute("message", "정상 탈퇴 처리되었습니다.");
			response.sendRedirect("gshop.do?command=updateMemberForm");
		}

	}

}

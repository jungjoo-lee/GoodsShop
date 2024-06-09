package com.goodsshop.controller.action.member;

import java.io.IOException;
import java.util.List;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.MemberDao;
import com.goodsshop.dto.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SearchPwdAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String email = request.getParameter("email");
		String userCode = request.getParameter("securityCodeInput"); // 사용자가 입력한 인증번호
		String randomCode = (String) request.getSession().getAttribute("verificationCode"); // 세션에 저장된 인증번호

		if (userCode.equals(randomCode)) {
			MemberDao mdao = MemberDao.getInstance();
			List<MemberVO> member1 = mdao.checkMemberPwd(userid, email);

			if (!member1.isEmpty()) {
				MemberVO member = member1.get(0); // 리스트의 첫 번째 회원 정보를 가져옴
				String pwd = member.getPwd(); // 회원 아이디를 가져옴

				// JavaScript로 새로운 창을 열어서 아이디를 보여줌
				String script = "<script>alert('회원 비밀민호는 " + pwd + " 입니다.');";
				script += "window.location.href='gshop.do?command=loginForm';</script>";
				response.getWriter().print(script);
			} else {
				// 해당하는 회원을 찾을 수 없는 경우에 대한 처리
				System.out.println(userid);
				System.out.println(email);
				String script = "<script>alert( '해당하는 회원을 찾을 수 없습니다.');";
				request.getRequestDispatcher("/WEB-INF/jsp/member/loginForm.jsp").forward(request, response);
			}
		} else {
			// 인증번호가 일치하지 않는 경우에 대한 처리
			String script = "<script>alert('인증번호 불일치');";
			script += "window.location.href='gshop.do?command=loginForm';</script>";
			response.getWriter().print(script);

		}
	}
}

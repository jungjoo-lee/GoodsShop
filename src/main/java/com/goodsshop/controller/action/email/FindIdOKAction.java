package com.goodsshop.controller.action.email;

import java.io.IOException;
import java.io.PrintWriter;

import com.goodsshop.controller.action.Action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class FindIdOKAction implements Action {


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userSecurityCode = request.getParameter("securityCodeInput"); // 사용자가 입력한 인증 코드
        String verificationCode = (String) session.getAttribute("verificationCode"); // 세션에 저장된 인증 코드
        Long verificationCodeExpiration = (Long) session.getAttribute("verificationCodeExpiration"); // 세션에 저장된 인증 코드의 만료 시간
        String name = request.getParameter("name");
		String email = request.getParameter("email");
        session.setAttribute("name", name);
        session.setAttribute("email", email);
     
        PrintWriter out = response.getWriter();

        // 인증 코드가 없거나 이미 만료된 경우
        if (verificationCode == null || verificationCodeExpiration == null || System.currentTimeMillis() > verificationCodeExpiration) {
        	out.print("<script>alert('인증 코드가 만료되었습니다. 다시 시도해주세요.'); window.location.href='gshop.do?command=findIdForm';</script>");
            out.close();
            return;
        }

        // 사용자가 입력한 인증 코드가 세션에 저장된 인증 코드와 일치하는지 확인
        if (userSecurityCode.equals(verificationCode)) {
            out.print("<script>alert('인증되었습니다.'); window.location.href='gshop.do?command=searchId';</script>");
            session.removeAttribute("verificationCode"); // 세션에서 인증 코드 제거
            session.removeAttribute("verificationCodeExpiration"); // 세션에서 인증 코드 만료 시간 제거
        } else {
            out.print("<script>alert('잘못된 인증 코드입니다.'); window.location.href='gshop.do?command=findIdForm';</script>");
        }
        out.close();
    }
}

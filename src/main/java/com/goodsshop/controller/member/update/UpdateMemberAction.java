package com.goodsshop.controller.member.update;

import java.io.IOException;
import java.io.PrintWriter;

import com.goodsshop.controller.action.Action;

import com.goodsshop.dao.MemberDAO;
import com.goodsshop.dto.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UpdateMemberAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				HttpSession session = request.getSession();
				MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
				if(mvo==null) {
					response.sendRedirect("gshop.do?command=loginForm");
				} else {
					MemberVO mvo1 = new MemberVO();
					mvo1.setUserid(request.getParameter("userid"));
					mvo1.setPwd(request.getParameter("pwd"));
					mvo1.setName(request.getParameter("name"));
					mvo1.setPhone(request.getParameter("phone"));
					mvo1.setEmail(request.getParameter("email"));
					mvo1.setZip_code(request.getParameter("zip_code"));
					mvo1.setAddress(request.getParameter("address"));
					mvo1.setD_address(request.getParameter("d_address"));
					
					MemberDAO mdao = MemberDAO.getInstance();
					int result = mdao.updateMember(mvo1);
					
					if(result == 1) {
					session.setAttribute("loginUser", mvo1);
					PrintWriter out = response.getWriter();
					out.print("<script>alert('정상 수정 되었습니다.');</script>");
					out.print("<script>location.href='gshop.do?command=index'</script>");
					}
				}
				
			}

}

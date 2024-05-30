package com.goodsshop.controller.member.update;

import java.io.IOException;

import com.goodsshop.controller.action.Action;
import com.goodsshop.controller.member.MemberDao;
import com.goodsshop.controller.member.MemberVO;

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
					
					MemberDao mdao = MemberDao.getInstance();
					mdao.updateMember(mvo1);
					
					session.setAttribute("loginUser", mvo1);
					
					response.sendRedirect("gshop.do?command=index");
				}
				
			}

}

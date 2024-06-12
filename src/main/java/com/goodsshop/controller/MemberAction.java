package com.goodsshop.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONObject;

import com.goodsshop.controller.member.MemberDao;
import com.goodsshop.dao.AdminDAO;
import com.goodsshop.dao.MemberDAO;
import com.goodsshop.dto.AddressVO;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.properties.Env;
import com.goodsshop.util.ParseList;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MemberAction {
	private static final String SENDER_EMAIL = Env.getSendEmail(); // 보내는 사람 이메일 주소
	private static final String SENDER_PASSWORD = Env.getEmailPwd(); // 보내는 사람 이메일 비밀번호
	private static final long CODE_EXPIRATION_DURATION = 3 * 60 * 1000;

	public String loginForm(HttpServletRequest request, HttpServletResponse response) {
		return "/member/loginForm.jsp";
	}

	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");

		MemberDao mdao = MemberDao.getInstance();
		MemberVO mvo = mdao.getMember(userid);

		String url = "/loginForm.do";
		if (mvo == null)
			request.setAttribute("message", "아이디 혹은 패스워드가 틀립니다");
		else if (!mvo.getPwd().equals(pwd)) {
			request.setAttribute("message", "아이디 혹은 패스워드가 틀립니다");
		} else if (mvo.getPwd().equals(pwd)) {
			if (mvo.getIs_login() == 1) {
				url = "/main.do";
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", mvo);

				session.removeAttribute("loginAdmin");
			} else {
				request.setAttribute("message", "해당 계정은 휴면상태이거나 탈퇴상태입니다");
			}
		} else {
			request.setAttribute("message", "관리자에게 문의하세요");

		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("loginUser");
		response.sendRedirect("/main.do");
	}

	public String findIdForm(HttpServletRequest request, HttpServletResponse response) {
		return "/member/findIdForm.jsp";
	}

	public void findId(HttpServletRequest request, HttpServletResponse response) {
		// 저장된 name 과 email 불러오기
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		// dao 불러오기
		MemberDAO mdao = MemberDAO.getInstance();
		MemberVO mvo = mdao.checkMember(name, email);
		String url = "/email/emailVerification.jsp";

		try {
			if (mvo == null) {
				request.setAttribute("message", "등록된 회원이 아닙니다");
				request.getRequestDispatcher("/WEB-INF/jsp/member/findIdForm.jsp").forward(request, response);
			} else if (mvo.getEmail() == null || mvo.getName() == null || !mvo.getEmail().equals(email)
					|| !mvo.getName().equals(name)) {
				request.setAttribute("message", "등록된 회원이 아닙니다");
				request.getRequestDispatcher("/WEB-INF/jsp/member/findIdForm.jsp").forward(request, response);
			} else if (mvo.getEmail().equals(email) && mvo.getName().equals(name)) {
				request.setAttribute("message", "인증번호가 전송되었습니다");
				// 랜덤한 인증 코드 생성
				String verificationCode = generateVerificationCode();

				// 이메일 전송
				sendEmail(email, verificationCode);

				// 이메일 전송 후 작업 수행
				// 예: 세션에 인증 코드 저장 등
				HttpSession session = request.getSession();
				session.setAttribute("verificationCode", verificationCode);
				session.setAttribute("verificationCodeExpiration",
						System.currentTimeMillis() + CODE_EXPIRATION_DURATION);
				session.setAttribute("name", name);
				session.setAttribute("email", email);

				// 이메일 인증 페이지로 이동
				request.getRequestDispatcher(url).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 랜덤한 6자리 숫자로 인증 코드 생성
	private static String generateVerificationCode() {
		Random random = new Random();
		int code = 100000 + random.nextInt(900000);
		return String.valueOf(code);
	}

	// 이메일 전송
	private static void sendEmail(String email, String verificationCode) {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.enable", "true"); // SSL 활성화
		properties.put("mail.smtp.host", "smtp.gmail.com"); // SMTP 호스트
		properties.put("mail.smtp.port", "465"); // SMTP 포트 (SSL 사용 시)

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SENDER_EMAIL));
			// 이메일 주소가 null 이 아닌 경우에만 이메일 주소를 설정합니다.
			if (email != null && !email.isEmpty()) {
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
				// 이메일 제목 설정
				message.setSubject("이메일 인증 코드");

				// 이메일 본문 설정
				String emailContent = "안녕하세요, 이메일 인증 코드는 다음과 같습니다: " + verificationCode;
				message.setText(emailContent);

				// 이메일 전송
				Transport.send(message);

				System.out.println("이메일이 성공적으로 전송되었습니다.");
			} else if (email == null || email.isEmpty()) {
				// 이메일 주소가 null 이거나 비어있는 경우에 대한 처리를 수행합니다.
				System.out.println("이메일 주소가 올바르지 않습니다.");

				return; // 메서드 종료
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	public void searchId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String userCode = request.getParameter("securityCodeInput"); // 사용자가 입력한 인증번호
		String randomCode = (String) request.getSession().getAttribute("verificationCode"); // 세션에 저장된 인증번호

		if (userCode.equals(randomCode)) { // 사용자 입력과 저장된 인증번호 비교
			MemberDAO mdao = MemberDAO.getInstance();
			List<MemberVO> members = mdao.getMembersByNameAndEmail(name, email);

			if (!members.isEmpty()) {
				MemberVO member = members.get(0); // 리스트의 첫 번째 회원 정보를 가져옴
				String userid = member.getUserid(); // 회원 아이디를 가져옴

				// JavaScript로 새로운 창을 열어서 아이디를 보여줌
				String script = "<script>alert('회원님의 아이디는 " + userid + " 입니다.');";
				script += "window.location.href='/loginForm.do';</script>";
				response.getWriter().print(script);
			} else {
				// 해당하는 회원을 찾을 수 없는 경우에 대한 처리
				request.setAttribute("errorMessage", "해당하는 회원을 찾을 수 없습니다.");
				request.getRequestDispatcher("/WEB-INF/jsp/member/loginForm.jsp").forward(request, response);
			}
		} else {
			// 인증번호가 일치하지 않는 경우에 대한 처리
			String script = "<script>alert('인증번호가 일치하지 않습니다')";
			System.out.println("인증번호 불일치");
			request.getRequestDispatcher("/WEB-INF/jsp/member/loginForm.jsp").forward(request, response);
		}
	}

	public String findPwdForm(HttpServletRequest request, HttpServletResponse response) {
		return "/member/findPwdForm.jsp";
	}

	public void findPwd(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getParameter("userid");
		String email = request.getParameter("email");

		// dao 불러오기
		MemberDAO mdao = MemberDAO.getInstance();
		MemberVO mvo = mdao.checkMembers(userid, email);
		String url = "/email/PwdEmail.jsp";
		try {
			if (mvo == null) {
				request.setAttribute("message", "등록된 회원이 아닙니다");
				request.getRequestDispatcher("/member/findPwdForm.jsp").forward(request, response);
			} else if (mvo.getEmail() == null || mvo.getUserid() == null || !mvo.getEmail().equals(email)
					|| !mvo.getUserid().equals(userid)) {

				request.setAttribute("message", "등록된 회원이 아닙니다");
				request.getRequestDispatcher("/member/findPwdForm.jsp").forward(request, response);
			} else if (mvo.getEmail().equals(email) && mvo.getUserid().equals(userid)) {
				request.setAttribute("message", "인증번호가 전송되었습니다");
				// 랜덤한 인증 코드 생성
				String verificationCode = generateVerificationCode();

				// 이메일 전송
				sendEmail(email, verificationCode);

				// 이메일 전송 후 작업 수행
				// 예: 세션에 인증 코드 저장 등
				HttpSession session = request.getSession();
				session.setAttribute("verificationCode", verificationCode);
				session.setAttribute("verificationCodeExpiration",
						System.currentTimeMillis() + CODE_EXPIRATION_DURATION);
				session.setAttribute("userid", userid);
				session.setAttribute("email", email);

				// 이메일 인증 페이지로 이동
				request.getRequestDispatcher(url).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchPwd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String email = request.getParameter("email");
		String userCode = request.getParameter("securityCodeInput"); // 사용자가 입력한 인증번호
		String randomCode = (String) request.getSession().getAttribute("verificationCode"); // 세션에 저장된 인증번호

		if (userCode.equals(randomCode)) {
			MemberDAO mdao = MemberDAO.getInstance();
			List<MemberVO> member1 = mdao.checkMemberPwd(userid, email);

			if (!member1.isEmpty()) {
				MemberVO member = member1.get(0); // 리스트의 첫 번째 회원 정보를 가져옴
				String pwd = member.getPwd(); // 회원 아이디를 가져옴

				// JavaScript로 새로운 창을 열어서 아이디를 보여줌
				String script = "<script>alert('회원 비밀번호는 " + pwd + " 입니다.');";
				script += "window.location.href='/loginForm.do';</script>";
				response.getWriter().print(script);
			} else {
				// 해당하는 회원을 찾을 수 없는 경우에 대한 처리
				String script = "<script>alert( '해당하는 회원을 찾을 수 없습니다.');";
				request.getRequestDispatcher("/WEB-INF/jsp/member/loginForm.jsp").forward(request, response);
			}
		} else {
			// 인증번호가 일치하지 않는 경우에 대한 처리
			String script = "<script>alert('인증번호 불일치');";
			script += "window.location.href='/loginForm.do';</script>";
			response.getWriter().print(script);
		}
	}

	public void join(HttpServletRequest request, HttpServletResponse response) throws IOException {
		MemberDAO mdao = MemberDAO.getInstance();
		MemberVO mvo = new MemberVO();

		mvo.setUserid(request.getParameter("userid"));
		mvo.setPwd(request.getParameter("pwd"));
		// mvo.setGseq(Integer.parseInt(request.getParameter("gseq")));
		mvo.setName(request.getParameter("name"));
		mvo.setEmail(request.getParameter("email"));
		mvo.setZip_code(request.getParameter("zip_code"));
		mvo.setAddress(request.getParameter("address"));
		mvo.setD_address(request.getParameter("d_address"));
		mvo.setPhone(request.getParameter("phone"));

		int result = mdao.insertMember(mvo);
		HttpSession session = request.getSession();
		if (result == 1)
			session.setAttribute("message", "회원가입이 완료되었습니다. 로그인 하세요");
		else
			session.setAttribute("message", "관리자에게 문의하세요");

		response.sendRedirect("/loginForm.do");
	}

	public String joinPage(HttpServletRequest request, HttpServletResponse response) {
		return "/member/JoinPage.jsp";
	}

	public String IDCheck(HttpServletRequest request, HttpServletResponse response) {
		String userid = request.getParameter("userid");
		MemberDAO mdao = MemberDAO.getInstance();
		MemberVO mvo = mdao.getMember(userid);

		if (mvo == null)
			request.setAttribute("result", -1); // 사용 가능
		else
			request.setAttribute("result", 1); // 사용 중

		request.setAttribute("userid", userid);
		return "/member/IDCheckPage.jsp";
	}

	public String getEmail(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		   
		// 랜덤한 인증 코드 생성
		String verificationCode = generateVerificationCode();
	      
		// 이메일 전송
		sendEmail(email, verificationCode);
		  
		// 이메일 전송 후 작업 수행
		// 예: 세션에 인증 코드 저장 등
		HttpSession session = request.getSession();
		session.setAttribute("verificationCode", verificationCode);
		  
		System.out.println(verificationCode);
		  
		// 이메일 인증 페이지로 이동
		return "/member/emailVFC.jsp";
	}

	public String updateMemberForm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");

		if (mvo != null) {
			return "/member/updateMember.jsp";
		} else {
			return "/member/loginForm.jsp";
		}
	}

	public void updateMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if(mvo==null) {
			response.sendRedirect("/loginForm.do");
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
			mdao.updateMember(mvo1);
			
			session.setAttribute("loginUser", mvo1);
			
			response.sendRedirect("/index.do");
		}
	}

	public void deleteMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		
		if( mvo==null ) {
			response.sendRedirect("/loginForm.do");
		}else {
			MemberDAO mdao = MemberDAO.getInstance();
			mdao.deleteMember(mvo.getUserid());
			
			session.removeAttribute("loginUser");
			
			//session.setAttribute("message", "정상 탈퇴 처리되었습니다.");
			response.sendRedirect("/loginForm.do");
		}
	}

	public String findZipnum(HttpServletRequest request, HttpServletResponse response) {
		String dong = request.getParameter("dong");
		if(dong != null) {
			if(dong.equals("") == false) {
				MemberDAO mdao = MemberDAO.getInstance();
				ArrayList<AddressVO> list = mdao.selectAddressByDong(dong);
				request.setAttribute("addressList", list);
			}
		}
		return "/member/findZipNum.jsp";
	}

	public void findIdOK(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
        	out.print("<script>alert('인증 코드가 만료되었습니다. 다시 시도해주세요.'); window.location.href='/findIdForm.do';</script>");
            out.close();
            return;
        }

        // 사용자가 입력한 인증 코드가 세션에 저장된 인증 코드와 일치하는지 확인
        if (userSecurityCode.equals(verificationCode)) {
            out.print("<script>alert('인증되었습니다.'); window.location.href='searchId.do';</script>");
            session.removeAttribute("verificationCode"); // 세션에서 인증 코드 제거
            session.removeAttribute("verificationCodeExpiration"); // 세션에서 인증 코드 만료 시간 제거
        } else {
            out.print("<script>alert('잘못된 인증 코드입니다.'); window.location.href='/findIdForm.do';</script>");
        }
        out.close();
	}

	//
	public JSONObject switchYN(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonStr = in.readLine();
		JSONObject jsonObj = new JSONObject(jsonStr);
		AdminDAO dao = AdminDAO.getInstance();
		JSONObject jsonResult = new JSONObject();
		
		ParseList parse = new ParseList();
		List<String> useridList = parse.parseStringList(jsonObj);
		
		try {
			dao.switchYN(useridList);
			jsonResult.put("status", true);
			jsonResult.put("message", "처리되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "실패");
		}
		
		return jsonResult;
	}

	public JSONObject discardMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String jsonStr = in.readLine();
		JSONObject jsonObj = new JSONObject(jsonStr);
		AdminDAO dao = AdminDAO.getInstance();
		JSONObject jsonResult = new JSONObject();
		
		ParseList parse = new ParseList();
		List<String> useridList = parse.parseStringList(jsonObj);
		
		try {
			dao.deleteMember(useridList);
			jsonResult.put("status", true);
			jsonResult.put("message", "처리되었습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.put("status", false);
			jsonResult.put("message", "실패");
		}
		
		return jsonResult;
	}
}

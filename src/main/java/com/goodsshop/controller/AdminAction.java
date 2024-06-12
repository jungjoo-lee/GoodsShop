package com.goodsshop.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.goodsshop.dao.AdminDAO;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dao.NoticeDAO;
import com.goodsshop.dao.OrderDAO;
import com.goodsshop.dao.QnaDAO;
import com.goodsshop.dao.ReviewDAO;
import com.goodsshop.dto.AdminVO;
import com.goodsshop.dto.GoodsImageVO;
import com.goodsshop.dto.GoodsVO;
import com.goodsshop.dto.OrderVO;
import com.goodsshop.util.MPaging;
import com.goodsshop.util.Paging;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class AdminAction {
	public String adminLoginForm(HttpServletRequest request, HttpServletResponse response) {
		return "/admin/adminLoginForm.jsp";
	}

	public void adminLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AdminDAO dao = AdminDAO.getInstance();
		AdminVO vo = dao.getAdmin(request.getParameter("adminID"));
		
		if(vo == null) 
			request.setAttribute("message", "실패");
		else if(!vo.getPwd().equals(request.getParameter("pwd"))) 
			request.setAttribute("message", "실패");
		else if( vo.getPwd().equals(request.getParameter("pwd")) ) {
			HttpSession session = request.getSession();
			session.setAttribute("loginAdmin", vo);
			request.setAttribute("message", "성공");
			session.removeAttribute("loginUser");
		}
		response.sendRedirect("adminIndex.do");
	}
	
	public void adminLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("loginAdmin");
		response.sendRedirect("adminIndex.do");
	}
	
	public String adminIndex(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		AdminDAO dao = AdminDAO.getInstance();
		
		int total = dao.getTotalMember();
		int currentPage = 1;
		int amount = 10;
		
		if(session.getAttribute("currentPage") != null) {
			currentPage = (Integer)session.getAttribute("currentPage");
		}
		if(session.getAttribute("amount") != null) {
			amount = (Integer)session.getAttribute("amount");
		}
		
		Paging paging = new Paging(currentPage, amount, total);
		System.out.println(paging.toString());
		
		request.setAttribute("memberList", dao.getMemberList(paging.getAmount(), paging.getCurrentPage()));
		request.setAttribute("paging", paging);
		
		return "/admin/index.jsp";
	}
	
	public String adminQnaList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		QnaDAO dao = QnaDAO.getInstance();
		
		int total = dao.getTotalQna();
		int currentPage = 1;
		int amount = 10;
		
		if(session.getAttribute("currentPage") != null) {
			currentPage = (Integer)session.getAttribute("currentPage");
		}
		if(session.getAttribute("amount") != null) {
			amount = (Integer)session.getAttribute("amount");
		}
		
		Paging paging = new Paging(currentPage, amount, total);
		
		request.setAttribute("qnaList", dao.getQnaList(paging.getAmount(), paging.getCurrentPage()));
		request.setAttribute("paging", paging);
		return "/admin/qnaList.jsp";
	}
	
	public String adminQnaView(HttpServletRequest request, HttpServletResponse response) {
		QnaDAO dao = QnaDAO.getInstance();
		
		request.setAttribute("vo", dao.getQna(Integer.parseInt(request.getParameter("qseq"))));
		return "/admin/qnaView.jsp";
	}
	
	public void qnaReplyWrite(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AdminDAO dao = AdminDAO.getInstance();
		int qseq = Integer.parseInt(request.getParameter("qseq"));
		String reply = request.getParameter("reply");
		
		dao.writeUpdateReply(reply, qseq);
		
		response.sendRedirect("adminQnaView.do?qseq=" + qseq);
	}

	public void qnaReplyUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AdminDAO dao = AdminDAO.getInstance();
		
		int qseq = Integer.parseInt(request.getParameter("qseq"));
		String reply = request.getParameter("reply");

		dao.writeUpdateReply(reply, qseq);
		
		response.sendRedirect("adminQnaView.do?qseq=" + qseq);
	}
	
	public void qnaReplyDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AdminDAO dao = AdminDAO.getInstance();
		int qseq = Integer.parseInt(request.getParameter("qseq"));
		
		dao.deleteReply(qseq);
		
		response.sendRedirect("adminQnaView.do?qseq=" + qseq);
	}

	public String adminNoticeList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		AdminDAO dao = AdminDAO.getInstance();
		
		int total = dao.getTotalNotice();
		int currentPage = 1;
		int amount = 10;
		
		if(session.getAttribute("currentPage") != null) {
			currentPage = (Integer)session.getAttribute("currentPage");
		}
		if(session.getAttribute("amount") != null) {
			amount = (Integer)session.getAttribute("amount");
		}
		
		Paging paging = new Paging(currentPage, amount, total);
		
		request.setAttribute("noticeList", dao.getNoticeList(paging.getAmount(), paging.getCurrentPage()));
		request.setAttribute("paging", paging);
		return "/admin/noticeList.jsp";
	}
	
	public String adminNoticeView(HttpServletRequest request, HttpServletResponse response) {
		NoticeDAO dao = NoticeDAO.getInstance();
		request.setAttribute("vo", dao.getNotice(Integer.parseInt(request.getParameter("nseq"))));
		
		return "/admin/noticeView.jsp";
	}
	
	public String adminReviewList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ReviewDAO dao = ReviewDAO.getInstance();
		
		int total = dao.getTotalReview();
		int currentPage = 1;
		int amount = 10;
		
		if(session.getAttribute("currentPage") != null) {
			currentPage = (Integer)session.getAttribute("currentPage");
		}
		if(session.getAttribute("amount") != null) {
			amount = (Integer)session.getAttribute("amount");
		}
		
		Paging paging = new Paging(currentPage, amount, total);
		
		request.setAttribute("reviewList", dao.getReviewList(paging.getAmount(), paging.getCurrentPage()));
		request.setAttribute("paging", paging);
		
		return "/admin/reviewList.jsp";
	}
	
	//admin-goods
	public void adminGoodsView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminVO loginAdmin = (AdminVO)session.getAttribute("loginAdmin");
		String url = "";
		
		if(loginAdmin == null) {
			url = "/adminLoginForm.do";
		} else {
			
			int page = 1;
			
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if(session.getAttribute("page") != null) {
				page = (Integer)session.getAttribute("page");
			} else {
				session.removeAttribute("page");
			}
			
			MPaging paging = new MPaging();
			paging.setPage(page);
			paging.setDisplayRow(10);
				
			GoodsDAO gdao = new GoodsDAO();
			int count = gdao.getAllCount("", "cgseq");
			paging.setTotalCount(count);			
			
			List<GoodsVO> adminGoodsList = gdao.getAllGoods("", paging);
			
			for (GoodsVO gvo : adminGoodsList) {
				GoodsDAO gdao1 = new GoodsDAO();
				List<GoodsImageVO> bestImageList = gdao1.getImageList(gvo.getGseq());
				gvo.setImageList(bestImageList);
			}
			
			System.out.println(paging);

			request.setAttribute("url", "adminGoodsView.do");				
			request.setAttribute("adminGoodsList", adminGoodsList);	
			request.setAttribute("paging", paging);		
			
			url = "/WEB-INF/jsp/admin/adminGoodsView.jsp";			
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	public void adminGoodsUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminVO loginAdmin = (AdminVO)session.getAttribute("loginAdmin");
		String url = "";
		
		if(loginAdmin == null) {
			url = "/adminLoginForm.do";		
		} else {		
			
			int gseq = Integer.parseInt(request.getParameter("gseq"));
			GoodsDAO gdao = new GoodsDAO();
			GoodsVO gvo = gdao.getGoods(gseq);
			
			List<GoodsImageVO> bestImageList = gdao.getImageList(gvo.getGseq());
			gvo.setImageList(bestImageList);
			String thum = gdao.getThumbnail(gvo.getGseq());
			gvo.setThum(thum);
			
			List<GoodsVO> categoryList = gdao.getAllCategories();
			request.setAttribute("categoryList", categoryList);
		
			request.setAttribute("updateGoods", gvo);
			
			url = "/WEB-INF/jsp/admin/adminGoodsWriteForm.jsp";
		}	
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	public void adminUpdateGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		GoodsVO gvo = new GoodsVO();
		gvo.setGseq(Integer.parseInt(request.getParameter("gseq")));
		gvo.setGname(request.getParameter("gname"));
		gvo.setCgseq(Integer.parseInt(request.getParameter("cgseq")));
		gvo.setOprice(Integer.parseInt(request.getParameter("oprice")));
		gvo.setSprice(Integer.parseInt(request.getParameter("sprice")));
		gvo.setMprice(Integer.parseInt(request.getParameter("mprice")));
		gvo.setContent(request.getParameter("content"));
		gvo.setBestyn(Integer.parseInt(request.getParameter("bestyn")));
		gvo.setUseyn(Integer.parseInt(request.getParameter("useyn")));

		GoodsDAO gdao = new GoodsDAO();
		gdao.updateGoods(gvo);

		String[] giseqs = request.getParameterValues("giseq");
		
		if(giseqs != null) {
			//체크박스에서 사용에 체크한 이미지가 있다면
			//해당 giseq들만 빼고 나머지를 gseq로 조회하여 테이블에서 레코드를 삭제
			//파일도 삭제
			gdao.deleteGoodsImages(giseqs, gvo.getGseq());							
		} else {
			//사용에 체크한 이미지가 없다면
			//gseq로 조회하여 테이블에서 전체 레코드 삭제
			gdao.deleteGoodsImages(gvo.getGseq());
		}
		
		//파일업로드
		String uploadPath = "C:\\upload\\" + gvo.getGseq() + gvo.getGname() + "\\";
		File uploadDir;

		String oriname = "";
		String realname = "";

		for (Part p : request.getParts()) {
			oriname = "";
			long fileSize = 0;

			for (String content : p.getHeader("content-disposition").split(";")) {
				if (content.trim().startsWith("filename")) {
					oriname = content.substring(content.indexOf("=") + 2, content.length() - 1);

					if (!oriname.equals("")) {
						realname = String.valueOf(System.currentTimeMillis());
						uploadDir = new File(uploadPath);

						if (!uploadDir.exists()) {
							uploadDir.mkdir();
						}

						// 파일 크기
						InputStream inputStream = p.getInputStream();
						ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
						byte[] buffer = new byte[4096];
						int bytesRead;
						while ((bytesRead = inputStream.read(buffer)) != -1) {
							byteArrayOutputStream.write(buffer, 0, bytesRead);
						}
						byte[] fileBytes = byteArrayOutputStream.toByteArray();
						fileSize = fileBytes.length;
						System.out.println("File Size: " + fileSize + " bytes");

						// 파일 저장
						p.write(uploadPath + realname);
						
						
						//파일 저장한 정보를 givo 에 담기
						GoodsImageVO givo = new GoodsImageVO();
						givo.setOriname(oriname);
						givo.setRealname(realname);
						givo.setFilesize(fileSize);
						givo.setGseq(gvo.getGseq());
						gdao.writeGoodsImages(givo, gvo.getGseq());
					}
				}
			}		
		}
		
		response.sendRedirect("adminGoodsView.do");
	}
	
	public void adminInsertGoodsForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminVO loginAdmin = (AdminVO)session.getAttribute("loginAdmin");
		String url = "";
		
		if(loginAdmin == null) {
			url = "/adminLoginForm.do";		
		} else {		
			
			GoodsDAO gdao = new GoodsDAO();
			List<GoodsVO> categoryList = gdao.getAllCategories();
			request.setAttribute("categoryList", categoryList);			
			
			url = "/WEB-INF/jsp/admin/adminGoodsWriteForm.jsp";
		}	
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	public void adminInsertGoods(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		GoodsVO gvo = new GoodsVO();
		gvo.setGname(request.getParameter("gname"));
		gvo.setCgseq(Integer.parseInt(request.getParameter("cgseq")));
		gvo.setOprice(Integer.parseInt(request.getParameter("oprice")));
		gvo.setSprice(Integer.parseInt(request.getParameter("sprice")));
		gvo.setMprice(Integer.parseInt(request.getParameter("mprice")));
		gvo.setContent(request.getParameter("content"));
		gvo.setBestyn(Integer.parseInt(request.getParameter("bestyn")));
		gvo.setUseyn(Integer.parseInt(request.getParameter("useyn")));

		GoodsDAO gdao = new GoodsDAO();
		gdao.insertGoods(gvo);

		//직전에 추가한 goods 테이블의 gseq 가져오기
		int gseq = gdao.lookupMaxGseq();
		
		// 파일업로드
		String uploadPath = "C:\\upload\\" + gseq + gvo.getGname() + "\\";
		File uploadDir;
		String oriname = "";
		String realname = "";

		for (Part p : request.getParts()) {
			oriname = "";
			long fileSize = 0;

			for (String content : p.getHeader("content-disposition").split(";")) {
				if (content.trim().startsWith("filename")) {
					oriname = content.substring(content.indexOf("=") + 2, content.length() - 1);

					if (!oriname.equals("")) {
						realname = String.valueOf(System.currentTimeMillis());
						uploadDir = new File(uploadPath);

						if (!uploadDir.exists()) {
						    // 기존 mkdir() 대신 mkdirs() 사용
						    if (!uploadDir.mkdirs()) {
						        throw new IOException("Failed to create directory: " + uploadPath);
						    }
						}

						// 파일 크기
						InputStream inputStream = p.getInputStream();
						ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
						byte[] buffer = new byte[4096];
						int bytesRead;
						while ((bytesRead = inputStream.read(buffer)) != -1) {
							byteArrayOutputStream.write(buffer, 0, bytesRead);
						}
						byte[] fileBytes = byteArrayOutputStream.toByteArray();
						fileSize = fileBytes.length;
						System.out.println("File Size: " + fileSize + " bytes");

						// 파일 저장
						try {
		                    p.write(uploadPath + realname);
		                } catch (IOException e) {
		                    throw new IOException("Error writing file: " + uploadPath + realname, e);
		                }

						// 파일 저장한 정보를 givo 에 담기
						GoodsImageVO givo = new GoodsImageVO();
						givo.setOriname(oriname);
						givo.setRealname(realname);
						givo.setFilesize(fileSize);
						givo.setGseq(gseq);

						//goodsimage 테이블에 새롭게 업로드한 레코드 추가
						gdao.writeGoodsImages(givo, gseq);
					}
				}
			}
		}
		response.sendRedirect("adminGoodsView.do");
	}
	
	public void adminGoodsDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] gseqs = request.getParameterValues("gseq");
		GoodsDAO gdao = new GoodsDAO();
		
		for (String gseq : gseqs) {
			gdao.deleteGoods(gseq);
		}
		
		response.sendRedirect("adminGoodsView.do");
	}
	
	public void adminBestToggle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] gseqs = request.getParameterValues("gseq");
		GoodsDAO gdao = new GoodsDAO();
		
		for (String gseq : gseqs) {
			gdao.bestToggle(gseq);
		}
		
		response.sendRedirect("adminGoodsView.do");
	}
	
	public void adminUseYnToggle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] gseqs = request.getParameterValues("gseq");
		GoodsDAO gdao = new GoodsDAO();
		
		for (String gseq : gseqs) {
			gdao.useYnToggle(gseq);
		}
		
		response.sendRedirect("adminGoodsView.do");
	}
	
	public void adminCategoryView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminVO loginAdmin = (AdminVO)session.getAttribute("loginAdmin");
		String cgseq = request.getParameter("selectCategory");
		
		String url = "";		
		
		if(loginAdmin == null) {
			url = "/adminLoginForm.do";
		} else {
			
			int page = 1;
			
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if(session.getAttribute("page") != null) {
				page = (Integer)session.getAttribute("page");
			} else {
				session.removeAttribute("page");
			}
			
			MPaging paging = new MPaging();
			paging.setPage(page);
			paging.setDisplayRow(10);
			
			GoodsDAO gdao = new GoodsDAO();
			int count = gdao.getAllCount(cgseq, "g1.cgseq");
			paging.setTotalCount(count);
			
			List<GoodsVO> adminGoodsList = gdao.getCategoryList(cgseq, paging);
			
			for (GoodsVO gvo : adminGoodsList) {
				GoodsDAO gdao1 = new GoodsDAO();
				List<GoodsImageVO> bestImageList = gdao1.getImageList(gvo.getGseq());
				gvo.setImageList(bestImageList);
			}
			
			List<GoodsVO> categoryList = gdao.getAllCategories();
			
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("paging", paging);		
			request.setAttribute("cgseq", cgseq);
			request.setAttribute("adminGoodsList", adminGoodsList);	
			request.setAttribute("loginAdmin", loginAdmin);
			request.setAttribute("url", "adminCategoryView.do");		
			
			System.out.println(paging);
			
			url = "/WEB-INF/jsp/admin/adminGoodsView.jsp";								
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
	
	public void adminGoodsSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminVO loginAdmin = (AdminVO)session.getAttribute("loginAdmin");
		String keyword = request.getParameter("searchKey");
		
		String url = "";
		
		if(loginAdmin == null) {
			url = "/adminLoginForm.do";
		} else {
			
			int page = 1;
			
			if(request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if(session.getAttribute("page") != null) {
				page = (Integer)session.getAttribute("page");
			} else {
				session.removeAttribute("page");
			}
			
			MPaging paging = new MPaging();
			paging.setPage(page);
			paging.setDisplayRow(10);
			
			GoodsDAO gdao = new GoodsDAO();
			int count = gdao.getAllCount(keyword , "g1.gseq");
			paging.setTotalCount(count);			
			
			List<GoodsVO> adminGoodsList = gdao.getAllGoods(keyword, paging);
			
			for (GoodsVO gvo : adminGoodsList) {
				GoodsDAO gdao1 = new GoodsDAO();
				List<GoodsImageVO> bestImageList = gdao1.getImageList(gvo.getGseq());
				gvo.setImageList(bestImageList);
			}
			
			System.out.println(paging);
			
			request.setAttribute("adminGoodsList", adminGoodsList);	
			request.setAttribute("key", keyword);
			request.setAttribute("paging", paging);				
			request.setAttribute("url", "adminGoodsSearch.do");			
			
			url = "/WEB-INF/jsp/admin/adminGoodsView.jsp";								
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
	
	//admin-order
	public void adminOrderView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String key = "";
		
		if (request.getParameter("key") != null) {
			key = request.getParameter("key");
			session.setAttribute("key", key);
		} else if (session.getAttribute("key") != null) {
			key = (String) session.getAttribute("key");
		} else {
			session.removeAttribute("key");
		}
		
		int page = 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
		} else if(session.getAttribute("page") != null) {
			page = (Integer)session.getAttribute("page");
		} else {
			session.removeAttribute("page");
		}
		
		MPaging paging = new MPaging();
		paging.setPage(page);
		
		OrderDAO odao = new OrderDAO();
		int count = odao.getAllCount("", "oseq");
		paging.setTotalCount(count);
		
		List<OrderVO> orderList = odao.getAllOrderList("", paging);
		
		
		request.setAttribute("orderList", orderList);
		request.setAttribute("url", "adminOrderView.do");		
		request.setAttribute("paging", paging);		
		request.getRequestDispatcher("/WEB-INF/jsp/admin/adminOrderView.jsp").forward(request, response);
	}
	
	public void adminOrderDetailView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int oseq = Integer.parseInt(request.getParameter("oseq"));
		
		OrderDAO odao = new OrderDAO();
		List<OrderVO> orderDetailList = odao.selectOrderDetail(oseq);
		
		for (OrderVO ovo : orderDetailList) {
			GoodsDAO gdao = new GoodsDAO();
			ovo.setThum(gdao.getThumbnail(ovo.getGseq()));
		}
		
		request.setAttribute("orderDetailList", orderDetailList);	
		request.getRequestDispatcher("/WEB-INF/jsp/admin/adminOrderDetail.jsp").forward(request, response);
	}
	
	public void adminUpdateOrderStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] oseqs = request.getParameterValues("oseq");
		String osseq = request.getParameter("osseq");
		OrderDAO odao = new OrderDAO();
		
		for(String oseq : oseqs) {
			odao.updateOrderStatus(oseq, osseq);
		}
		
		response.sendRedirect("adminOrderView.do");
	}
	
	public void adminSearchOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String searchBy = request.getParameter("searchBy");
		String key = request.getParameter("searchKey");
		OrderDAO odao = new OrderDAO();
		
		List<OrderVO> orderList = new ArrayList<OrderVO>();
		
		int page = 1;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
		} else if(session.getAttribute("page") != null) {
			page = (Integer)session.getAttribute("page");
		} else {
			session.removeAttribute("page");
		}
		
		MPaging paging = new MPaging();
		paging.setPage(page);
		
		int count = 0;
		String url = "adminSearchOrder.do";
		
		 if (searchBy.equals("gname")) {
			count = odao.getAllCount(key, "gname");
			paging.setTotalCount(count);
			orderList = odao.getAllOrderList(key, paging);
			
		} else if (searchBy.equals("userid")) {
			count = odao.getAllCount(key, "userid");
			paging.setTotalCount(count);
			orderList = odao.getAllOrderListById(key, paging);		
			
		} else if (searchBy.equals("name")) {
			count = odao.getAllCount(key, "name");
			paging.setTotalCount(count);			
			orderList = odao.getAllOrderListByName(key, paging);
			
		}
		
		request.setAttribute("orderList", orderList);
		request.setAttribute("searchBy", searchBy);
		request.setAttribute("key", key);
		request.setAttribute("url", url);
		request.setAttribute("paging", paging);		
		request.getRequestDispatcher("/WEB-INF/jsp/admin/adminOrderView.jsp").forward(request, response);
	}
}

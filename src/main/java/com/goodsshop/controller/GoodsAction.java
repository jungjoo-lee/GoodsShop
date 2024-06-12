package com.goodsshop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.goodsshop.dao.CartDAO;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dao.OrderDAO;
import com.goodsshop.dao.ReviewDAO;
import com.goodsshop.dto.CartVO;
import com.goodsshop.dto.GoodsImageVO;
import com.goodsshop.dto.GoodsVO;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.dto.OrderVO;
import com.goodsshop.dto.ReviewVO;
import com.goodsshop.util.MPaging;
import com.goodsshop.util.Paging;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoodsAction {
	public String goodsDetailView(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		int gseq = Integer.parseInt(request.getParameter("gseq"));
		
		GoodsDAO gdao = new GoodsDAO();
		GoodsVO gvo = gdao.getGoods(gseq);
		List<GoodsImageVO> image = gdao.getImageList(gseq);
		gvo.setImageList(image);
		
		if (loginUser != null) {
			int oldPrice = gvo.getSprice();		
			int newPrice = 0;
			
			newPrice = (int)Math.ceil(oldPrice - (oldPrice * loginUser.getSale()));
			
			gvo.setSprice(newPrice);
		}	
		
		ReviewDAO rDAO = ReviewDAO.getInstance();
		int total = rDAO.getGoodsReviewTotal(gseq);
		int currentPage = 1;
		
		Paging paging = new Paging(currentPage, 10, total);
		List<ReviewVO> reviewList =  rDAO.getGoodsReviewList(gseq, 10, paging.getCurrentPage());

		request.setAttribute("goodsDetail", gvo);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("paging", paging);
		return "/goods/goodsDetail.jsp";
	}
	
	public void viewCartlist(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();	
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		CartDAO cdao = new CartDAO();

		if (loginUser == null) {
			PrintWriter out = response.getWriter();			
			out.print("<script>alert('로그인을 먼저 진행해주세요');</script>");
			out.print("<script>location.href='loginForm.do';</script>");
		} else {
			
			List<CartVO> wishlist = null;
			String userid = loginUser.getUserid();
			
			wishlist = cdao.getWishList(userid);
			
			for( CartVO cvo : wishlist) {				
				int oldPrice = cvo.getSprice();		
				int newPrice = 0;
				newPrice = (int)Math.ceil(oldPrice - (oldPrice * loginUser.getSale()));
				
				cvo.setSprice(newPrice);
			}
			
			request.setAttribute("wishlist", wishlist);
			request.getRequestDispatcher("/WEB-INF/jsp/goods/wishlistView.jsp").forward(request, response);
		}
	}
	
	public void addCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		List<CartVO> cartlist = null;
		
		if (session.getAttribute("cartlist") == null) {
			cartlist = new ArrayList<CartVO>();
		} else {
			cartlist = (List<CartVO>)session.getAttribute("cartlist");
		}
		
		
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		int quantity = Integer.parseInt(request.getParameter("input_quantity")) ;
		int gseq = Integer.parseInt(request.getParameter("gseq"));
		
		GoodsDAO gdao = new GoodsDAO();
		GoodsVO gvo = gdao.getGoods(gseq);

		CartVO result = cartlist.stream().filter(obj -> obj.getGseq() == gseq).findFirst().orElse(null);
			
		CartVO cvo = new CartVO();
		cvo.setUserid(mvo.getUserid());
		cvo.setUsername(mvo.getName());
		cvo.setQuantity(quantity);
		cvo.setGseq(gvo.getGseq());
		cvo.setGoodsname(gvo.getGname());
		cvo.setSprice(gvo.getSprice());		
		cvo.setTotalprice(gvo.getSprice() * quantity);
		cvo.setRealname(gvo.getRealname());
		
		
		if(result != null) {
			int oldQuantity = result.getQuantity();
			result.setQuantity(oldQuantity + quantity);
		} else {
			cartlist.add(cvo);
		}
		session.setAttribute("cartlist", cartlist);
		
		response.sendRedirect("viewCartlist.do");
	}
	
	public void deleteCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		List<CartVO> cartlist = (List<CartVO>)session.getAttribute("cartlist");
		
		String [] gseqs = request.getParameterValues("gseq");
		
		
		for(String gseq : gseqs) {
			int gseqInt = Integer.parseInt(gseq);
			
			CartVO deleteTo = cartlist.stream()
					.filter(obj -> obj.getGseq() == gseqInt)
					.findFirst().orElse(null);
			
			cartlist.remove(deleteTo);
			
		}	

		session.setAttribute("cartlist", cartlist);
		response.sendRedirect("viewCartlist.do");
	}
	
	public void viewWishlist(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();	
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		CartDAO cdao = new CartDAO();

		if (loginUser == null) {
			PrintWriter out = response.getWriter();			
			out.print("<script>alert('로그인을 먼저 진행해주세요');</script>");
			out.print("<script>location.href='loginForm.do';</script>");
		} else {
			
			List<CartVO> wishlist = null;
			String userid = loginUser.getUserid();
			
			wishlist = cdao.getWishList(userid);
			
			for( CartVO cvo : wishlist) {				
				int oldPrice = cvo.getSprice();		
				int newPrice = 0;
				newPrice = (int)Math.ceil(oldPrice - (oldPrice * loginUser.getSale()));
				
				cvo.setSprice(newPrice);
			}
			request.setAttribute("wishlist", wishlist);
			request.getRequestDispatcher("/WEB-INF/jsp/goods/wishlistView.jsp").forward(request, response);		
		}
	}
	
	public void addWish(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		String userid = loginUser.getUserid();

		int gseq = Integer.parseInt(request.getParameter("gseq"));
		
		CartDAO cdao = new CartDAO();
		GoodsDAO gdao = new GoodsDAO();
		GoodsVO gvo = gdao.getGoods(gseq);
		
		int cnt = cdao.selectWish(userid, gvo);
		
		if( cnt != 0 ) {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('이미 찜목록에 동일한 상품이 존재합니다.');</script>");
			out.print("<script>history.go(-1);</script>");

		} else {		
			cdao.insertWish(userid, gvo);		
			response.sendRedirect("viewWishlist.do");
		}
	}

	public void wishToCart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		List<CartVO> cartlist = new ArrayList<CartVO>();
		String [] gseqs = request.getParameterValues("gseq");
		
		for(String gseq : gseqs) {
			int gseqInt = Integer.parseInt(gseq);
			
			GoodsDAO gdao = new GoodsDAO();
			GoodsVO gvo = gdao.getGoods(gseqInt);

			CartVO cvo = new CartVO();
			cvo.setUserid(mvo.getUserid());
			cvo.setUsername(mvo.getName());
			cvo.setQuantity(1);
			cvo.setGseq(gvo.getGseq());
			cvo.setGoodsname(gvo.getGname());
			cvo.setSprice(gvo.getSprice());
			cvo.setTotalprice(gvo.getSprice());
			cvo.setThum(gdao.getThumbnail(gseqInt));
			
			cartlist.add(cvo);		
			CartDAO cdao = new CartDAO();
			cdao.deleteWish(gseqInt);
		}	
		session.setAttribute("cartlist", cartlist);
		response.sendRedirect("viewCartlist.do");
	}
	
	public void deleteWish(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String [] gseqs = request.getParameterValues("gseq");
		
		for(String gseq : gseqs) {
			int gseqInt = Integer.parseInt(gseq);
			
			CartDAO cdao = new CartDAO();
			cdao.deleteWish(gseqInt);
			
		}			

		response.sendRedirect("viewWishlist.do");
	}
	
	public void viewOrderList(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");

		if (mvo == null) {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('로그인을 먼저 진행해주세요');</script>");
			out.print("<script>location.href='loginForm.do';</script>");
		} else {

			int page = 1;

			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if (session.getAttribute("page") != null) {
				page = (Integer) session.getAttribute("page");
			} else {
				session.removeAttribute("page");
			}

			MPaging paging = new MPaging();
			paging.setPage(page);

			OrderDAO odao = new OrderDAO();
			int count = odao.getAllCount(mvo.getUserid(), "userid");
			paging.setTotalCount(count);

			List<OrderVO> orderList = odao.selectOrderList(mvo.getUserid(), paging);

			request.setAttribute("orderList", orderList);
			request.setAttribute("paging", paging);
			request.getRequestDispatcher("WEB-INF/jsp/mypage/orderlistView.jsp").forward(request, response);
		}
	}
	
	public void orderFromCart(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		if (mvo == null) {
			PrintWriter out = response.getWriter();			
			out.print("<script>alert('로그인을 먼저 진행해주세요');</script>");
			out.print("<script>location.href='loginForm.do';</script>");
		} else {
			String[] gseqs = request.getParameterValues("gseq");
			String[] quantity = request.getParameterValues("quantity");		
			List<OrderVO> list = new ArrayList<OrderVO>();
			
			for(int i = 0; i<gseqs.length; i++) {
				int gseqInt = Integer.parseInt(gseqs[i]);
				
				GoodsDAO gdao = new GoodsDAO();
				GoodsVO gvo = gdao.getGoods(gseqInt);
				gvo.setThum(gdao.getThumbnail(gseqInt));		
				
				OrderVO ovo = new OrderVO();
				ovo.setUserid(mvo.getUserid());
				ovo.setName(mvo.getName());
				ovo.setZipcode(mvo.getZip_code());
				ovo.setAddress(mvo.getAddress());
				ovo.setDaddress(mvo.getD_address());
				ovo.setPhone(mvo.getPhone());
				ovo.setGseq(gseqInt);
				ovo.setGname(gvo.getGname());
				ovo.setQuantity(Integer.parseInt(quantity[i]));
				ovo.setRealname(gvo.getRealname());
				
				int oldPrice = gvo.getSprice();		
				int newPrice = 0;
				
				newPrice = (int)Math.ceil(oldPrice - (oldPrice * mvo.getSale()));
				
				gvo.setSprice(newPrice);
				
				ovo.setTotalprice(gvo.getSprice() * 1);
				ovo.setThum(gdao.getThumbnail(gseqInt));
				
				list.add(ovo);		
			}
		
			session.setAttribute("orderProductList", list);
			request.getRequestDispatcher("WEB-INF/jsp/goods/orderPage.jsp").forward(request, response);
		}
	}
	
	public void orderNow(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		if (mvo == null) {
			PrintWriter out = response.getWriter();			
			out.print("<script>alert('로그인을 먼저 진행해주세요');</script>");
			out.print("<script>location.href='loginForm.do';</script>");
		} else {
			int gseq = Integer.parseInt(request.getParameter("gseq")); 
			GoodsDAO gdao = new GoodsDAO();
			GoodsVO gvo = gdao.getGoods(gseq);
			gvo.setThum(gdao.getThumbnail(gseq));
			
			List<OrderVO> list = new ArrayList<OrderVO>();
			
			OrderVO ovo = new OrderVO();
			ovo.setUserid(mvo.getUserid());
			ovo.setName(mvo.getName());
			ovo.setZipcode(mvo.getZip_code());
			ovo.setAddress(mvo.getAddress());
			ovo.setDaddress(mvo.getD_address());
			ovo.setPhone(mvo.getPhone());
			ovo.setGseq(gseq);
			ovo.setGname(gvo.getGname());
			ovo.setQuantity(1);
			ovo.setRealname(gvo.getRealname());
			
			int oldPrice = gvo.getSprice();		
			int newPrice = 0;
			
			newPrice = (int)Math.ceil(oldPrice - (oldPrice * mvo.getSale()));
			
			gvo.setSprice(newPrice);
			
			ovo.setTotalprice(gvo.getSprice() * 1);
			ovo.setThum(gdao.getThumbnail(gseq));

			list.add(ovo);
			
			session.setAttribute("orderProductList", list);
			request.getRequestDispatcher("/WEB-INF/jsp/goods/orderPage.jsp").forward(request, response);
		}
	}
	
	public String getPayment(HttpServletRequest request, HttpServletResponse response) {
		int numberOfGoods = Integer.parseInt(request.getParameter("numberOfGoods"));
		int totalPrice = Integer.parseInt(request.getParameter("orderTotalPrice"));
		
		return "/goods/paymentPage.jsp";
	}
	
	public void goOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		List<OrderVO> orderProductList = (List<OrderVO>)session.getAttribute("orderProductList");
		List<CartVO> cartlist = (List<CartVO>)session.getAttribute("cartlist");
		
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		OrderDAO odao = new OrderDAO();
		
		odao.insertOrder(mvo.getUserid());
		
		int oseq = odao.lookupMaxOseq(mvo.getUserid());

		for(OrderVO ovo : orderProductList) {
			OrderDAO odao1 = new OrderDAO();
			GoodsDAO gdao = new GoodsDAO();
			odao1.insertOrderDetail(ovo, oseq);
			
			if(cartlist != null) {
				CartVO deleteTo = cartlist.stream()
						.filter(obj -> obj.getGseq() == ovo.getGseq())
						.findFirst().orElse(null);
				
				cartlist.remove(deleteTo);	
			}
		}

		session.setAttribute("cartlist", cartlist);
		session.removeAttribute("orderProductList");
		response.sendRedirect("viewOrderList.do");
	}
	
	public String orderDetailView(HttpServletRequest request, HttpServletResponse response) {
		int oseq = Integer.parseInt(request.getParameter("oseq"));
		
		OrderDAO odao = new OrderDAO();
		List<OrderVO> orderDetailList = odao.selectOrderDetail(oseq);
		
		request.setAttribute("orderDetailList", orderDetailList);	
		return "/mypage/orderDetailView.jsp";
	}

	public String viewCategory(HttpServletRequest request, HttpServletResponse response) {
		String cgseq = request.getParameter("cgseq");
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
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
		paging.setDisplayRow(20);
		
		GoodsDAO gdao = new GoodsDAO();
		int count = gdao.getAllCount("cgseq", cgseq);
		paging.setTotalCount(count);
		
		List<GoodsVO> categoryList = new ArrayList<GoodsVO>();
		
		if(cgseq.equals("0")) {
			//best 50
			categoryList = gdao.getBestList();					
		} else {
			//나머지 카테고리들
			categoryList = gdao.getCategoryList(cgseq, paging);			
		}
		
		for (GoodsVO vo : categoryList) {
			GoodsDAO gdao1 = new GoodsDAO();

			List<GoodsImageVO> categoryImageList = gdao1.getImageList(vo.getGseq());
			vo.setImageList(categoryImageList);

			// 사용자별 가격표시
			if (loginUser != null) {
				int oldPrice = vo.getSprice();
				int newPrice = 0;

				newPrice = (int) Math.ceil(oldPrice - (oldPrice * loginUser.getSale()));

				vo.setSprice(newPrice);
			}
		}

		request.setAttribute("categoryList", categoryList);
		request.setAttribute("cgseq", cgseq);
		request.setAttribute("paging", paging);			
		return "/goods/categoryView.jsp";
	}
	
	public String searchGoods(HttpServletRequest request, HttpServletResponse response) {
		String key = request.getParameter("key");
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
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
		paging.setDisplayRow(20);
		
		GoodsDAO gdao = new GoodsDAO();
		int count = gdao.getAllCount("gseq", "");
		paging.setTotalCount(count);
		
		List<GoodsVO> searchList = new ArrayList<GoodsVO>();
		searchList = gdao.getAllGoods(key, paging);

		for (GoodsVO vo : searchList) {
				GoodsDAO gdao1 = new GoodsDAO();

				List<GoodsImageVO> searchImageList = gdao1.getImageList(vo.getGseq());
				vo.setImageList(searchImageList);

				// 사용자별 가격표시
				if (loginUser != null) {
					int oldPrice = vo.getSprice();		
					int newPrice = 0;
					
					newPrice = (int)Math.ceil(oldPrice - (oldPrice * loginUser.getSale()));
					
					vo.setSprice(newPrice);
				}
		}
		
		request.setAttribute("categoryList", searchList);
		request.setAttribute("paging", paging);
		
		return "/goods/categoryView.jsp";
	}
}

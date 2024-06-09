package com.goodsshop.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dao.NoticeDAO;
import com.goodsshop.dao.QnaDAO;
import com.goodsshop.dao.ReviewDAO;
import com.goodsshop.dto.CartVO;
import com.goodsshop.dto.GoodsImageVO;
import com.goodsshop.dto.GoodsVO;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.util.ImageUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MainAction {
	private static Map<String, String> imageBufferMap = new HashMap<>();
	
	public String mainForm(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		NoticeDAO nDAO = NoticeDAO.getInstance();
		QnaDAO qDAO = QnaDAO.getInstance();
		ReviewDAO rDAO = ReviewDAO.getInstance();
		GoodsDAO gdao = new GoodsDAO();
		
		request.setAttribute("noticeList", nDAO.getMainNoticeList());
		request.setAttribute("qnaList", qDAO.getMainQnaList());
		request.setAttribute("reviewList", rDAO.getMainReviewList());
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		List<GoodsVO> bestlist = gdao.getBestList();
		List<GoodsVO> newlist = gdao.getNewList();
		
		for(GoodsVO vo : bestlist) {
			GoodsDAO gdao1 = new GoodsDAO();
			
			List<GoodsImageVO> bestImageList = gdao1.getImageList(vo.getGseq());
			vo.setImageList(bestImageList);
			String thum = gdao1.getThumbnail(vo.getGseq());
			vo.setThum(thum);
			
			if (loginUser != null) {
				int oldPrice = vo.getSprice();		
				int newPrice = 0;
				
				newPrice = (int)Math.ceil(oldPrice - (oldPrice * loginUser.getSale()));
				
				vo.setSprice(newPrice);
			}		
		}
		
		for(GoodsVO vo : newlist) {
			GoodsDAO gdao1 = new GoodsDAO();
			List<GoodsImageVO> newImageList = gdao1.getImageList(vo.getGseq());
			vo.setImageList(newImageList);
			String thum = gdao1.getThumbnail(vo.getGseq());
			vo.setThum(thum);
			
			if (loginUser != null) {
				int oldPrice = vo.getSprice();		
				int newPrice = 0;
				
				newPrice = (int)Math.ceil(oldPrice - (oldPrice * loginUser.getSale()));
				
				vo.setSprice(newPrice);
			}
		}
		
        if (session.getAttribute("imageMap") == null) {
			loadImageIntoBuffer(bestlist);
	        loadImageIntoBuffer(newlist);
        }
		
		List<CartVO> cartlist = new ArrayList<CartVO>();
		
		request.setAttribute("bestlist", bestlist);
		request.setAttribute("newlist", newlist);
		session.setAttribute("imageMap", imageBufferMap);
		
		if(request.getAttribute("loginUser") != null) {
			session.setAttribute("cartlist", cartlist);			
		}
		
		return "/index/main.jsp";
	}
	
	private void loadImageIntoBuffer(List<GoodsVO> list) throws ServletException {
        String imagePath = "c:\\upload\\";

        for (GoodsVO vo : list) {
            String folder = String.valueOf(vo.getGseq()) + vo.getGname();
            
            for (GoodsImageVO imageVO : vo.getImageList()) {
                String key = folder + imageVO.getRealname();

                if (!imageBufferMap.containsKey(key)) {
                	try {
	        			InputStream inputStream = new FileInputStream(new File(imagePath + folder + "\\" + imageVO.getRealname()));
	        			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	        			
	        			int bytesRead;
	                    byte[] data = new byte[1024];
	                    while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
	                        buffer.write(data, 0, bytesRead);
	                    }
	                    byte[] imageBytes = buffer.toByteArray();
                        String encodedImage = ImageUtils.encodeImage(imageBytes);
                        imageBufferMap.put(key, encodedImage);
                	} catch (IOException e) {
                        e.printStackTrace();
                        throw new ServletException("Failed to load image: " + key, e);
                    }
                }
            }
        }
    }
}

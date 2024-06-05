package com.goodsshop.controller.action.admin.goods;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.goodsshop.controller.action.Action;
import com.goodsshop.dao.GoodsDAO;
import com.goodsshop.dto.GoodsImageVO;
import com.goodsshop.dto.GoodsVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class AdminUpdateGoodsAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		
		response.sendRedirect("gshop.do?command=adminGoodsView");
		
	}
}

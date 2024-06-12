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

public class AdminInsertGoodsAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
	}
}

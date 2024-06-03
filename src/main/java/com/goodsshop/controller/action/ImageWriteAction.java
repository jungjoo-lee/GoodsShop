package com.goodsshop.controller.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ImageWriteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String folder = request.getParameter("folder");
		String realName = request.getParameter("realName");
		
		try {
			String imagePath = "c:\\upload\\";
	        File imageFile = new File(imagePath + folder + "\\" + realName);
	        
	        if (imageFile.exists()) {
	            response.setContentType("image/jpeg");
	            FileInputStream fis = new FileInputStream(imageFile);
	            OutputStream os = response.getOutputStream();
	            
	            byte[] buffer = new byte[1024];
	            int bytesRead;
	            while ((bytesRead = fis.read(buffer)) != -1) {
	                os.write(buffer, 0, bytesRead);
	            }
	            os.close();
	            fis.close();
	        } else {
	            response.sendError(HttpServletResponse.SC_NOT_FOUND);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
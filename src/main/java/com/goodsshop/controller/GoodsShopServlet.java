package com.goodsshop.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.json.JSONObject;

import com.goodsshop.controller.action.Action;
import com.goodsshop.controller.action.FatchAction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig(	
		fileSizeThreshold = 1024*1024,	
		maxFileSize = 1024*1024*5, 
		maxRequestSize = 1024*1024*5*5 
)
public class GoodsShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GoodsShopServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		JSONObject jsonResult;
		PrintWriter out;
		
		String command = request.getParameter("command");
		System.out.println("command : " + command);
		if(command==null) System.out.println("1. command 값 오류");
		
		if (command.equals("asyn")) {
			BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
			String jsonStr = in.readLine();
			JSONObject jsonObj = new JSONObject(jsonStr);
			String asynCommand = jsonObj.getString("command");

			FatchFactory ff = FatchFactory.getInstance();
			FatchAction fa = ff.getAction(asynCommand, jsonObj);
			
			if( fa == null ) System.out.println("2. FatchAction 조립 오류");
			else {
				jsonResult = fa.execute(request, response);
				out = response.getWriter();
				out.println(jsonResult.toString());
			}
		} else {
			ActionFactory af = ActionFactory.getInstance();
			Action ac = af.getAction(command);
			
			if( ac == null ) System.out.println("2. Action 조립 오류");
			else ac.execute(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
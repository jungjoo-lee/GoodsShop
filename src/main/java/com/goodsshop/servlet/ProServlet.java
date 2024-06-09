package com.goodsshop.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.goodsshop.properties.Env;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map<String, Object> classNameMap = new HashMap<>();
	Map<String, Object> objectMap = new HashMap<>();
	Map<String, Method> methodMap = new HashMap<>();
	String contextPath;

	public ProServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		ServletContext context = getServletContext();
		InputStream is = context.getResourceAsStream(Env.getInitPath());
		BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
		contextPath = config.getServletContext().getContextPath();

		try {			
			String actionNames = null;
			while ((actionNames = buffer.readLine()) != null) {
				for (String line : actionNames.split("\n")) {
					line = line.trim();

					String[] actionInfo = line.split(":");
					Class<?> cls = Class.forName(actionInfo[1]);

					if (!classNameMap.containsKey(actionInfo[1])) {
						Object object = cls.getDeclaredConstructor().newInstance();
						classNameMap.put(actionInfo[1], object);
						objectMap.put(actionInfo[0], object);
					} else {
						objectMap.put(actionInfo[0], classNameMap.get(actionInfo[1]));
					}
					Method method = cls.getMethod(actionInfo[2], HttpServletRequest.class, HttpServletResponse.class);
					methodMap.put(actionInfo[0], method);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String path = request.getRequestURI();
		path = path.substring(contextPath.length());
		Object obj = objectMap.get(path);
		Method method = methodMap.get(path);
		
		if (obj != null && method != null) {
			try {
				Object ret = method.invoke(obj, request, response);

				if (ret != null) {
					if (ret.getClass().equals(String.class)) {
						RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp" + (String) ret);
						dispatcher.forward(request, response);
					} else if (ret.getClass().equals(JSONObject.class)) {
						JSONObject jsonResult = (JSONObject) ret;
						PrintWriter out = response.getWriter();
						out.println(jsonResult == null ? "{status:false}" : jsonResult.toString());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(request.getRequestURI());
	}
}
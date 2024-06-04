package com.goodsshop.controller.action.admin.goods;

import java.io.File;
import java.io.IOException;

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
		 
		
		String[] giseqs = request.getParameterValues("giseq");
		if (giseqs != null) {
			for (String giseq : giseqs) {
				GoodsImageVO givo = new GoodsImageVO();
				//givo.set
				//giseq들 : 그대로 사용하겠다고 체크한 이미지들 
				//이친구들은 update goods set ()어쩌구 할때 그대로 쓸거임
				
				//dao에서 goods 에만 update하면 되는게 아니고 
				//goodsimage 테이블에도 giseq로 update 해줘야한다
				
				//그럼 사용하지 않겠다고 체크한 친구들도 가져와서 삭제해줘야 하는거 아닌가
				//goodsimage 테이블에서 gseq로 검색한다음
				//쓰겠다고 체크한 giseq 를 제외한 나머지 친구들을 삭제해야 하나??
				//그건 sql문 어떻게 쓰지 (where gseq = ? 근데 giseq = ?는 제외하고)
				
				
				//그러면 순서가
				
				//1. (굿즈dao) 먼저 gvo의 정보로 goods table을 update하고
				//2. 파일업로드 실행한다음
				//3. 파일업로드 하는 반복실행문에 goodsimage table을 해당 oriname, realname, getfilesize 해서 걔네를 goodsimageVO 객체로 만들어서
				//4. (굿즈dao) 해당 givo 의 정보로 goodsimage table update  
				//5. 사용하겠다고 체크한 giseq들 외의 giseq들을 삭제 (위에 적은 주석 내용)
				
				
				//SELECT * FROM goodsimage WHERE gseq = 이미지gseq and giseq not in (); 이렇게 쓰면 된다는데 배열을 저 안에 넣을 수 있나..
				
				// ↓ 이런 식으로 반복문을 활용해서 sql문 자체를 ? 를 배열 길이만큼 sql문에 추가해서 동적으로 생성하면 된다고 함 
				
				/*
				 * String[] values = {"value1", "value2", "value3"}; String sql =
				 * "SELECT * FROM tableName WHERE column NOT IN ("; for (int i = 0; i <
				 * values.length; i++) { if (i != 0) { sql += ","; } sql += "?"; } sql += ")";
				 * 
				 * try (Connection conn = DriverManager.getConnection(url, username, password);
				 * PreparedStatement pstmt = conn.prepareStatement(sql)) { for (int i = 0; i <
				 * values.length; i++) { pstmt.setString(i + 1, values[i]); } // 나머지 코드 }
				 */
				
				//setString도 반복문으로 처리하면 됨

			}
		}
		
		String uploadPath = "C:\\upload\\"+gvo.getGseq()+gvo.getGname()+"\\";
		File uploadDir;
				
		String oriname = "";
		String realname = "";
		
		try {
			for (Part p : request.getParts()) {
				oriname = "";
				
				
				for(String content : p.getHeader("content-disposition").split(";")) {
					
					if(content.trim().startsWith("filename")) {
						
						oriname = content.substring(content.indexOf("=") + 2, content.length() - 1);
						System.out.println("oriname : " + oriname);
						
						if(!oriname.equals("")) {
							
							realname = String.valueOf(System.currentTimeMillis());
							
							uploadDir = new File(uploadPath);
							
							if(!uploadDir.exists()) {
								uploadDir.mkdir();
							}					
							
							p.write(uploadPath + realname);
							
							GoodsDAO gdao = new GoodsDAO();
							//여기 이미지 추가
						} 
					}
				}			
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	}
}

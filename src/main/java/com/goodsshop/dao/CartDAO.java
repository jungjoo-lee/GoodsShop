package com.goodsshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goodsshop.dto.CartVO;
import com.goodsshop.dto.GoodsVO;
import com.goodsshop.util.DB;

public class CartDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<CartVO> getWishList(String userid) {
		List<CartVO> list = new ArrayList<CartVO>();
		con = DB.getConnection();
		String sql = "select*from cart_view c1 inner join (select gseq, min(giseq) as min_giseq from cart_view group by gseq) c2 "
				+ " on c1.gseq = c2.gseq and c1.giseq = c2.min_giseq "
				+ " where userid = ? order by c1.cseq desc";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartVO cvo = new CartVO();
				cvo.setCseq(rs.getInt("cseq"));
				cvo.setGoodsname(rs.getString("gname"));
				cvo.setGseq(rs.getInt("gseq"));
				cvo.setSprice(rs.getInt("s_price"));
				cvo.setUserid(userid);
				cvo.setUsername(rs.getString("name"));
				cvo.setRealname(rs.getString("realname"));
				
				list.add(cvo);
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
		
		return list;
	}

	public void insertWish(String userid, GoodsVO gvo) {
		con = DB.getConnection();
		String sql = "insert into cart (userid, gseq, quantity, totalprice) values (?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, gvo.getGseq());
			pstmt.setInt(3, 1);
			pstmt.setInt(4, gvo.getSprice());
			
			pstmt.executeUpdate();	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}		
	}

	public void deleteWish(int gseqInt) {
		con = DB.getConnection();
		String sql = "delete from cart where gseq = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gseqInt);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}	
	}

	public int selectWish(String userid, GoodsVO gvo) {
		int count = 0;
		
		con = DB.getConnection();
		String sql = "select count(*) as cnt from cart_view where userid = ? and gseq = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, gvo.getGseq());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
		
		System.out.println(count);
		
		return count;
	}


	
	
	
}

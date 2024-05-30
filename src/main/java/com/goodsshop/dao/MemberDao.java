package com.goodsshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.goodsshop.dto.MemberVO;
import com.goodsshop.util.DB;

public class MemberDao {
	private MemberDao() {}
	private static MemberDao itc = new MemberDao();
	public static MemberDao getInstance()	{return itc;}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public MemberVO getMember(String userid) {
		MemberVO mvo = null;
		con = DB.getConnection();
		String sql = "select * from member where userid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				mvo = new MemberVO();
				mvo.setUserid( rs.getString("userid"));
				mvo.setPwd( rs.getString("pwd"));
				mvo.setName( rs.getString("name"));
				mvo.setEmail( rs.getString("email"));
				mvo.setZip_code( rs.getString("zip_code"));
				mvo.setAddress( rs.getString("address"));
				mvo.setD_address( rs.getString("d_address"));
				mvo.setIndate( rs.getTimestamp("indate"));
				mvo.setLast_login_time( rs.getTimestamp("last_login_time"));
				mvo.setIs_login(rs.getInt("is_login"));

			} 
		} catch (SQLException e) { e.printStackTrace();
		} finally { DB.close(con, pstmt, rs); }
		
		return mvo;
	}


	public int insertMember(MemberVO mvo) {
		int result = 0;
		String sql = "insert into member(userid, pwd, name, email, phone, zip_code, address,"
				+ "d_address, gseq) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		con = DB.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mvo.getUserid());
			pstmt.setString(2, mvo.getPwd());
			pstmt.setString(3, mvo.getName());
			pstmt.setString(4, mvo.getEmail());
			pstmt.setString(5, mvo.getPhone());
			pstmt.setString(6, mvo.getZip_code());
			pstmt.setString(7, mvo.getAddress());
			pstmt.setString(8, mvo.getD_address());
			pstmt.setInt(9, mvo.getGseq());
			result = pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { DB.close(con, pstmt, rs); }
		return result;
	}


	public void deleteMember(String userid) {
		con = DB.getConnection();
		String sql = "delete from member where userid=?";
		con = DB.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "userid");
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { DB.close(con, pstmt, rs); }
	}
}




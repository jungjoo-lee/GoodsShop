package com.goodsshop.controller.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.goodsshop.controller.util.Db;




public class MemberDao {

	private MemberDao() {}
	private static MemberDao itc = new MemberDao();
	public static MemberDao getInstance() {return itc;}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public MemberVO getMember(String userid) {
		MemberVO mvo = null;
		con = Db.getConnection();
		String sql = "select * from member where userid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mvo = new MemberVO();
				mvo.setUserid(rs.getString("userid"));
				mvo.setGseq(rs.getInt("gseq"));
				mvo.setPwd(rs.getString("pwd"));
				mvo.setName(rs.getString("name"));
				mvo.setEmail(rs.getString("email"));
				mvo.setZip_code(rs.getString("zip_code"));
				mvo.setAddress(rs.getString("address"));
				mvo.setD_address(rs.getString("d_address"));
				mvo.setPhone(rs.getString("phone"));
				mvo.setIndate(rs.getTimestamp("indate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.close(con, pstmt, rs);
		}
		return mvo;
	}
	
}
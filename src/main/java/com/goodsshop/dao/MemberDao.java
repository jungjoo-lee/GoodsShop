package com.goodsshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goodsshop.dto.AddressVO;
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
	
	
	public void updateMember(MemberVO mvo) {
	     con = DB.getConnection();
	      String sql = "update member set pwd=?, name=?, zip_code=?, address=?, "
	      		+ "d_address=?, email=?, phone=? where userid=?";
	      try {
	         pstmt= con.prepareStatement(sql);
	         pstmt.setString(1, mvo.getPwd());
	         pstmt.setString(2, mvo.getName());
	         pstmt.setString(3, mvo.getZip_code());
	         pstmt.setString(4, mvo.getAddress());
	         pstmt.setString(5, mvo.getD_address());
	         pstmt.setString(6, mvo.getEmail());
	         pstmt.setString(7, mvo.getPhone());
	         pstmt.setString(8, mvo.getUserid());
	         pstmt.executeUpdate();
	      } catch (SQLException e) { e.printStackTrace();
	      } finally {DB.close(con, pstmt, rs);
	      }
	}

	public ArrayList<AddressVO> selectAddressByDong(String dong) {
		ArrayList<AddressVO> list = new ArrayList<AddressVO>();
		con = DB.getConnection();
		String sql = "select*from address where dong like concat('%', ?, '%')";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dong);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AddressVO avo = new AddressVO();
				avo.setZip_num(rs.getString("zip_num"));
				avo.setSido(rs.getString("sido"));
				avo.setGugun(rs.getString("gugun"));
				avo.setDong(rs.getString("dong"));
				avo.setZip_code(rs.getString("zip_code"));
				avo.setBunji(rs.getString("bunji"));
				list.add(avo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.close(con, pstmt, rs);
		}
		return list;
	}
	public MemberVO checkMember(String name, String email) {
		MemberVO mvo = null;
		con = DB.getConnection();
		String sql = "select * from member where name=? and email=? ";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				mvo = new MemberVO();
				mvo.setName( rs.getString("name"));
				mvo.setEmail( rs.getString("email"));
			} 
		} catch (SQLException e) { e.printStackTrace();
		}
		return mvo;
	}
		public List<MemberVO> getMembersByNameAndEmail(String name, String email) {

	        List<MemberVO> list = new ArrayList<>();
	        try {
	        	con = DB.getConnection();
	            String sql = "SELECT userid, name, email FROM members WHERE name = ? AND email = ?";
	            pstmt = con.prepareStatement(sql);
	            pstmt.setString(1, name);
	            pstmt.setString(2, email);
	            rs = pstmt.executeQuery();

	            while (rs.next()) {
	            MemberVO mvo = new MemberVO();
	            mvo.setUserid(rs.getString("userid"));
	            mvo.setName(rs.getString("name"));
	            mvo.setEmail(rs.getString("email"));
	            list.add(mvo);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } 
	        return list;
	}


		public MemberVO checkMembers(String userid, String email) {
				MemberVO mvo = null;
			con = DB.getConnection();
			String sql = "select * from member where userid=? and email=? ";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userid);
				pstmt.setString(2, email);
				rs = pstmt.executeQuery();
				if( rs.next() ) {
					mvo = new MemberVO();
					mvo.setUserid( rs.getString("userid"));
					mvo.setEmail( rs.getString("email"));
				} 
			} catch (SQLException e) { e.printStackTrace();
			}
			return mvo;
		}
}




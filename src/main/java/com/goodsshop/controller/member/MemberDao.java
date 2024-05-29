package com.goodsshop.controller.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
				mvo.setIs_login(rs.getInt("is_login"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.close(con, pstmt, rs);
		}
		return mvo;
	}

	public void updateMember(MemberVO mvo) {
	     con = Db.getConnection();
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
	      } finally {Db.close(con, pstmt, rs);
	      }
	}

	public ArrayList<AddressVO> selectAddressByDong(String dong) {
		ArrayList<AddressVO> list = new ArrayList<AddressVO>();
		con = Db.getConnection();
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
			Db.close(con, pstmt, rs);
		}
		return list;
	}
	}
	
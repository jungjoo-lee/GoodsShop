package com.goodsshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goodsshop.dto.QnaVO;
import com.goodsshop.properties.Env;
import com.goodsshop.util.DB;

public class QnaDAO {
	private QnaDAO() {}
	private static QnaDAO instance = new QnaDAO();
	public static QnaDAO getInstance() { return instance;}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<QnaVO> getMainQnaList() {
		List<QnaVO> list = new ArrayList<>();
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getMainQnaList());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(QnaVO.builder()
						.qseq(rs.getInt(1))
						.userid(rs.getString(2))
						.subject(rs.getString(3))
						.indate(rs.getTimestamp(5))
						.replyDate(rs.getTimestamp(7))
						.build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public List<QnaVO> getQnaList(int amount, int currentPage) {
		List<QnaVO> list = new ArrayList<>();
		int offset = (currentPage - 1) * amount;
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getQnaList());
			pstmt.setInt(1, amount);
			pstmt.setInt(2, offset);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(QnaVO.builder()
						.qseq(rs.getInt(1))
						.userid(rs.getString(2))
						.subject(rs.getString(3))
						.content(rs.getString(4))
						.indate(rs.getTimestamp(5))
						.replyDate(rs.getTimestamp(7))
						.build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return list;
	}

	public Object getMyQnaList(int amount, int currentPage, String userid) {
		List<QnaVO> list = new ArrayList<>();
		int offset = (currentPage - 1) * amount;
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getMyQnaList());
			pstmt.setString(1, userid);
			pstmt.setInt(2, amount);
			pstmt.setInt(3, offset);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(QnaVO.builder()
						.qseq(rs.getInt(1))
						.userid(rs.getString(2))
						.subject(rs.getString(3))
						.content(rs.getString(4))
						.indate(rs.getTimestamp(5))
						.replyDate(rs.getTimestamp(7))
						.build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public int getTotalQna() {
		int total = 0;
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getQnaTotal());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return total;
	}
	
	public int getTotalMyQna(String userid) {
		int total = 0;
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getMyQnaTotal());
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return total;
	}

	public int getTotalQna(String sql) {
		int total = 0;
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return total;
	}
}

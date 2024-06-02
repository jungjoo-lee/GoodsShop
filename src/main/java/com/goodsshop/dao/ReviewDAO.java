package com.goodsshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.goodsshop.dto.QnaVO;
import com.goodsshop.dto.ReviewVO;
import com.goodsshop.properties.Env;
import com.goodsshop.util.DB;

public class ReviewDAO {
	private ReviewDAO() {}
	private static ReviewDAO instance = new ReviewDAO();
	public static ReviewDAO getInstance() { return instance;}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<ReviewVO> getMainReviewList() {
		List<ReviewVO> list = new ArrayList<>();
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getMainReviewList());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(ReviewVO.builder()
						.rseq(rs.getInt(1))
						.userid(rs.getString(2))
						.gseq(rs.getInt(4))
						.gname(rs.getString(6))
						.subject(rs.getString(7))
						.indate(rs.getTimestamp(8))
						.build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public List<ReviewVO> getReviewList(int amount, int currentPage) {
		List<ReviewVO> list = new ArrayList<>();
		int offset = (currentPage - 1) * amount;
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getReviewList());
			pstmt.setInt(1, amount);
			pstmt.setInt(2, offset);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(ReviewVO.builder()
						.rseq(rs.getInt(1))
						.userid(rs.getString(2))
						.grade(rs.getInt(3))
						.gseq(rs.getInt(4))
						.category(rs.getString(5))
						.gname(rs.getString(6))
						.subject(rs.getString(7))
						.indate(rs.getTimestamp(8))
						.giseq(rs.getInt(9))
						.realName(rs.getString(10))
						.build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return list;
	}

	public List<ReviewVO> getReviewMyList(int amount, int currentPage, String userid) {
		List<ReviewVO> list = new ArrayList<>();
		int offset = (currentPage - 1) * amount;
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getMyReviewList());
			pstmt.setString(1, userid);
			pstmt.setInt(2, amount);
			pstmt.setInt(3, offset);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(ReviewVO.builder()
						.rseq(rs.getInt(1))
						.userid(rs.getString(2))
						.grade(rs.getInt(3))
						.gseq(rs.getInt(4))
						.category(rs.getString(5))
						.gname(rs.getString(6))
						.subject(rs.getString(7))
						.indate(rs.getTimestamp(8))
						.giseq(rs.getInt(9))
						.realName(rs.getString(10))
						.build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public int getTotalReview() {
		int total = 0;
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getReviewTotal());
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

	public int getTotalMyReview(String userid) {
int total = 0;
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getMyReviewTotal());
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

	public List<ReviewVO> getReviewList(String sql) {
		List<ReviewVO> reviewList = new ArrayList<>();
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				reviewList.add(ReviewVO.builder()
						.rseq(rs.getInt(1))
						.userid(rs.getString(2))
						.grade(rs.getInt(3))
						.gseq(rs.getInt(4))
						.category(rs.getString(5))
						.gname(rs.getString(6))
						.subject(rs.getString(7))
						.indate(rs.getTimestamp(8))
						.giseq(rs.getInt(9))
						.realName(rs.getString(10))
						.build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return reviewList;
	}

	public int getTotalReview(String sql) {
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

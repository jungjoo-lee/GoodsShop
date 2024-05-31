package com.goodsshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
						.gseq(rs.getInt(3))
						.gname(rs.getString(4))
						.subject(rs.getString(5))
						.content(rs.getString(6))
						.indate(rs.getTimestamp(7))
						.build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public List<ReviewVO> getReviewList() {
		List<ReviewVO> list = new ArrayList<>();
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getReviewTestList());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(ReviewVO.builder()
						.rseq(rs.getInt(1))
						.userid(rs.getString(2))
						.grade(rs.getString(3))
						.gseq(rs.getInt(4))
						.category(rs.getString(5))
						.gname(rs.getString(6))
						.subject(rs.getString(7))
						.content(rs.getString(8))
						.indate(rs.getTimestamp(9))
						.giseq(rs.getInt(10))
						.build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return list;
	}
}

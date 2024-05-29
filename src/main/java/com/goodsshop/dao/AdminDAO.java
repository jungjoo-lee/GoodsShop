package com.goodsshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goodsshop.dto.QnaVO;
import com.goodsshop.properties.Env;
import com.goodsshop.util.Db;

public class AdminDAO {
	private AdminDAO() {}
	private static AdminDAO instance = new AdminDAO();
	public static AdminDAO getInstance() { return instance;}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<QnaVO> getQnaList(int amount, int currentPage){
		List<QnaVO> qnaList = new ArrayList<>();
		int offset = (currentPage - 1) * amount;
		
		try {
			conn = Db.getConnection();
			pstmt = conn.prepareStatement(Env.getQnaList());
			pstmt.setInt(1, amount);
			pstmt.setInt(2, offset);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				qnaList.add(QnaVO.builder()
						.qseq(rs.getInt(1))
						.userid(rs.getString(2))
						.subject(rs.getString(3))
						.content(rs.getString(4))
						.indate(rs.getTimestamp(5))
						.reply(rs.getString(6))
						.build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.close(conn, pstmt, rs);
		}
		
		return qnaList;
	}

	public int getTotalQna() {
		int total = 0;
		
		try {
			conn = Db.getConnection();
			pstmt = conn.prepareStatement(Env.getQnaTotal());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.close(conn, pstmt, rs);
		}
		
		return total;
	}
	
	public QnaVO getQna(int qseq) {
		QnaVO vo = null;
		
		try {
			conn = Db.getConnection();
			pstmt = conn.prepareStatement(Env.getQna());
			pstmt.setInt(1, qseq);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vo = QnaVO.builder()
						.qseq(rs.getInt(1))
						.userid(rs.getString(2))
						.subject(rs.getString(3))
						.content(rs.getString(4))
						.indate(rs.getTimestamp(5))
						.reply(rs.getString(6))
						.replyDate(rs.getTimestamp(7))
						.build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	
}

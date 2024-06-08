package com.goodsshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.goodsshop.dto.NoticeVO;
import com.goodsshop.dto.QnaVO;
import com.goodsshop.properties.Env;
import com.goodsshop.util.DB;


public class NoticeDAO {
	private NoticeDAO() {}
	private static NoticeDAO instance = new NoticeDAO();
	public static NoticeDAO getInstance() { return instance;}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public List<NoticeVO> getMainNoticeList() {
		List<NoticeVO> list = new ArrayList<>();
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getMainNoticeList());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(NoticeVO.builder()
						.nseq(rs.getInt(1))
						.adminId(rs.getString(2))
						.subject(rs.getString(3))
						.content(rs.getString(4))
						.indate(rs.getTimestamp(5))
						.build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public List<NoticeVO> getNoticeList(int amount, int currentPage) {
		List<NoticeVO> list = new ArrayList<>();
		int offset = (currentPage - 1) * amount;
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getNoticeList());
			pstmt.setInt(1, amount);
			pstmt.setInt(2, offset);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(NoticeVO.builder()
						.nseq(rs.getInt(1))
						.adminId(rs.getString(2))
						.subject(rs.getString(3))
						.content(rs.getString(4))
						.indate(rs.getTimestamp(5))
						.build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public List<NoticeVO> getNoticeList(String sql) {
		List<NoticeVO> list = new ArrayList<>();
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(NoticeVO.builder()
						.nseq(rs.getInt(1))
						.adminId(rs.getString(2))
						.subject(rs.getString(3))
						.content(rs.getString(4))
						.indate(rs.getTimestamp(5))
						.build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return list;
	}

	public int getTotalNotice() {
		int total = 0;
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getNoticeTotal());
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

	public int getTotalNotice(String sql) {
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

	public NoticeVO getNotice(int nseq) {
		NoticeVO vo = null;
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getNotice());
			pstmt.setInt(1, nseq);
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo = NoticeVO.builder()
						.nseq(rs.getInt(1))
						.adminId(rs.getString(2))
						.subject(rs.getString(3))
						.content(rs.getString(4))
						.indate(rs.getTimestamp(5))
						.build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	public void updateNotice(NoticeVO vo) {
	    try {
	        conn = DB.getConnection();
	        pstmt = conn.prepareStatement(Env.getupdateNotice());
	        pstmt.setString(1, vo.getSubject());
	        pstmt.setString(2, vo.getContent());
	        pstmt.setInt(3, vo.getNseq());
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        DB.close(conn, pstmt, rs);
	    }
	}

	public void insertNotice(NoticeVO vo) {
		conn = DB.getConnection();
		try {
			pstmt = conn.prepareStatement(Env.getinsertNotice());
			pstmt.setString(1, vo.getAdminId());
			pstmt.setString(2, vo.getSubject());
			pstmt.setString(3, vo.getContent());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.close(conn, pstmt, rs);
		}	
	}}

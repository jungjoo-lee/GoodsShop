package com.goodsshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.goodsshop.dto.AdminVO;
import com.goodsshop.dto.MemberVO;
import com.goodsshop.dto.NoticeVO;
import com.goodsshop.dto.QnaVO;
import com.goodsshop.properties.Env;
import com.goodsshop.util.DB;

public class AdminDAO {
	private AdminDAO() {}
	private static AdminDAO instance = new AdminDAO();
	public static AdminDAO getInstance() { return instance;}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public AdminVO getAdmin(String id) {
		AdminVO vo = null;
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getAdmin());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				vo = AdminVO.builder()
						.adminId(rs.getString(1))
						.pwd(rs.getString(2))
						.name(rs.getString(3))
						.phone(rs.getString(4))
						.email(rs.getString(5))
						.build();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	public List<MemberVO> getMemberList(int amount, int currentPage) {
		List<MemberVO> memberList = new ArrayList<>();
		int offset = (currentPage - 1) * amount;
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getMemberList());
			pstmt.setInt(1, amount);
			pstmt.setInt(2, offset);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memberList.add(MemberVO.builder()
						.userid(rs.getString(1))
						.gseq(rs.getInt(3))
						.name(rs.getString(4))
						.email(rs.getString(5))
						.phone(rs.getString(6))
						.zip_code(rs.getString(7))
						.address(rs.getString(8))
						.d_address(rs.getString(9))
						.indate(rs.getTimestamp(10))
						.last_login_time(rs.getTimestamp(11))
						.is_login(rs.getInt(12))
						.grade(rs.getString(14))
						.sale(rs.getInt(15))
						.build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return memberList;
	}
	
	public List<MemberVO> getMemberList(String sql) {
		List<MemberVO> memberList = new ArrayList<>();
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				memberList.add(MemberVO.builder()
						.userid(rs.getString(1))
						.gseq(rs.getInt(3))
						.name(rs.getString(4))
						.email(rs.getString(5))
						.phone(rs.getString(6))
						.zip_code(rs.getString(7))
						.address(rs.getString(8))
						.d_address(rs.getString(9))
						.indate(rs.getTimestamp(10))
						.last_login_time(rs.getTimestamp(11))
						.is_login(rs.getInt(12))
						.grade(rs.getString(14))
						.sale(rs.getInt(15))
						.build());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
		
		return memberList;
	}
	
	public int getTotalMember() {
		int total = 0;
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getMemberTotal());
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
	
	public int getTotalMember(String sql) {
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
	
	public List<QnaVO> getQnaList(int amount, int currentPage) {
		List<QnaVO> qnaList = new ArrayList<>();
		int offset = (currentPage - 1) * amount;
		
		try {
			conn = DB.getConnection();
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
			DB.close(conn, pstmt, rs);
		}
		
		return qnaList;
	}
	
	public List<QnaVO> getQnaList(String sql) {
		List<QnaVO> qnaList = new ArrayList<>();
		
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(sql);
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
			DB.close(conn, pstmt, rs);
		}
		
		return qnaList;
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
	
	public QnaVO getQna(int qseq) {
		QnaVO vo = null;
		
		try {
			conn = DB.getConnection();
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
			DB.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	public void writeUpdateReply(String reply, int qseq) {
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.writeUpdateReply());
			pstmt.setString(1, reply);
			pstmt.setInt(2, qseq);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
	}
	
	public void deleteReply(int qseq) {
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.deleteReply());
			pstmt.setInt(1, qseq);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
	}
	
	public int getTotalNotice(String sql) {
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
	public Object getNoticeList(int amount, int currentPage) {
		List<NoticeVO> noticeList = new ArrayList<>();
		int offset = (currentPage - 1) * amount;
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getNoticeList());
			pstmt.setInt(1, amount);
			pstmt.setInt(2, offset);
			rs = pstmt.executeQuery();
			while (rs.next()) {
						noticeList.add(NoticeVO.builder()
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
		return noticeList;
	}

	public Object getNotice(int nseq) {
		NoticeVO vo = null;
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getNotice());
			pstmt.setInt(1, nseq);
			rs = pstmt.executeQuery();
			if (rs.next()) {
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

	public void deleteNotice(int nseq) {
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getdeleteNotice());
			pstmt.setInt(1, nseq);
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
	}

	public void updateNotice(NoticeVO vo) {
		try {
			conn = DB.getConnection();
			pstmt = conn.prepareStatement(Env.getupdateNotice());
			pstmt.setString(1,vo.getSubject());
			pstmt.setString(2,vo.getContent());
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(conn, pstmt, rs);
		}
	}
}

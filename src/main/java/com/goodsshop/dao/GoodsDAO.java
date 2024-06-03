package com.goodsshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goodsshop.dto.GoodsImageVO;
import com.goodsshop.dto.GoodsVO;
import com.goodsshop.util.DB;
import com.goodsshop.controller.goods.Db;
import com.goodsshop.dto.GoodsImageVO;
import com.goodsshop.dto.GoodsVO;
import com.goodsshop.dto.ReviewVO;
import com.goodsshop.util.DB;


public class GoodsDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public List<GoodsImageVO> getImageList(int gseq) {
		List<GoodsImageVO> list = new ArrayList<GoodsImageVO>();
		
		con = Db.getConnection();
		String sql = "select * from goodsimage where gseq = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gseq);
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				GoodsImageVO givo = new GoodsImageVO();
				givo.setGiseq(rs.getInt("giseq"));
				givo.setGseq(rs.getInt("gseq"));
				givo.setOriname(rs.getString("oriname"));
				givo.setRealname(rs.getString("realname"));
				givo.setFilesize(rs.getLong("filesize"));
				
				list.add(givo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	public String getThumbnail(int gseq) {
		String thumbnailImage = "";
		
		con = Db.getConnection();
		String sql = "select * from goodsimage where gseq = ? order by giseq limit 1";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gseq);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				thumbnailImage = rs.getString("realname");
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.close(con, pstmt, rs);
		}

		return thumbnailImage;
	}
	
	
	
	public List<GoodsVO> getBestList() {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		
		con = Db.getConnection();
		String sql = "select * from bestlist_view limit 20";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
				
			while(rs.next()) {
				GoodsVO gvo = new GoodsVO();
				gvo.setGseq(rs.getInt("gseq"));
				gvo.setGname(rs.getString("gname"));
				gvo.setSprice(rs.getInt("s_price"));
				
				list.add(gvo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}

		return list;
	}

	public List<GoodsVO> getNewList() {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		
		con = DB.getConnection();
		String sql = "select * from newlist_view limit 8";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();		
			
			while(rs.next()) {
				GoodsVO gvo = new GoodsVO();
				gvo.setGseq(rs.getInt("gseq"));
				gvo.setGname(rs.getString("gname"));
				gvo.setSprice(rs.getInt("s_price"));
				
				list.add(gvo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}

		return list;
	}

	public GoodsVO getGoods(int gseq) {
		GoodsVO gvo = null;
		con = Db.getConnection();
		String sql = "select * from goods where gseq = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gseq);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				gvo = new GoodsVO();
				gvo.setGseq(rs.getInt("gseq"));
				gvo.setGname(rs.getString("gname"));
				gvo.setCgseq(rs.getInt("cgseq"));
				gvo.setOprice(rs.getInt("o_price"));
				gvo.setSprice(rs.getInt("s_price"));
				gvo.setMprice(rs.getInt("m_price"));
				gvo.setContent(rs.getString("content"));
				gvo.setBestyn(rs.getInt("bestyn"));
				gvo.setUseyn(rs.getInt("useyn"));
				gvo.setIndate(rs.getDate("indate"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.close(con, pstmt, rs);
		}
		
		
		return gvo;
	}

	public List<ReviewVO> getReviewList(int gseq) {
		List<ReviewVO> reviewList = new ArrayList<>();
		
		return reviewList;
	}
}

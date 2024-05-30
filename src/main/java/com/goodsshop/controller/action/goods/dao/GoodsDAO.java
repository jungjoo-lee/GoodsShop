package com.goodsshop.controller.action.goods.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goodsshop.controller.action.goods.dto.GoodsImageVO;
import com.goodsshop.controller.action.goods.dto.GoodsVO;
import com.goodsshop.controller.action.goods.util.Db;

public class GoodsDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	
	public List<GoodsVO> getBestList() {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		
		con = Db.getConnection();
		String sql = "select * from bestlist_view";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				GoodsVO gvo = new GoodsVO();
				gvo.setGseq(rs.getInt("gseq"));
				gvo.setGname(rs.getString("gname"));
				gvo.setS_price(rs.getInt("s_price"));
				
				list.add(gvo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.close(con, pstmt, rs);
		}

		return list;
	}

	public List<GoodsImageVO> getImageList(int gseq) {
		List<GoodsImageVO> list = new ArrayList<GoodsImageVO>();
		
		con = Db.getConnection();
		String sql = "select * from goodsimage where gseq = ? limit 20";
		
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
				System.out.println(givo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.close(con, pstmt, rs);
		}
		
		return list;
	}



	public List<GoodsVO> getNewList() {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		
		con = Db.getConnection();
		String sql = "select * from newlist_view limit 8";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();		
			
			while(rs.next()) {
				GoodsVO gvo = new GoodsVO();
				gvo.setGseq(rs.getInt("gseq"));
				gvo.setGname(rs.getString("gname"));
				gvo.setS_price(rs.getInt("s_price"));
				
				list.add(gvo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Db.close(con, pstmt, rs);
		}

		return list;
	}
		


}

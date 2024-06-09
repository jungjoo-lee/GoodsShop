package com.goodsshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.goodsshop.controller.action.goods.MPaging;
import com.goodsshop.dto.GoodsImageVO;
import com.goodsshop.dto.GoodsVO;
import com.goodsshop.dto.ReviewVO;
import com.goodsshop.util.DB;
import com.goodsshop.util.Paging;


public class GoodsDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public List<GoodsImageVO> getImageList(int gseq) {
		List<GoodsImageVO> list = new ArrayList<GoodsImageVO>();
		
		con = DB.getConnection();
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
		
		con = DB.getConnection();
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
			DB.close(con, pstmt, rs);
		}

		return thumbnailImage;
	}
	

	
	
	
	public List<GoodsVO> getBestList() {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		
		con = DB.getConnection();
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
		con = DB.getConnection();
		String sql = "select * from goods_view where gseq = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gseq);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				gvo = new GoodsVO();
				gvo.setGseq(rs.getInt("gseq"));
				gvo.setGname(rs.getString("gname"));
				gvo.setOprice(rs.getInt("o_price"));
				gvo.setSprice(rs.getInt("s_price"));
				gvo.setMprice(rs.getInt("m_price"));
				gvo.setContent(rs.getString("content"));
				gvo.setBestyn(rs.getInt("bestyn"));
				gvo.setUseyn(rs.getInt("useyn"));
				gvo.setIndate(rs.getDate("indate"));
				gvo.setCgseq(rs.getInt("cgseq"));
				gvo.setCategory(rs.getString("category"));
				gvo.setGiseq(rs.getInt("giseq"));
				gvo.setRealname(rs.getString("realname"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
		
		
		return gvo;
	}
	

	public List<ReviewVO> getReviewList(int gseq) {
		List<ReviewVO> reviewList = new ArrayList<>();
		
		return reviewList;
	}
	

	public List<GoodsVO> getCategoryList(String cgseq, MPaging paging) {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		
		con = DB.getConnection();
		String sql = "select * from goods_view where cgseq = ? limit ? offset ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cgseq);
			pstmt.setInt(2, paging.getDisplayRow());
			pstmt.setInt(3, paging.getStartNum() - 1);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GoodsVO gvo = new GoodsVO();
				gvo.setGseq(rs.getInt("gseq"));
				gvo.setGname(rs.getString("gname"));
				gvo.setOprice(rs.getInt("o_price"));
				gvo.setSprice(rs.getInt("s_price"));
				gvo.setMprice(rs.getInt("m_price"));
				gvo.setContent(rs.getString("content"));
				gvo.setBestyn(rs.getInt("bestyn"));
				gvo.setUseyn(rs.getInt("useyn"));
				gvo.setIndate(rs.getDate("indate"));
				gvo.setCgseq(rs.getInt("cgseq"));
				gvo.setCategory(rs.getString("category"));
				gvo.setGiseq(rs.getInt("giseq"));
				gvo.setRealname(rs.getString("realname"));
				
				list.add(gvo);
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}		
		return list;
	}
	

	public List<GoodsVO> getAllGoods(String keyword, MPaging paging) {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		
		con = DB.getConnection();
		String sql = "select * from goods_view g1 "
				+ "inner join "
				+ "(select gseq, min(giseq) as min_giseq from goods_view group by gseq) g2 "
				+ "on g1.gseq = g2.gseq and g1.giseq = g2.min_giseq "
				+ "where gname like concat('%', ?, '%') order by g1.gseq limit ? offset ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setInt(2, paging.getDisplayRow());
			pstmt.setInt(3, paging.getStartNum() - 1);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GoodsVO gvo = new GoodsVO();
				gvo.setGseq(rs.getInt("gseq"));
				gvo.setGname(rs.getString("gname"));
				gvo.setOprice(rs.getInt("o_price"));
				gvo.setSprice(rs.getInt("s_price"));
				gvo.setMprice(rs.getInt("m_price"));
				gvo.setContent(rs.getString("content"));
				gvo.setBestyn(rs.getInt("bestyn"));
				gvo.setUseyn(rs.getInt("useyn"));
				gvo.setIndate(rs.getDate("indate"));
				gvo.setCgseq(rs.getInt("cgseq"));
				gvo.setCategory(rs.getString("category"));
				gvo.setGiseq(rs.getInt("giseq"));
				gvo.setRealname(rs.getString("realname"));
				
				list.add(gvo);

			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	
	public List<GoodsVO> getAllGoods(String keyword) {
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		
		con = DB.getConnection();
		String sql = "select * from goods_view g1 "
				+ "inner join "
				+ "(select gseq, min(giseq) as min_giseq from goods_view group by gseq) g2 "
				+ "on g1.gseq = g2.gseq and g1.giseq = g2.min_giseq "
				+ "where gname like concat('%', ?, '%') order by g1.gseq";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GoodsVO gvo = new GoodsVO();
				gvo.setGseq(rs.getInt("gseq"));
				gvo.setGname(rs.getString("gname"));
				gvo.setOprice(rs.getInt("o_price"));
				gvo.setSprice(rs.getInt("s_price"));
				gvo.setMprice(rs.getInt("m_price"));
				gvo.setContent(rs.getString("content"));
				gvo.setBestyn(rs.getInt("bestyn"));
				gvo.setUseyn(rs.getInt("useyn"));
				gvo.setIndate(rs.getDate("indate"));
				gvo.setCgseq(rs.getInt("cgseq"));
				gvo.setCategory(rs.getString("category"));
				gvo.setGiseq(rs.getInt("giseq"));
				gvo.setRealname(rs.getString("realname"));
				
				list.add(gvo);

			}		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	
	public List<GoodsVO> getAllCategories(){
		List<GoodsVO> list = new ArrayList<GoodsVO>();
		
		con = DB.getConnection();
		String sql = "select * from category";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				GoodsVO gvo = new GoodsVO();
				gvo.setCgseq(rs.getInt("cgseq"));
				gvo.setCategory(rs.getString("category"));				
				list.add(gvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}	
		return list;
	}

	public void updateGoods(GoodsVO gvo) {
		con = DB.getConnection();
		String sql = "update goods set gname = ?, cgseq = ?, o_price = ?, s_price = ?, m_price = ?, content = ?, bestyn = ?, useyn = ? where gseq = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gvo.getGname());
			pstmt.setInt(2, gvo.getCgseq());
			pstmt.setInt(3, gvo.getOprice());
			pstmt.setInt(4, gvo.getSprice());
			pstmt.setInt(5, gvo.getMprice());
			pstmt.setString(6, gvo.getContent());
			pstmt.setInt(7, gvo.getBestyn());
			pstmt.setInt(8, gvo.getUseyn());
			pstmt.setInt(9, gvo.getGseq());
			
			int result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			DB.close(con, pstmt, rs);
		}
	}

	public void writeGoodsImages(GoodsImageVO givo, int gseq) {
		con = DB.getConnection();
		String sql = "insert into goodsimage (gseq, oriname, realname, filesize) values (?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gseq);
			pstmt.setString(2, givo.getOriname());
			pstmt.setString(3, givo.getRealname());
			pstmt.setLong(4, givo.getFilesize());
			
			int result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
	}

	public void deleteGoodsImages(String[] giseqs, int gseq) {		
		con = DB.getConnection();
		
		//sql문 배열로
		String sql = "delete from goodsimage where gseq = ? and giseq not in (";
		for (int i = 0; i < giseqs.length; i++) {
			if (i != 0) {
				sql += ",";
			}
			sql += "?";
		}
		sql += ")";
		
		try {
			pstmt = con.prepareStatement(sql);	
			pstmt.setInt(1, gseq);
			for (int i = 0; i<giseqs.length; i++) {
					pstmt.setString(i+2, giseqs[i]);
				}
			
			int result = pstmt.executeUpdate();
			

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
	}

	public void deleteGoodsImages(int gseq) {
		con = DB.getConnection();
		String sql = "delete from goodsimage where gseq = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			int result = pstmt.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}		
	}

	public void insertGoods(GoodsVO gvo) {
		con = DB.getConnection();
		String sql = "insert into goods (gname, cgseq, o_price, s_price, m_price, content, bestyn, useyn) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gvo.getGname());
			pstmt.setInt(2, gvo.getCgseq());
			pstmt.setInt(3, gvo.getOprice());
			pstmt.setInt(4, gvo.getSprice());
			pstmt.setInt(5, gvo.getMprice());
			pstmt.setString(6, gvo.getContent());
			pstmt.setInt(7, gvo.getBestyn());
			pstmt.setInt(8, gvo.getUseyn());
			
			int result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}		
	}

	public int lookupMaxGseq() {
		int gseq = 0;
		
		con = DB.getConnection();
		String sql = "select max(gseq) from goods";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				gseq = rs.getInt("max(gseq)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}	
		
		return gseq;
	}

	public void deleteGoods(String gseq) {
		con = DB.getConnection();
		String sql = "delete from goods where gseq = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gseq);
			int result = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
	}

	public void bestToggle(String gseq) {
		con = DB.getConnection();
		String sql = "update goods set bestyn = bestyn^1 where gseq = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gseq);
			
			int result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
	}

	public void useYnToggle(String gseq) {
		con = DB.getConnection();
		String sql = "update goods set useyn = useyn^1 where gseq = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gseq);
			
			int result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
		
	}
		
	
	public int getAllCount(String key, String fieldName) {
		int count = 0;
		
		con = DB.getConnection();
		String sql = "select count(*) as cnt from goods_view g1 inner join (select gseq, min(giseq) as min_giseq from goods_view group by gseq) g2 on g1.gseq = g2.gseq and g1.giseq = g2.min_giseq "
				+ "where " + fieldName + " like concat('%', ?, '%') order by g1.gseq ";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
		
		return count;
	}		

	
}

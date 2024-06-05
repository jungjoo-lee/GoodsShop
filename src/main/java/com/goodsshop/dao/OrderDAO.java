package com.goodsshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.goodsshop.dto.OrderVO;
import com.goodsshop.util.DB;

public class OrderDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public List<OrderVO> selectOrderList(String userid) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		
		con = DB.getConnection();
		String sql = "select * from order_view o1 inner join (select oseq, min(odseq) as min_odseq from order_view group by oseq) o2 on o1.oseq = o2.oseq and o1.odseq = o2.min_odseq where userid = ? order by o1.oseq desc";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);		
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderVO ovo = new OrderVO();
				ovo.setOseq(rs.getInt("oseq"));
				ovo.setOsseq(rs.getInt("osseq"));
				ovo.setStatus(rs.getString("status"));	
				ovo.setGname(rs.getString("gname"));
				ovo.setIndate(rs.getDate("indate"));
				
				ovo.setQuantity(selectOrderRows(rs.getInt("oseq")));
				ovo.setTotalprice(selectOrderTotalprice(rs.getInt("oseq")));
				
				list.add(ovo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}	
		
		return list;
	}
	
	
	private int selectOrderTotalprice(int oseq) {
		int price = 0;
		
		Connection con3 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs3 = null;
		
		con3 = DB.getConnection();
		String sql = "select totalprice from order_view where oseq = ?";
		
		try {
			pstmt3 = con3.prepareStatement(sql);
			pstmt3.setInt(1, oseq);
			rs3 = pstmt3.executeQuery();
			
			while(rs3.next()) {
				price += rs3.getInt("totalprice");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con3, pstmt3, rs3);
		}
		
		return price;
	}


	public int selectOrderRows (int oseq) {
		int count = 0;
		
		Connection con2 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		
		con2 = DB.getConnection();
		String sql = "select count(*) from order_view where oseq = ?";
		
		try {
			pstmt2 = con2.prepareStatement(sql);
			pstmt2.setInt(1, oseq);
			rs2 = pstmt2.executeQuery();
			
			if(rs2.next()) {
				count = rs2.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con2, pstmt2, rs2);
		}	
		return count;
	}
	
	
	public void insertOrder(String userid) {
		con = DB.getConnection();
		String sql = "insert into orders (userid) values (?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}		
	}
	
	
	public int lookupMaxOseq(String userid) {
		int oseq = 0;
		con = DB.getConnection();
		String sql = "select max(oseq) as maxoseq from orders where userid = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if (rs.next())
				oseq = rs.getInt("maxoseq");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
		return oseq;
	}

	public void insertOrderDetail(OrderVO ovo, int oseq) {
		con = DB.getConnection();
		String sql = "insert into order_detail (oseq, gseq, quantity, totalprice, osseq) values (?, ?, ?, ?, ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, oseq);
			pstmt.setInt(2, ovo.getGseq());
			pstmt.setInt(3, ovo.getQuantity());
			pstmt.setInt(4, ovo.getTotalprice());
			pstmt.setInt(5, 1);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}		
	}


	public List<OrderVO> selectOrderDetail(int oseq) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		con = DB.getConnection();
		String sql = "select * from order_view where oseq = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, oseq);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderVO ovo = new OrderVO();
				ovo.setOseq(oseq);
				ovo.setOdseq(rs.getInt("odseq"));
				ovo.setIndate(rs.getDate("indate"));
				ovo.setUserid(rs.getString("userid"));
				ovo.setName(rs.getString("name"));
				ovo.setZipcode(rs.getString("zip_code"));
				ovo.setAddress(rs.getString("address"));
				ovo.setDaddress(rs.getString("d_address"));
				ovo.setPhone(rs.getString("phone"));
				ovo.setGseq(rs.getInt("gseq"));
				ovo.setGname(rs.getString("gname"));
				ovo.setQuantity(rs.getInt("quantity"));
				ovo.setTotalprice(rs.getInt("totalprice"));
				ovo.setOsseq(rs.getInt("osseq"));
				ovo.setStatus(rs.getString("status"));
				ovo.setRealname(rs.getString("realname"));
				
				list.add(ovo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
		
		return list;
	}


	public List<OrderVO> getAllOrderList(String keyword) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		
		con = DB.getConnection();
		String sql = "select * from order_view o1 inner join (select oseq, min(odseq) as min_odseq from order_view group by oseq) o2 on o1.oseq = o2.oseq and o1.odseq = o2.min_odseq "
				+ "where gname like concat('%', ?, '%')";
				
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			
			System.out.println(rs.getRow());
			
			while(rs.next()) {
				OrderVO ovo = new OrderVO();
				ovo.setOseq(rs.getInt("oseq"));
				ovo.setOdseq(rs.getInt("odseq"));
				ovo.setIndate(rs.getDate("indate"));
				ovo.setUserid(rs.getString("userid"));
				ovo.setName(rs.getString("name"));
				ovo.setZipcode(rs.getString("zip_code"));
				ovo.setAddress(rs.getString("address"));
				ovo.setDaddress(rs.getString("d_address"));
				ovo.setPhone(rs.getString("phone"));
				ovo.setGseq(rs.getInt("gseq"));
				ovo.setGname(rs.getString("gname"));
				ovo.setOsseq(rs.getInt("osseq"));
				ovo.setStatus(rs.getString("status"));		
				ovo.setRealname(rs.getString("realname"));
				
				
				ovo.setQuantity(selectOrderRows(rs.getInt("oseq")));
				ovo.setTotalprice(selectOrderTotalprice(rs.getInt("oseq")));
				
				list.add(ovo);				
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}	
		return list;
	}
	
	public List<OrderVO> getAllOrderListByName(String keyword) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		
		con = DB.getConnection();
		String sql = "select * from order_view o1 inner join (select oseq, min(odseq) as min_odseq from order_view group by oseq) o2 on o1.oseq = o2.oseq and o1.odseq = o2.min_odseq "
				+ "where name like concat('%', ?, '%')";
				
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			
			System.out.println(rs.getRow());
			
			while(rs.next()) {
				OrderVO ovo = new OrderVO();
				ovo.setOseq(rs.getInt("oseq"));
				ovo.setOdseq(rs.getInt("odseq"));
				ovo.setIndate(rs.getDate("indate"));
				ovo.setUserid(rs.getString("userid"));
				ovo.setName(rs.getString("name"));
				ovo.setZipcode(rs.getString("zip_code"));
				ovo.setAddress(rs.getString("address"));
				ovo.setDaddress(rs.getString("d_address"));
				ovo.setPhone(rs.getString("phone"));
				ovo.setGseq(rs.getInt("gseq"));
				ovo.setGname(rs.getString("gname"));
				ovo.setOsseq(rs.getInt("osseq"));
				ovo.setStatus(rs.getString("status"));		
				ovo.setRealname(rs.getString("realname"));
				
				
				ovo.setQuantity(selectOrderRows(rs.getInt("oseq")));
				ovo.setTotalprice(selectOrderTotalprice(rs.getInt("oseq")));
				
				list.add(ovo);				
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}	
		return list;
	}
	
	public List<OrderVO> getAllOrderListById(String keyword) {
		List<OrderVO> list = new ArrayList<OrderVO>();
		
		con = DB.getConnection();
		String sql = "select * from order_view o1 inner join (select oseq, min(odseq) as min_odseq from order_view group by oseq) o2 on o1.oseq = o2.oseq and o1.odseq = o2.min_odseq "
				+ "where userid like concat('%', ?, '%')";
				
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs = pstmt.executeQuery();
			
			System.out.println(rs.getRow());
			
			while(rs.next()) {
				OrderVO ovo = new OrderVO();
				ovo.setOseq(rs.getInt("oseq"));
				ovo.setOdseq(rs.getInt("odseq"));
				ovo.setIndate(rs.getDate("indate"));
				ovo.setUserid(rs.getString("userid"));
				ovo.setName(rs.getString("name"));
				ovo.setZipcode(rs.getString("zip_code"));
				ovo.setAddress(rs.getString("address"));
				ovo.setDaddress(rs.getString("d_address"));
				ovo.setPhone(rs.getString("phone"));
				ovo.setGseq(rs.getInt("gseq"));
				ovo.setGname(rs.getString("gname"));
				ovo.setOsseq(rs.getInt("osseq"));
				ovo.setStatus(rs.getString("status"));		
				ovo.setRealname(rs.getString("realname"));
				
				
				ovo.setQuantity(selectOrderRows(rs.getInt("oseq")));
				ovo.setTotalprice(selectOrderTotalprice(rs.getInt("oseq")));
				
				list.add(ovo);				
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}	
		return list;
	}	


	public void updateOrderStatus(String oseq, String osseq) {
		con = DB.getConnection();
		String sql = "update order_detail set osseq = ? where oseq = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, osseq);
			pstmt.setString(2, oseq);
			
			pstmt.executeUpdate();
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(con, pstmt, rs);
		}
	}
}

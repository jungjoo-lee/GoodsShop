package com.goodsshop.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.goodsshop.properties.Env;

public class DB {

	public static Connection getConnection() {
		Connection con = null;
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup(Env.getEnvContext());
			DataSource ds = (DataSource) envContext.lookup(Env.getDataSource());
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}

	public static void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

package com.goodsshop.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.goodsshop.properties.Env;
		
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup(Env.getEnvContext());
			DataSource dataFactory = (DataSource) envContext.lookup(Env.getDataSource());
			conn = dataFactory.getConnection();
		}catch (NamingException e) {e.printStackTrace();
		}catch (SQLException e) {e.printStackTrace();
		}
		
	}
	
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
		}
	}
}

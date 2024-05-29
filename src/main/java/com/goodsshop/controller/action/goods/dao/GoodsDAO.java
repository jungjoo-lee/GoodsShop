package com.goodsshop.controller.action.goods.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.mysql.cj.protocol.Resultset;

public class GoodsDAO {
	
	private GoodsDAO() {}
	private static GoodsDAO itc = new GoodsDAO();
	public static GoodsDAO getInstance() {
		return itc;
	}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	Resultset rs = null;

}

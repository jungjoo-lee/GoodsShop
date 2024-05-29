package com.goodsshop.controller.action.goods.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class GoodsDTO {
	private int gseq;
	private String gname;
	private int category;
	private int o_price;
	private int s_price;
	private int m_price;
	private String content;
	private boolean bestyn;
	private boolean useyn;
	private Date indate;
	private int giseq;
	private String oriname;
	private String realname;
	private String filesize;
}

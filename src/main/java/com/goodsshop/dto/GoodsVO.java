package com.goodsshop.dto;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class GoodsVO {
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
	private List<GoodsImageVO> imageList;
}

package com.goodsshop.dto;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class GoodsVO {
	private int gseq;
	private String gname;
	private int cgseq;
	private int oprice;
	private int sprice;
	private int mprice;
	private String content;
	private int bestyn;
	private int useyn;
	private Date indate;
	private String thum;
	private List<GoodsImageVO> imageList;
}

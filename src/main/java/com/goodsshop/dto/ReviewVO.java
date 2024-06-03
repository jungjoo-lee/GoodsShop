package com.goodsshop.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ReviewVO {
	private int rseq;
	private String userid;
	private int grade;
	private int gseq;
	private String category;
	private String gname;
	private String subject;
	private String content;
	private Timestamp indate;
	private int giseq;
	private String realName;
	
	public ReviewVO() {}
}

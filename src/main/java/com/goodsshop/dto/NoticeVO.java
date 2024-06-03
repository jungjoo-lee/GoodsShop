package com.goodsshop.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class NoticeVO {
	private int nseq;
	private String adminId;
	private String subject;
	private String content;
	private Timestamp indate;
	
	public NoticeVO() {}
}
package com.goodsshop.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class QnaVO {
	private int qseq;
	private String userid;
	private String subject;
	private String content;
	private Timestamp indate;
	private String reply;
	private Timestamp replyDate;
	
	public QnaVO() {}
}

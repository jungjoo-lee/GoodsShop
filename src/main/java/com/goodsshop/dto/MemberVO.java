package com.goodsshop.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MemberVO {
	private String userid;
	private String pwd;
	private int gseq;
	private String grade;
	private int sale;
	private String name;
	private String email;
	private String phone;
	private String zip_code;
	private String address;
	private String d_address;
	private Timestamp indate;
	private Timestamp last_login_time;
	private int is_login;
	
	public MemberVO() {}
}

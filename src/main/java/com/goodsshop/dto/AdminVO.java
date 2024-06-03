package com.goodsshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AdminVO {
	private String adminId;
	private String pwd;
	private String name;
	private String phone;
	private String email;
}

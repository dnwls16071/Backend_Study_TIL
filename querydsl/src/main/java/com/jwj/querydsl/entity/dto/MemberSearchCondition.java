package com.jwj.querydsl.entity.dto;

import lombok.Data;

@Data
public class MemberSearchCondition {

	private String username;
	private String teamName;
	private Integer ageGoe;
	private Integer ageLoe;
}

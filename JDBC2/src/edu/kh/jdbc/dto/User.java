package edu.kh.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Data Transfer Object : 값을 묶어서 전달하는 용도의 객체
// - DB의 데이터를 전달하거나, 가져올때 사용
// DB 특정 테이블의 한 행의 데이터를 저장할 수 있는 형태로 class를 작성
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private String enrollDate;
	// enrollDate 는 왜 java.sql.Date가 아니라 String으로 이용하는가
	// -> DB 조회시 날짜 데이터를 원하는 문자의 형태의 문자열로
	//	  변환하여 조회할 예정, 날짜는 DB에서 다루는게 편해서
	// 		-> TOCHAR(DATE, 'YYMMDD') 이용예정
}

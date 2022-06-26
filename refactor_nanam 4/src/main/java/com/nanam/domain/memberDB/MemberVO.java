package com.nanam.domain.memberDB;
import lombok.*;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
public class MemberVO {

	private long id; //primary key

	private	String userId; //아이디
	private String userName; // 이름
	private	String userPwd; //비밀번호
	private	String userPwdConfirm; //비밀번호 확인
	private	String userEmail; //이메일
	private	String userPhone; //전화번호

	private	String zipcode; //우편번호
	private	String address; //주소
	private	String detailAddress; //상세주소

	private	Timestamp inDate;//가입일자
	private	Timestamp outDate;//탈퇴일자
	private	Timestamp modDate;//수정일자

	private	String useYnOfService; //이용여부

	private List<MemberVO> memberVOList;



}

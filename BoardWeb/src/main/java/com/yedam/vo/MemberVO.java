package com.yedam.vo;

import lombok.Data;

@Data
public class MemberVO {
	
	private String memberID;
	private String passwd;
	private String memberName;
	private String responsibility;


	
	public MemberVO() {
		
	}

	public MemberVO (String memberID, String passwd, String memberName) {
		
		this.memberID = memberID;
		this.passwd = passwd;
		this.memberName = memberName;
		
	}

}

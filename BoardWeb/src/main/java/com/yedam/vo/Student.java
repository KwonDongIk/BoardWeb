package com.yedam.vo;

public class Student {
	private String studentNo;
	private String studentName;
	private String telNo;
	private String address;
	
	
	public Student() {
		
	}
	
	public Student(String studentNo, String studentName, String telNo, String address) {
		this.studentNo = studentNo;
		this.studentName = studentName;
		this.telNo = telNo;
		this.address = address;
	}


	public String getStudentNo() {
		return studentNo;
	}


	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}


	public String getStudentName() {
		return studentName;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public String getTelNo() {
		return telNo;
	}


	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Student [studentNo=" + studentNo + ", studentName=" + studentName + ", telNo=" + telNo + ", address="
				+ address + "]";
	}
	

}


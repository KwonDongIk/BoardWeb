package com.yedam.vo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * 사원번호(정수타입)
 * 사원의 이름(홍길동, 김민수)
 * 전화번호(909-1234)
 * 입사일자(2020-02-03)
 * 급여(300, 350)
 */


@Setter
@Getter
@ToString
@NoArgsConstructor // 기본 생성자를 만드는 어노테이션
@AllArgsConstructor // 모든 매개값을 받는 생성자를 만드는 어노테이션

public class Employee { // tbl_employess 
	private int empNo; // emp_no 컬럼
	private String empName; // emp_name
	private String telNo; // tel_no
	private Date hireDate; // hire_date
	private int salary; // salray
	
	
	// 생성자
	public Employee(int empNo, String empName, String telNo) {
		this.empNo = empNo;
		this.empName = empName;
		this.telNo = telNo;
		this.hireDate = new Date();
		this.salary = 250;
	}
	
	public Employee(int empNo, String empName, String telNo, String hireDate, int salary) {
		this(empNo, empName, telNo); // Employee 에서 호출
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.hireDate = sdf.parse(hireDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.salary = salary;
		
	}
	
	// 사번, 이름, 연락처, 급여
	public String empInfo() {
		// 사번 이름 연락처 급여
		
		return empNo + "  " + empName + "  " + telNo + "  " + salary;
	}
	
	

}

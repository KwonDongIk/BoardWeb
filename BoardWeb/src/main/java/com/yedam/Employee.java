package com.yedam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 사원번호(정수타입)
 * 사원의 이름(홍길동, 김민수)
 * 전화번호(909-1234)
 * 입사일자(2020-02-03)
 * 급여(300, 350)
 */

public class Employee {
	private int empNo;
	private String empName;
	private String telNo;
	private Date hireDate;
	private int salary;
	
	
	// 생성자
	public Employee() {
		
	}
	
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

	// getter, setter
	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
	

}

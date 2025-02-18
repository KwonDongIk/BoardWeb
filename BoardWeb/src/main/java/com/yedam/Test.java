package com.yedam;

import java.util.List;

public class Test {
	public static void main(String[] args) {
		
		StudentDAO sdao = new StudentDAO();
		List<Student> list = sdao.studentList();
		for (Student std : list) {
			System.out.println(std.toString());
		}
		
		
	}
	
	

}

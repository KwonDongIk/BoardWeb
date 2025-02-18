package com.yedam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO extends DAO{
	
	public boolean addStudent(Student student) {
		String qry = "INSERT INTO tbl_student (student_no, student_name, phone, address)" +
					 " VALUES(?, ?, ?, ?)";
		
		try {
			psmt = getConnect().prepareStatement(qry);
			psmt.setString(1, student.getStudentNo());
			psmt.setString(2, student.getStudentName());
			psmt.setString(3, student.getTelNo());
			psmt.setString(4, student.getAddress());
			
			
			// 쿼리실행
			int r = psmt.executeUpdate(); // 처리된 건수 반환
			if (r > 0) {
				return true;
			}
			
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	
	// 학생목록을 반환 메소드
	public List<Student> studentList(){
		
		List<Student> stdList = new ArrayList<>();
		String qry = "SELECT * FROM tbl_student" +
					" ORDER BY student_no";
		
		try {
			psmt = getConnect().prepareStatement(qry);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				Student stdl = new Student();
				stdl.setStudentNo(rs.getString("student_no"));
				stdl.setStudentName(rs.getString("student_name"));
				stdl.setTelNo(rs.getString("phone"));
				stdl.setAddress(rs.getString("address"));
				
				stdList.add(stdl);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return stdList;
		
		
		
		
		
		
	}  // End of studentList

}

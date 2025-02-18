package com.yedam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;



public class EmpDAO extends DAO{ // DAO 상속
	
	
	// 상세조회
	public Employee selectEmp(int empNo) {
		String query = "select * from tbl_employees "
				+ "where emp_no = ?";
		try {
			psmt = getConnect().prepareStatement(query);
			psmt.setInt(1, empNo);
			
			rs = psmt.executeQuery(); // 조회
			if (rs.next()) { // 조회결과가 한건 있으면..
				Employee emp = new Employee();
				emp.setEmpNo(rs.getInt("emp_no")); // 칼럼값
				emp.setEmpName(rs.getString("emp_name"));
				emp.setTelNo(rs.getString("tel_no"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setSalary(rs.getInt("salary"));
				
				return emp;
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null; // 조회된 결과 없음 null
	}
	
	// 등록
	public boolean registerEmp(Employee emp) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String query = "insert into tbl_employees ";
		query += "values (" + emp.getEmpNo() 
				+ ", '" + emp.getEmpName() 
				+ "', '" + emp.getTelNo() 
				+ "', '" + sdf.format(emp.getHireDate()) 
				+ "', " + emp.getSalary()
				+ ")";
		
		try {
			Statement stmt = getConnect().createStatement();
			int r = stmt.executeUpdate(query);
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return false;
		
	} // End register
		
	
	// 목록
	public List<Employee> search(Employee emp) {
		
		List<Employee> empList = new ArrayList<>();
		String qry = "select * from tbl_employees" + 
				//" WHERE emp_name = nvl('" + emp.getEmpName() + "', emp_name) "
				" WHERE emp_name = nvl(?, emp_name)" // number, varchar2에 따라 처리
				+ " order by emp_no";
		
		try {
			//Statement stmt = getConnect().createStatement();
			PreparedStatement stmt = getConnect().prepareStatement(qry);
			stmt.setString(1, emp.getEmpName()); // 첫번째 ?에 사원이름을 대입
			ResultSet rs = stmt.executeQuery(); // 매개값이 없는 쿼리가 실행되어야함
			
			while(rs.next()) {
				Employee empl = new Employee();
				empl.setEmpNo(rs.getInt("emp_no"));
				empl.setEmpName(rs.getString("emp_name"));
				empl.setTelNo(rs.getString("tel_no"));
				empl.setHireDate(rs.getDate("hire_date"));
				empl.setSalary(rs.getInt("salary"));
				empList.add(empl);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return empList;
	}
}

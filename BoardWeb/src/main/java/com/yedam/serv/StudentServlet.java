//package com.yedam;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/studentServlet")
//public class StudentServlet extends HttpServlet{
//	
//	@Override
//	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		resp.setContentType("text/html;charset=utf-8");
//		PrintWriter out = resp.getWriter();
//		
//		StudentDAO sdao = new StudentDAO();
//		
//		List<Student> list = sdao.studentList(new Student());
//		
//		out.print("<table border='1'>");
//		out.print("<thead><tr><th>학번</th><th>이름</th><th>전화번호</th><th>주소</th></tr></thead>");
//		out.print("<tbody>");
//		
//		for(Student std : list) {
//			out.print("<tr>");
//			out.print("<td>" + std.getStudentNo() + "</td>");
//			out.print("<td>" + std.getStudentName() + "</td>");
//			out.print("<td>" + std.getTelNo() + "</td>");
//			out.print("<td>" + std.getAddress() + "</td>");
//			out.print("</tr>");
//		}
//		out.print("</tbody></table>");
//	}
//
//}
//package com;



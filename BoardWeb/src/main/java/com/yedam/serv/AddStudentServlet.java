package com.yedam.serv;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.StudentDAO;
import com.yedam.vo.Student;

@WebServlet("/addStudentServlet")
public class AddStudentServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String sno = req.getParameter("studentNo");
		String sname = req.getParameter("studentName");
		String tel = req.getParameter("telNo");
		String addr = req.getParameter("address");
		
		StudentDAO sdao = new StudentDAO();
		Student std = new Student();
		std.setStudentNo(sno);
		std.setStudentName(sname);
		std.setTelNo(tel);
		std.setAddress(addr);
		
		boolean result = sdao.addStudent(std);
		
		if (result) {
			resp.getWriter().print("처리성공");
			//resp.sendRedirect("sample"); // addEmpServlet
		} else {
			resp.getWriter().print("처리실패");		
		}
	
	}
	

}

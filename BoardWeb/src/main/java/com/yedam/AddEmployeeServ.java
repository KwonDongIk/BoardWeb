package com.yedam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// init - service - destroy : 서블릿의 생명주기
@WebServlet("/addEmpServlet")
public class AddEmployeeServ extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		String eno = req.getParameter("empNo"); // empNo의 parameter에 담겨있는 값 반환
		String ename = req.getParameter("empName");
		String tel = req.getParameter("telNo");
		
		EmpDAO edao = new EmpDAO();
		boolean result = edao.registerEmp(new Employee(Integer.parseInt(eno), ename, tel));
		
		if (result) {
			resp.sendRedirect("sample"); // addEmpServlet
		} else {
			resp.getWriter().print("처리실패");		
		}
	
	}

}

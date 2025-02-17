package com.yedam;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/empInfo")
public class GetEmployeeServ extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터 (?eno=1001)
		resp.setContentType("text/html;charset=utf-8");
		String eno = req.getParameter("eno");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		
		EmpDAO edao = new EmpDAO();
		Employee result = edao.selectEmp(Integer.parseInt(eno));
		String str = "<table border = 2>"; // <table><tr><th>사번</th><td>1</td></tr>...
		str += "<tr><th>사번</th><th>이름</th><th>전화</th><th>급여</th><th>입사일자</th></tr>";
		str += "<tr><td>"+result.getEmpNo()+"</td><td>"+result.getEmpName()+"</td><td>"
				+result.getTelNo()+"</td><td>"+result.getSalary()+"</td><td>"+sdf.format(result.getHireDate())+"</td></tr>";
		str += "</table>";
		str += "<a href = 'sample'>"+"목록이동"+"</a>";
		PrintWriter out = resp.getWriter(); // 출력 스트림
		out.print(str);
	}

}

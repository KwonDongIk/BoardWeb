package com.yedam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * 서블릿 생성(http 프로토콜 통해 web browser 출력)
 * 1. HttpServelt 을 상속
 * 2. WebApplicationServer(WAS) => tomcat
 * 3. WAS의 실행 순서 1) init 2) service 3) destroy
 */

public class SampleServlet extends HttpServlet {
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 호출, 최초 한번만 실행");
	}
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("service 호출, 호출마다 실행");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter(); // 출력 스트림 생성ㅣ
		for (int i = 0; i <= 3; i ++) {
			
		out.print("<h1>맨유 화이팅~~!</h1>");
		
		}
		
		EmpDAO edao = new EmpDAO();
		List<Employee> list = edao.search(new Employee());
		
		System.out.println("조회된 데이터 수 : " + list.size());
		
		for(Employee emp : list) {
			out.print("<p>사번 :  <a href='empInfo?eno="+emp.getEmpNo()+"'>"+ emp.getEmpNo() + "</a>, 이름 : " + emp.getEmpName() + "</p>");
			}
//		out.print("<script>alert('hi')</script>");
		}
	
	@Override
	public void destroy() {
		
		System.out.println("서버 종료");
	}
}

package com.yedam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.control.AddBoardListControl;
import com.yedam.control.BoardListControl;
import com.yedam.control.Control;

/*
 * MVC에서 Control 역할
 * url 요청 -> 서블릿 실행
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet{
	
	Map<String, Control> map;
	
	public FrontController() {
		map = new HashMap<>(); // map 필드의 초기화
		
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		//map.put("url", "servlet"); // addStudent.do AddStudentServlet
		map.put("/boardList.do", new BoardListControl());
		map.put("/addBoard.do", new AddBoardListControl());
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("Front Control");
		// http://localhost:8080/BoardWeb/adddStudent.do => url
		// /BoardWeb/adddStudent.do => uri
		String uri = req.getRequestURI(); // /BoardWeb/adddStudent.do
		String context = req.getContextPath(); // /BoardWeb
		String page = uri.substring(context.length());
		
		System.out.println(page);
		
		// map 컬렉션에서 key를 입력하면 val 반환
		Control control = map.get(page);
		control.exec(req, resp);
	}

}

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
import com.yedam.control.AddFormControl;
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
		map.put("/main.do", new MainControl());
		map.put("/boardList.do", new BoardListControl()); // 글목록
		map.put("/addForm.do", new AddFormControl()); // 등록화면
		map.put("/addBoard.do", new AddBoardListControl()); // 등록처리
		map.put("/board.do", new BoardControl()); // 상세화면
		map.put("/modifyForm.do", new ModifyControl()); // 수정화면
		map.put("/modifyBoard.do", new ModifyBoardControl()); // 수정처리
		map.put("/removeBoard.do", new RemoveBoardContorl()); // 삭제화면, 삭제처리
		
		// 로그인
		map.put("/loginForm.do", new LoginControl());
		map.put("/login.do", new LoginControl());
		map.put("/logout.do", new LogoutControl());
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

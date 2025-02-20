package com.yedam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.control.Control;
import com.yedam.dao.MemberDAO;
import com.yedam.vo.MemberVO;

public class LoginControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청방식 (get / post)
		if(req.getMethod().equals("GET")) {
			
			// 1. 로그인 화면
			req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
		} else if(req.getMethod().equals("POST")) {
		
			// 2. 로그인 기능
			String id = req.getParameter("uname");
			String pw = req.getParameter("psw");
			
			MemberDAO mdao = new MemberDAO();
			MemberVO mvo = mdao.login(id, pw);
			
			if(mvo != null) { // 로그인 성공
				
				System.out.println("환영해요." + mvo.getMemberName() + "님");
				HttpSession session = req.getSession();
				session.setAttribute("loginID", id); // attribute 활용
				resp.sendRedirect("boardList.do");
				
			} else { // 로그인 실패
				System.out.println("아이디와 비밀번호를 확인하세요.");
			}
		}
	}

}

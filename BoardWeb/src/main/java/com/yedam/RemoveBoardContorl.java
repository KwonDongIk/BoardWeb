package com.yedam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.control.Control;
import com.yedam.dao.BoardDAO;

public class RemoveBoardContorl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ?bno=6 글번호만 알고있으면 됨
		String bno = req.getParameter("bno");
		
		BoardDAO bdao = new BoardDAO();
		if (bdao.deleteBoard(Integer.parseInt(bno))){
			
			resp.sendRedirect("boardList.do");
			
		} else {
			
			System.out.println("처리를 실패했어요.");
			
		}

	}

}

package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.BoardDAO;
import com.yedam.vo.BoardVO;

public class AddBoardListControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("글 등록");
		// 3개 파라미터 활용 DB 저장 -> 목록으로 이동
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		
		// 매개값으로 활용
		BoardVO bvo = new BoardVO();
		bvo.setBoardTitle(title);
		bvo.setBoardContent(content);
		bvo.setBoardWriter(writer);
		
		
		BoardDAO bdao = new BoardDAO();
		if(bdao.insertBoard(bvo)) {
			// forward(매개값 활용) vs redirect(매개값을 전달 못함)
			resp.sendRedirect("boardList.do");
		} else {
			System.out.println("실패");
		}
		

	}

}

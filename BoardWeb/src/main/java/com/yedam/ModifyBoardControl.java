package com.yedam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.control.Control;
import com.yedam.dao.BoardDAO;
import com.yedam.vo.BoardVO;


public class ModifyBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ?bno=6&title=맨유+화이팅&content=맹구
		
		String bno = req.getParameter("bno");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		
		BoardVO board = new BoardVO();
		board.setBoardNo(Integer.parseInt(bno));
		board.setBoardTitle(title);
		board.setBoardContent(content);
		
		
		
		BoardDAO bdao = new BoardDAO();
		if(bdao.updateBoard(board)) { // 목록이동
			
			resp.sendRedirect("boardList.do");
			
		} else {
			
			System.out.println("처리를 실패했어요.");
			
		}

	}

}

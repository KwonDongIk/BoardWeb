package com.yedam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.control.Control;
import com.yedam.dao.BoardDAO;
import com.yedam.vo.BoardVO;

public class BoardControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String bno = req.getParameter("bno");
		
		BoardDAO bdao = new BoardDAO();
		bdao.updateCount(Integer.parseInt(bno));
		BoardVO board = bdao.getBoard(Integer.parseInt(bno));
		
		// 요청정보의 attribute 활용
		req.setAttribute("board", board);
		req.getRequestDispatcher("/WEB-INF/views/board.jsp").forward(req, resp);

	}

}

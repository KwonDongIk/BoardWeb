package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.PageVO;
import com.yedam.dao.BoardDAO;
import com.yedam.vo.BoardVO;

public class BoardListControl implements Control{
	
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//		System.out.println("글 목록");
//		String name = "호날두";
//		System.out.println(name);
//		req.setAttribute("msg", name);
		
		// boardList.do -> BoardListControl -> boardList.jsp
		// boardList.do?page=1
		String page = req.getParameter("page");
		
		page = page == null ? "1" : page; // 삼항연산자
		
		BoardDAO edao = new BoardDAO();
		List<BoardVO> list = edao.selectBoard(Integer.parseInt(page));
		req.setAttribute("list", list);
		
		// 페이징
		int totalCnt = edao.getTotalCount();
		PageVO paging = new PageVO(Integer.parseInt(page), totalCnt);
		req.setAttribute("paging", paging);
		
		// 요청 재지정(url:boardList.do (boardList.jsp))
		req.getRequestDispatcher("/WEB-INF/views/boardList.jsp").forward(req, resp);
			
		
	}

}

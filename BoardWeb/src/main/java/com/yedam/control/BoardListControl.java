package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.common.PageVO;
import com.yedam.common.SearchVO;
import com.yedam.mapper.BoardMapper;
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
		
		String sc = req.getParameter("searchCondition");
		String kw = req.getParameter("keyword");
		
		sc = sc == null ? "" : sc; // null 값
		kw = kw == null ? "" : kw; // null 값
		
		// SearchVO : 파라미터
		SearchVO search = new SearchVO(Integer.parseInt(page), sc, kw);
		
//		BoardDAO edao = new BoardDAO();
//		List<BoardVO> list = edao.selectBoard(search);
		SqlSession sqlSession = DataSource.getInstance().openSession();
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		List<BoardVO> list = mapper.selectBoard(search);
		req.setAttribute("list", list);
		
		// 페이징
		int totalCnt = mapper.getTotalCount(search); // 실제 건수
		PageVO paging = new PageVO(Integer.parseInt(page), totalCnt);
		req.setAttribute("paging", paging);
		
		// searchCondition, keyword 전달
		req.setAttribute("searchCondition", sc);
		req.setAttribute("keyword", kw);
		
		// 요청 재지정(url:boardList.do (boardList.jsp))
		req.getRequestDispatcher("board/boardList.tiles").forward(req, resp);
			
		
	}

}

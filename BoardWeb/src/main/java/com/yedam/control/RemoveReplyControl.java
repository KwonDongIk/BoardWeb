package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.dao.ReplyDAO;

public class RemoveReplyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 댓글 번호를 파라미터로... 
		String rno = req.getParameter("rno");
		
		ReplyDAO rdao = new ReplyDAO();
		boolean run = rdao.deleteReply(Integer.parseInt(rno));
		
		if (run) {
			
			resp.getWriter().print("{\"retCode\" : \"OK\"}");
			
		} else {
		
			resp.getWriter().print("{\"retCode\" : \"NG\"}");
			
		}

	}

}

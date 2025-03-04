package com.yedam.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.dao.ReplyDAO;
import com.yedam.vo.ReplyVO;

public class AddReplyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/json;charset=utf-8");
		// 댓글 내용, 작성자, 원본 글 번호
		
		String reply = req.getParameter("reply");
		String replyer = req.getParameter("replyer");
		String bno = req.getParameter("bno");
		
		// 매개값
		
		ReplyVO rvo = new ReplyVO();
		rvo.setBoardNo(Integer.parseInt(bno));
		rvo.setReply(reply);
		rvo.setReplyer(replyer);
		
		// DB 반영
		ReplyDAO rdao = new ReplyDAO();
		boolean run = rdao.insertReply(rvo);
		
		// 결과값
		Map<String, Object> result = new HashMap<>();
		
		if (run) {
			// {"retCode" : "OK"} Json으로..
			//resp.getWriter().print("{\"retCode\" : \"OK\"}");
			result.put("retCode", "OK");
			result.put("retVal", rvo);
		} else {
			//resp.getWriter().print("{\"retCode\" : \"NG\"}");
			result.put("retCode", "NG");
		}
		
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(result);
		
		resp.getWriter().print(json);
	}

}

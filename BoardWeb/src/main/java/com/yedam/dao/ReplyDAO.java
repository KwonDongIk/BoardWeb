package com.yedam.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.ReplyVO;

public class ReplyDAO extends DAO{
	
	// 목록
	public List<ReplyVO> replyList(int boardNo){
		
		String sql = "SELECT * FROM tbl_reply where board_no = ?";
		
		List<ReplyVO> list = new ArrayList<>();
		
		try {
			
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				ReplyVO reply = new ReplyVO();
				reply.setBoardNo(rs.getInt("board_no"));
				reply.setReply(rs.getString("reply"));
				reply.setReplyDate(rs.getDate("reply_date"));
				reply.setReplyNo(rs.getInt("reply_no"));
				reply.setReplyer(rs.getString("replyer"));
				
				
				list.add(reply);
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			disConnect();
		}
		
		return list;
	}
	
	// 상세
	public ReplyVO selectReply(int replyNo) {
		
		
		String sql = "SELECT * FROM tbl_reply where relpy_no = ?";
		
		List<ReplyVO> list = new ArrayList<>();
		
		try {
			
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, replyNo);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				
				ReplyVO reply = new ReplyVO();
				reply.setBoardNo(rs.getInt("board_no"));
				reply.setReply(rs.getString("reply"));
				reply.setReplyDate(rs.getDate("reply_date"));
				reply.setReplyNo(rs.getInt("reply_no"));
				reply.setReplyer(rs.getString("replyer"));
				
				
				return reply;
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			disConnect();
		}
		
		return null; // 조회결과 없음
	}
	
	// 등록
	public boolean insertReply(ReplyVO reply) {
		String sql1 = "SELECT reply_seq.nextval FROM dual";
		String sql = "INSERT INTO tbl_reply (reply_no, reply, replyer, board_no) VALUES (?, ?, ?, ?)";
		
		try {
			
			psmt = getConnect().prepareStatement(sql1);
			rs = psmt.executeQuery();
			if (rs.next()) {
				reply.setReplyNo(rs.getInt(1)); // 첫번째 칼럼
			}
			
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, reply.getReplyNo());
			psmt.setString(2, reply.getReply());
			psmt.setString(3, reply.getReplyer());
			psmt.setInt(4, reply.getBoardNo());
			
			int r = psmt.executeUpdate(); // 쿼리 실행
			
			if(r > 0) {
				
				return true;
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			disConnect();
			
		}
		
		return false;
	}
	
	// 삭제
	public boolean deleteReply(int replyNo){
		
		String qry = "DELETE from tbl_reply WHERE reply_no = ?";
		
		try {
			psmt = getConnect().prepareStatement(qry);
			psmt.setInt(1, replyNo);
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) { 
			
			e.printStackTrace();
			
		} finally {
			disConnect();
		}
		return false;
	}

}

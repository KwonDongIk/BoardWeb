package com.yedam.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yedam.vo.ReplyVO;

public class ReplyDAO extends DAO{
	
	// 부서별 인원 현황
	public List<Map<String, Object>> chartData() {
		String sql = "SELECT emp.department_id, dept.department_name, count(1) cnt "
					+ "FROM employees emp "
					+ "JOIN departments dept "
					+ "ON emp.department_id = dept.department_id "
					+ "GROUP BY emp.department_id, dept.department_name";
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		try {
			psmt = getConnect().prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("dept_name", rs.getString(2));
				map.put("dept_count", rs.getInt(3));
				list.add(map);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			disConnect();
		}
		return list;
	}
	
	
	// 댓글의 전체 건수(페이징)
	public int replyCount(int boardNo) {
		
		String sql = "SELECT COUNT(1) FROM tbl_reply WHERE board_no = ?";
		
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				
				return rs.getInt(1);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			disConnect();
			
		}
		return 0;
	}
	
	
	// 목록
	public List<ReplyVO> replyList(int boardNo, int page){
		
		String sql = "SELECT tbl_a.* "
					+ "FROM (SELECT /*+ INDEX_DESC (r pk_reply) */ ROWNUM rn, r.* FROM tbl_reply r WHERE board_no = ?) "
					+ "tbl_a WHERE tbl_a.rn > (?-1)*5 and tbl_a.rn <= ?*5 ";
		
		List<ReplyVO> list = new ArrayList<>();
		
		try {
			
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.setInt(2, page);
			psmt.setInt(3, page);
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
		
		
		String sql = "SELECT * FROM tbl_reply where reply_no = ?";
		
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

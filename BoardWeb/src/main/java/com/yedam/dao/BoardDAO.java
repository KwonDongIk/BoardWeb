package com.yedam.dao;

import java.sql.SQLException;
import java.util.*;
import com.yedam.vo.BoardVO;

/*
 * 추가, 수정, 삭제, 조회
 * Create, Read, Update, Delete
 */

public class BoardDAO extends DAO{
	
	// 조회
	public List<BoardVO> selectBoard(){
		
		List<BoardVO> bList = new ArrayList<>();
		String qry = "SELECT * FROM tbl_board" +
					" ORDER BY board_no";
		
		try {
			psmt = getConnect().prepareStatement(qry);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bvo = new BoardVO();
				bvo.setBoardNo(rs.getInt("board_no")); // 칼럼명
				bvo.setBoardTitle(rs.getString("title"));
				bvo.setBoardContent(rs.getString("content"));
				bvo.setBoardWriter(rs.getString("writer"));
				bvo.setBoardDate(rs.getDate("write_date"));
				bvo.setBoardView(rs.getInt("view_cnt"));
				
				bList.add(bvo);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bList;
	}
	
	
	// 추가
	public boolean insertBoard(BoardVO board) {
		return false;
	}
	
	// 수정
	public boolean updateBoard(BoardVO board) {
		return false;
	}
	
	
	// 삭제
	public boolean deleteBoard(int boardNo) {
		String qry = "DELETE FROM tbl_board WHERE board_no = ?";
		
		try {
			psmt = getConnect().prepareStatement(qry);
			psmt.setInt(1, boardNo);
			
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
}

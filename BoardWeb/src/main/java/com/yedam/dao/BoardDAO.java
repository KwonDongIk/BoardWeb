package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.BoardVO;

/*
 * 추가, 수정, 삭제, 조회
 * Create, Read, Update, Delete
 */

public class BoardDAO extends DAO {
	
	// 글 조회수 증가
	public void updateCount(int boardNo) {
		String sql = "UPDATE tbl_board SET view_cnt = view_cnt + 1 WHERE board_no = ?";
		
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.executeUpdate();
		} catch (SQLException e){
			e.printStackTrace();
			
		}
	}
	
	// 상세조회
	public BoardVO getBoard(int boardNo) {
		
		String sql = "SELECT * FROM tbl_board WHERE board_no = ?";
		
		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNo(rs.getInt("board_no"));
				board.setBoardTitle(rs.getString("title"));
				board.setBoardContent(rs.getString("content"));
				board.setBoardWriter(rs.getString("writer"));
				board.setBoardDate(rs.getDate("write_date"));
				board.setBoardView(rs.getInt("view_cnt"));
				
				return board;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null; // 조회결과 없음
	}
	

	// 조회
	public List<BoardVO> selectBoard() {

		List<BoardVO> bList = new ArrayList<>();
		String qry = "SELECT * FROM tbl_board" + " ORDER BY board_no";

		try {
			psmt = getConnect().prepareStatement(qry);
			rs = psmt.executeQuery();

			while (rs.next()) {
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
		String qry = "INSERT INTO tbl_board (board_no, title, content, writer) VALUES (board_seq.nextval, ?, ?, ?)";

		try {
			psmt = getConnect().prepareStatement(qry);
			psmt.setString(1, board.getBoardTitle());
			psmt.setString(2, board.getBoardContent());
			psmt.setString(3, board.getBoardWriter());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// 수정
	public boolean updateBoard(BoardVO board) {
		String qry = "UPDATE tbl_board SET title = ?, content = ? WHERE board_no = ?";
		
		try {
			psmt = getConnect().prepareStatement(qry);
			psmt.setString(1, board.getBoardTitle());
			psmt.setString(2, board.getBoardContent());
			psmt.setInt(3, board.getBoardNo());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e){
			e.printStackTrace();
			
		}
		
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

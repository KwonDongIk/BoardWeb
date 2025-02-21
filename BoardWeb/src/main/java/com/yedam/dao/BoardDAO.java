package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.BoardVO;
import com.yedam.vo.SearchVO;

/*
 * 추가, 수정, 삭제, 조회
 * Create, Read, Update, Delete
 */

public class BoardDAO extends DAO {
	
	// 페이징 처리를 위한 실제 데이터
	public int getTotalCount(SearchVO search) { // 해당 테이블 데이터 건수 모두 반환
		String sql = "SELECT count(1) FROM tbl_board";
		
		if (search.getSearchCondition().equals("T")) {
			sql += "	WHERE title like '%'||?||'%' ";
		} else if (search.getSearchCondition().equals("W")) {
			sql += "	WHERE writer like '%'||?||'%' ";
		} else if (search.getSearchCondition().equals("TW")) {
			sql += "	WHERE title like '%'||?||'%' or writer like '%'||?||'%' ";
		}
		
		
		try {
			psmt = getConnect().prepareStatement(sql);
			int cnt = 1;
			// 조건
			
			if (search.getSearchCondition().equals("T")) {
				psmt.setString(cnt++, search.getKeyword());
			} else if (search.getSearchCondition().equals("W")) {
				psmt.setString(cnt++, search.getKeyword());
			} else if (search.getSearchCondition().equals("TW")) {
				psmt.setString(cnt++, search.getKeyword());
				psmt.setString(cnt++, search.getKeyword());
			}
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				
				return rs.getInt(1); // count(1) 값
			
			}
		
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			disConnect(); // 정상실행이거나 예외 발생이나 반드시 실행할 코드
		}
		return 0;
	}

	// 글 조회수 증가
	public void updateCount(int boardNo) {
		String sql = "UPDATE tbl_board SET view_cnt = view_cnt + 1 WHERE board_no = ?";

		try {
			psmt = getConnect().prepareStatement(sql);
			psmt.setInt(1, boardNo);
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			disConnect(); // 정상실행이거나 예외 발생이나 반드시 실행할 코드
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
				board.setImg(rs.getString("img"));

				return board;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect(); // 정상실행이거나 예외 발생이나 반드시 실행할 코드
		}

		return null; // 조회결과 없음
	}

	// 조회
	public List<BoardVO> selectBoard(SearchVO search) {

		List<BoardVO> bList = new ArrayList<>();
		String qry = 	"SELECT * FROM( "
						+ "SELECT rownum rn, tbl_a.* FROM( "
						+ "SELECT * FROM tbl_board ";
					
						if (search.getSearchCondition().equals("T")) {
							qry += "	WHERE title like '%'||?||'%' ";
						} else if (search.getSearchCondition().equals("W")) {
							qry += "	WHERE writer like '%'||?||'%' ";
						} else if (search.getSearchCondition().equals("TW")) {
							qry += "	WHERE title like '%'||?||'%' or writer like '%'||?||'%' ";
						}
					
					qry += "ORDER BY board_no DESC) tbl_a) tbl_b "
						+ "WHERE tbl_b.rn >= (? - 1) * 5 + 1 and tbl_b.rn <= ? * 5";

		try {
			psmt = getConnect().prepareStatement(qry);
			
			int cnt = 1;
			// 조건
			
			if (search.getSearchCondition().equals("T")) {
				psmt.setString(cnt++, search.getKeyword());
			} else if (search.getSearchCondition().equals("W")) {
				psmt.setString(cnt++, search.getKeyword());
			} else if (search.getSearchCondition().equals("TW")) {
				psmt.setString(cnt++, search.getKeyword());
				psmt.setString(cnt++, search.getKeyword());
			}
			
			psmt.setInt(cnt++, search.getPage());
			psmt.setInt(cnt++, search.getPage());
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
		} finally {
			disConnect(); // 정상실행이거나 예외 발생이나 반드시 실행할 코드
		}
		return bList;
	}

	// 추가
	public boolean insertBoard(BoardVO board) {
		String qry = "INSERT INTO tbl_board (board_no, title, content, writer, img) VALUES (board_seq.nextval, ?, ?, ?, ?)";

		try {
			psmt = getConnect().prepareStatement(qry);
			psmt.setString(1, board.getBoardTitle());
			psmt.setString(2, board.getBoardContent());
			psmt.setString(3, board.getBoardWriter());
			psmt.setString(4, board.getImg());

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect(); // 정상실행이거나 예외 발생이나 반드시 실행할 코드
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
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			disConnect(); // 정상실행이거나 예외 발생이나 반드시 실행할 코드
		}

		return false;
	}

	// 삭제
	public boolean deleteBoard(int boardNo) {
		String qry = "DELETE FROM tbl_board WHERE board_no = ?";

		try {
			psmt = getConnect().prepareStatement(qry);
			psmt.setInt(1, boardNo); // ?에 값 지정

			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect(); // 정상실행이거나 예외 발생이나 반드시 실행할 코드
		}
		return false;
	}

}

package com.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.vo.MemberVO;

public class MemberDAO extends DAO{
	
	public List<MemberVO> members() {
		String sql = "SELECT * FROM tbl_member ORDER BY member_id";
		
		List<MemberVO> list = new ArrayList<>();
		
		try {
		
			psmt = getConnect().prepareStatement(sql);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) { // 조회된 결과가 있으면
				
				MemberVO mvo = new MemberVO();
				mvo.setMemberID(rs.getString("member_id"));
				mvo.setPasswd(rs.getString("passwd"));
				mvo.setMemberName(rs.getString("member_name"));
				mvo.setResponsibility(rs.getString("responsibility"));
				
				
				list.add(mvo);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		} finally {
		
			disConnect();
		
		}
		return list;
	}
	
	
	
	public MemberVO login(String id, String pw) {
		String sql = "SELECT * FROM tbl_member WHERE member_id = ? and passwd = ?";
		
		try {
		
			psmt = getConnect().prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) { // 조회된 결과가 있으면
				
				MemberVO mvo = new MemberVO();
				mvo.setMemberID(rs.getString("member_id"));
				mvo.setPasswd(rs.getString("passwd"));
				mvo.setMemberName(rs.getString("member_name"));
				mvo.setResponsibility(rs.getString("responsibility"));
				return mvo;
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		} finally {
		
			disConnect();
		
		}
		return null; // 조회결과 없음
	}
	
	

}

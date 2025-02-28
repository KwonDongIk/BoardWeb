package com.yedam.common;

import lombok.Data;

@Data
public class PageVO {
	// 192건 1~39페이지
	// 현재 페이지 : 2페이지. 1 ~ 10페이지
	private int startPage; // 첫 페이지
	private int endPage; // 마지막 페이지
	private int currentPage; // 현재 페이지
	private boolean prev, next; // 이전, 이후 페이지의 여부
	
	// 생성자
	
	public PageVO(int page, int totalCnt) {
		currentPage = page;
		endPage = (int) Math.ceil(currentPage / 5.0) * 5;
		startPage = endPage - 4; // 계산상의 start, end
		
		int realEnd = (int) Math.ceil(totalCnt / 5.0); // 실제 마지막 페이지
		endPage = endPage > realEnd ? realEnd : endPage;
		
		
		prev = startPage == 1 ? false : true;
		next = endPage == realEnd ? false : true;
	}

}

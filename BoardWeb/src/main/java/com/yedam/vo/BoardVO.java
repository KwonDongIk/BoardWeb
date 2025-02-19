package com.yedam.vo;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class BoardVO {
	private int boardNo; // 게시글 board_no~!~!
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private Date boardDate;
	private int boardView;

}


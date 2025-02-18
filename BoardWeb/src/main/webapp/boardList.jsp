<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="com.yedam.vo.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<style>
		*{
			margin: 0;
			padding: 0;
			font-family: "궁서", san-serif;
		}
		body {
			text-align: center;
		
		}
		h3 {
			font-size: 55px;
			margin: 30px;
		}
		.main_table{
			width: 1000px;
			height: 300px;
			font-size: 35px;
			margin: 0 auto;
			background-color: green;
			color: white;
			border: 3px solid black;
		}
		
		.main_table th{
			border: 5px solid white;
			padding: 10px;
		}
		
		.main_table td{
			border: 5px solid white;
			padding: 10px;
		}
	</style>

	<!-- html 주석문 -->
	// boardList.do -> request -> boardList.jsp
	<%
		String msg = "Hi";
		System.out.println(msg);
		String result = (String) request.getAttribute("msg");
		List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");
	%>
	<h3>게시글 목록</h3>
	<table class = 'main_table'>
		<tbody>
		<thead>
		<tr>
			<th>번호</th>
			<th>게시글 제목</th>
			<th>작성자</th>
			<th>작성날짜</th>
			<th>조회수</th>
		</tr>
		</thead>
	<%
		for (BoardVO board : list){
			
	%>
		<tr>
			<td><%=board.getBoardNo() %></td>
			<td><%=board.getBoardTitle() %></td>
			<td><%=board.getBoardWriter() %></td>
			<td><%=board.getBoardDate() %></td>
			<td><%=board.getBoardView() %></td>
		</tr>
	<%
		}
	%>
		</tbody>
	</table>
</body>
</html>
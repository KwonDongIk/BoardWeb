<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style>
	h1 {
		font-size: 80px !important;
		text-align: center;
	}
	
	#main_table{
			width: 1000px;
			height: 200px;
			font-size: 28px;
			margin: 0 auto;
			background-color: green;
			color: white;
			border: 3px solid black;
			text-align: center;
		}
	
</style>
<jsp:include page="includes/header.jsp"></jsp:include>
<h1>상세화면(board.jsp)</h1>
<%
	BoardVO board = (BoardVO) request.getAttribute("board");
%>
<form action="modifyForm.do">
<input type="hidden" name="bno" value="<%=board.getBoardNo() %>">
<table class="table" id="main_table">
	<tr>
		<th>번호</th><td><%=board.getBoardNo() %></td>
		<th>조회수</th><td><%=board.getBoardView() %></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><%=board.getBoardWriter() %></td>
		<th>작성날짜</th>
		<td><%=board.getBoardDate() %></td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="3"><%=board.getBoardContent() %></td>
	</tr>
	<tr>
		<td colspan="3">
		<button class = "btn btn-warning" type="submit">수정</button>
		<button class = "btn btn-danger" type="button">삭제</button>
		</td>
		
	</tr>
</table>
</form>

<jsp:include page="includes/footer.jsp"></jsp:include>
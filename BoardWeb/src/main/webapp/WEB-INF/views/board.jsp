<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<style>
	h1 {
		font-size: 80px !important;
		text-align: center;
		margin: 30px !important;
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
	
	#main_table span{
	
		font-family: "궁서", san-serif;
		text-decoration: underline;
		color: red;
		
	}
	
</style>
<jsp:include page="includes/header.jsp"></jsp:include>
<h1>상세화면(board.jsp)</h1>
<%
	BoardVO board = (BoardVO) request.getAttribute("board");
	String msg = (String) request.getAttribute("msg");
	String logID = (String) session.getAttribute("loginID");
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
		<th>제목</th>
		<td colspan="3"><%=board.getBoardTitle() %></td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="3"><%=board.getBoardContent() %></td>
	</tr>
	<tr>
		<td colspan="4">
		<button class = "btn btn-warning" type="submit">수정</button>
		<button class = "btn btn-danger" type="button">삭제</button><br>
		<%if (msg != null) {%>
		
			<span><%=msg %></span>
			
		<% }%>
		</td>
		
	</tr>
</table>
</form>
<script>

	let logid = "<%=logID%>";
	
	// 삭제버튼에 클릭 이벤트
	document.querySelector('button.btn-danger').addEventListener('click', function(e){
		
		let writer = document.querySelector('#main_table tr:nth-child(2) td:first-of-type').innerHTML;
		let bno = document.querySelector('input[name="bno"]').value;
		//console.log(bno);
		
		
		if (writer == logid){
			location.href = "removeBoard.do?bno=" + bno;
		} else {
			Swal.fire({
				  title: "권한을 확인하세요.",
				  text: "아이디가 달라요.",
				  icon: "error"
				});
		}
		
	});
	
</script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<jsp:include page="includes/footer.jsp"></jsp:include>
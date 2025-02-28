<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		
	.content{
			width: 1000px;
			font-size: 28px;
			margin: 0 auto;
			background-color: green;
			color: white;
			border: 3px solid black;
			text-align: center;
			margin-top: 30px;
	}
	
	.header{
			width: 1000px;
			font-size: 28px;
			margin: 0 auto;
			background-color: green;
			color: white;
			border: 3px solid black;
			text-align: center;
			margin-top: 30px;
	}
	
	#main_table span{
	
		font-family: "궁서", san-serif;
		text-decoration: underline;
		color: red;
		
	}
	
	.reply .content ul {
	
		list-style-type:none;
	
	}
	
	.reply .content span {
	
		display: inline-block;
	
	}
	
	.reply .content ul .li_1 {
	
		font-weight: bold;
	}
	
	.footer {
	
		margin-top: 30px;
		
	}
</style>
<h1>상세화면(board.jsp)</h1>

<form action="modifyForm.do">
<input type="hidden" name="bno" value="${board.boardNo }">
<table class="table" id="main_table">
	<tr>
		<th>번호</th><td><c:out value="${board.boardNo }"></c:out></td>
		<th>조회수</th><td><c:out value="${board.boardView }"></c:out></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><c:out value="${board.boardWriter }"></c:out></td>
		<th>작성날짜</th>
		<td><c:out value="${board.boardDate }"></c:out></td>
	</tr>
	<tr>
		<th>제목</th>
		<td colspan="3"><c:out value="${board.boardTitle }"></c:out></td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="3"><c:out value="${board.boardContent }"></c:out></td>
	</tr>
	<tr>
		<th>상세 이미지</th>
		<td colspan="3"><img src="images/${board.img}" style="width: 500px"></td>
	</tr>
	<tr>
		<td colspan="4">
		<button class = "btn btn-warning" type="submit">수정</button>
		<button class = "btn btn-danger" type="button">삭제</button><br>
		
		<c:if test="${msg != null }">
		
			<span>${msg }</span>
			
		</c:if>
		
		</td>
		
	</tr>
</table>
</form>


<!-- 댓글 관련 -->
<div class = "container reply">
	<!-- 댓글 등록 -->
	<div class = "header">
		<input type="text" id="reply" class="col-sm-9">
		<button id="addReply">댓글등록</button>

	</div>
	
	<!-- 댓글 목록 -->
	<div class = "content">
		<ul>
			<li class = "li_1">
				<span class = "col-sm-2">글번호</span>
				<span class = "col-sm-5">글내용</span>
				<span class = "col-sm-2">작성자</span>
				<span class = "col-sm-2">삭제</span>
				<hr>
			</li>
		</ul>
	
	</div>
	
	<!-- 댓글 페이징 -->
	<div class = "footer">
	<nav aria-label="Page navigation example">
	  <ul class="pagination justify-content-center">
	    
	  </ul>
	</nav>
	
	</div>

</div>

<script>

	let logid = "${loginID}";
	
	const bno = "${board.boardNo }";
	
	console.log(bno);
	
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
<script src="js/replyService.js"></script>
<script src="js/reply.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

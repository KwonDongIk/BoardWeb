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
	
	#main_table span{
	
		font-family: "궁서", san-serif;
		text-decoration: underline;
		color: red;
		
	}
</style>
<jsp:include page="includes/header.jsp"></jsp:include>
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
<script>

	let logid = "${loginID}";
	
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
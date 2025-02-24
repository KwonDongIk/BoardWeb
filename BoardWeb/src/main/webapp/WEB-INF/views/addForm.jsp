<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<style>
*{
	margin: 0;
	padding: 0;
}

h3 {
	font-size: 80px !important;
	margin: 30px !important;
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

table th {
			color: white;
		}
		
table td {
			color: white;
		}
</style>



<h3>글등록화면(addForm.jsp)</h3>
<form action="addBoard.do" method = "post" enctype="multipart/form-data">
	<table class="table" id = "main_table">
		<tr>
			<th>제목</th><td><input type="text" name="title" class="form-control"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="3" cols ="45" class="form-control" name="content"></textarea>
		</tr>
		<tr>
			<th>작성자</th><td><input type="hidden" name="writer" class="form-control" value="${loginID }">${loginID }</td>
		</tr>
		<tr>
			<th>이미지</th>
			<td><input type="file" name="img" class="form-control"></td>		
		</tr>
		<tr>
			<td colspan="2"><input class = "btn btn-primary" type="submit" value="등록">
							<input class = "btn btn-warning" type="reset" value="취소"></td>	
	</table>
</form>


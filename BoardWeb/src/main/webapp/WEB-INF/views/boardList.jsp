<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
	* {
		margin: 0;
		padding: 0;

	}

	body {
		text-align: center;

	}

	h3 {
		font-size: 80px !important;
		margin: 30px !important;
		text-align: center;
	}

	#main_form {
		display: flex;
		justify-content: center;
		margin: 20px auto;

	}

	#main_table {
		width: 1000px;
		height: 500px;
		font-size: 28px;
		margin: 0 auto;
		background-color: green;
		color: white;
		border: 3px solid black;
		text-align: center;
	}

	#main_table th {
		border: 5px solid green;
		padding: 10px;
	}

	#main_table td {
		border: 5px solid green;
		padding: 10px;
	}

	#main_table a {
		color: white;
		text-decoration: none;
	}

	#main_table a:hover {
		color: green;
		background-color: white;
		text-decoration: none;
	}

	#paging_nav {
		display: flex;
		justify-content: center;
		margin: 20px auto;
	}
	
</style>


<h3>게시글 목록</h3>
<form action="boardList.do" id="main_form">
	<div class="center">
		<div class="row">
			<div class="col-sm-3">
				<select class="form-control" name="searchCondition">
					<option value="">선택하세요</option>
					<option value="T" ${searchCondition == "T" ? "selected" : ""}>제목</option>
					<option value="W" ${searchCondition == "W" ? "selected" : ""}>작성자</option>
					<option value="TW" ${searchCondition == "TW" ? "selected" : ""}>제목 & 작성자</option>
				</select>
			</div>
			<div class="col-sm-7">
				<input type="text" class="form-control" name="keyword" value="${keyword }">
			</div>
			<div class="col-sm-2">
				<input type="submit" name="" value="조회" class="btn btn-primary">
			</div>
		</div>
	</div>
</form>
<table id='main_table' class='table table-hover'>
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
	<tbody>
		<c:forEach var="board" items="${list }">
			<tr>
				<td>
					<c:out value="${board.boardNo }"></c:out>
				</td>
				<td><a href="board.do?bno=${board.boardNo }">
						<c:out value="${board.boardTitle }"></c:out>
					</a></td>
				<td>
					<c:out value="${board.boardWriter }"></c:out>
				</td>
				<td>
					<c:out value="${board.boardDate }"></c:out>
				</td>
				<td>
					<c:out value="${board.boardView }"></c:out>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<!-- paging -->
<nav aria-label="..." id="paging_nav">
	<ul class="pagination">
		<!-- 이전 페이지 여부 -->
		<c:choose>
			<c:when test="${paging.prev }">
				<li class="page-item"><a class="page-link" href="boardList.do?page=${paging.startPage - 1 }&searchCondition=${searchCondition}&keyword=${keyword}">이전</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item disabled"><span class="page-link">이전</span></li>
			</c:otherwise>
		</c:choose>

		<!-- 페이지 start ~ end 반복 -->
		<c:forEach var="p" begin="${paging.startPage }" end="${paging.endPage }">
			<c:choose>
				<c:when test="${ p == paging.currentPage }">
					<li class="page-item active" aria-current="page"><span class="page-link">
							<c:out value="${p }"></c:out>
						</span></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="boardList.do?page=${p }&searchCondition=${searchCondition}&keyword=${keyword}">${p }</a></li>
				</c:otherwise>
			</c:choose>
		</c:forEach>

		<!--  <li class="page-item"><a class="page-link" href="boardList.do?page=">3</a></li> -->

		<!-- 이후 페이지 여부 -->
		<c:choose>
			<c:when test="${paging.next }">
				<li class="page-item"><a class="page-link" href="boardList.do?page=${paging.endPage + 1 }&searchCondition=${searchCondition}&keyword=${keyword}">다음</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item disabled"><span class="page-link">다음</span></li>
			</c:otherwise>
		</c:choose>


	</ul>
</nav>

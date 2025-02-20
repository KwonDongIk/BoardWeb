<%@page import="com.yedam.PageVO"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="com.yedam.vo.Employee"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<style>
		*{
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
		#main_table{
			width: 1000px;
			height: 500px;
			font-size: 28px;
			margin: 0 auto;
			background-color: green;
			color: white;
			border: 3px solid black;
			text-align: center;
		}
		
		#main_table th{
			border: 5px solid green;
			padding: 10px;
		}
		
		#main_table td{
			border: 5px solid green;
			padding: 10px;
		}
		
		#main_table a{
			color: white;
			text-decoration: none;
		}
		
		#main_table a:hover{
			color: green;
			background-color: white;
			text-decoration: none;
		}
		
		#paging_nav{
			display: flex;
			justify-content: center;
			margin: 20px auto;
		}
	</style>
	
	<jsp:include page="includes/header.jsp"></jsp:include>

	<!-- html 주석문 -->
	<%
	// boardList.do -> request -> boardList.jsp
		String msg = "Hi";
		System.out.println(msg);
		String result = (String) request.getAttribute("msg");
		List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");
		// Control에서 paging의 값을 얻어오기
		PageVO paging = (PageVO) request.getAttribute("paging");
	%>
	<h3>게시글 목록</h3> <!--  // page의 값은 <%=paging %> -->
	<table id = 'main_table' class = 'table table-hover'>
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
			<td><a href="board.do?bno=<%=board.getBoardNo() %>"><%=board.getBoardTitle() %></a></td>
			<td><%=board.getBoardWriter() %></td>
			<td><%=board.getBoardDate() %></td>
			<td><%=board.getBoardView() %></td>
		</tr>
	<%
		}
	%>
		</tbody>
	</table>
	<!-- paging -->
	<nav aria-label="..." id = "paging_nav">
		<ul class="pagination">
			<!-- 이전 페이지 여부 -->
			<%if (paging.isPrev()) {%>
			<li class="page-item"><a class="page-link" href="boardList.do?page=<%=paging.getStartPage()-1%>">이전</a></li>
			<%} else {%>
			<li class="page-item disabled"><span class="page-link">이전</span></li>
			<%} 
			 %>
			
			
			
			<!-- 페이지 start ~ end 반복 -->
			<%for (int p = paging.getStartPage(); p <= paging.getEndPage(); p++ ) {%>
			
			<%if (p == paging.getCurrentPage()) {%>
			
				<li class="page-item active" aria-current="page"><span class="page-link"><%=p %></span></li>
			
				<%} else {%>
			
			<li class="page-item"><a class="page-link" href="boardList.do?page=<%=p %>"><%=p %></a></li>
			<%}
			
			}%>
			
			<!--  <li class="page-item"><a class="page-link" href="boardList.do?page=">3</a></li> -->
			
			<!-- 이후 페이지 여부 -->
			<%if (paging.isNext()) {%>
			<li class="page-item"><a class="page-link" href="boardList.do?page=<%=paging.getEndPage()+1%>">다음</a></li>
			<%} else {%>
			<li class="page-item disabled"><span class="page-link">다음</span></li>
			<%} 
			 %>
			
			
		</ul>
	</nav>
<jsp:include page="includes/footer.jsp"></jsp:include>
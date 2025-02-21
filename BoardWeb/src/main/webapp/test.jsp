<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 연습</title>
</head>
<body>
	<h1>안녕하세요~</h1>
	<c:set var="msg" value="Hello"></c:set>
	
	<p>msg의 값은 <c:out value="${msg }"></c:out>입니다.</p>
	
	<!-- if 조건문 -->
	<c:set var="myAge" value="2" />
	<c:if test="${myAge >= 20}" >
		<p>당신은 성인입니다.</p>
	</c:if>
	<!-- if else -->
	<c:choose>
		<c:when test="${myAge >= 20}">
			<p> 당신은 성인입니다. </p>
		</c:when>
		<c:otherwise>
			<p> 당신은 미성년자입니다. </p>
		</c:otherwise>
	</c:choose>
	<!-- 반복 for문 -->
	<c:forEach var = "i" begin = "1" end = "10" step = "2">
		<p>i의 값은 ${i % 2}입니다.</p>
	</c:forEach>
	
	
</body>
</html>
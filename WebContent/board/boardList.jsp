<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/shopping.css">
<title>게시글 리스트</title>
</head>
<body>
<div id="wrap" align="center">
<h1>게시글 리스트</h1>
<table class="list">
<tr>
	<td colspan="5" style="border: white; text-align:right">
	<a href="BoardServlet?command=board_write_form">게시글 등록</a>
	</td>
</tr>

<tr>
	<th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회</th>
</tr>

<c:forEach var="board" items="${boardList}">
<tr class="record">
	<td>${board.num }</td>
	<td>
		<a href="BoardServlet?command=board_view&num=${board.num}">
		${board.title }
		</a>
		<td>${board.name }</td>
	<td><fmt:formatDate value="${board.writedate }"/></td>
	<td>${board.readcount}</td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>
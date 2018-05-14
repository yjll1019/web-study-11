<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>상품 관리</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
<!-- css 지정. -->
</head>
<body>
	<div id="wrap" align="center">
		<h1>상품 삭제 - 관리자 페이지</h1>
		<form action="productDelete.do" method="post">
			<table>
				<tr>
					<td><c:choose>
							<c:when test="${empty product.pictureUrl }">
								<img src="upload/noimage.jpg">
							</c:when>
							<c:otherwise>
								<img src="upload/${product.pictureUrl }">
							</c:otherwise>
						</c:choose></td>
					<td>
					<table>
							<tr>
								<th style="width: 80px">상품명</th>
								<td>${product.name }</td>
							</tr>
							<tr>
								<th style="width: 80px">원</th>
								<td>${product.price }</td>
							</tr>
							<tr>
								<th style="width: 80px">섦 명</th>
								<td>${product.description }</td>
							</tr>
						</table>
					</td>
					</tr>
					</table>
					<br>
					<input type="hidden" name="code" value="${product.code}">
					<input type="submit" value="삭제">
					<input type="button" value="목록" onclick="location.href='productList.do'">
		</form>
		</div>
</body>
</html>
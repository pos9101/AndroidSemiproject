<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
     <script type="text/javascript" src="./MovieJSP/jquery-3.1.1.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
		<hgroup>
			<h1>2관 오후</h1>
		</hgroup>
	</header>
		
		<table class="table table-bordered table-hover" border="1">
		<tr class="success">
			<th></th>
			<th>A</th>
			<th>B</th>
			<th>C</th>
			<th>D</th>
			<th>E</th>
			<th>F</th>
			<th>G</th>
		</tr>
		<c:forEach var="i" begin="0" end="5" step="1">
			<tr>
				<td class="success">${i+1}</td>
				<td><a href="cinema22insertOK.do?id=admin&ciNm=22&seat=A${i+1}">A${i+1}</a></td>
				<td><a href="cinema22insertOK.do?id=admin&ciNm=22&seat=B${i+1}">B${i+1}</a></td>
				<td><a href="cinema22insertOK.do?id=admin&ciNm=22&seat=C${i+1}">C${i+1}</a></td>
				<td><a href="cinema22insertOK.do?id=admin&ciNm=22&seat=D${i+1}">D${i+1}</a></td>
				<td><a href="cinema22insertOK.do?id=admin&ciNm=22&seat=E${i+1}">E${i+1}</a></td>
				<td><a href="cinema22insertOK.do?id=admin&ciNm=22&seat=F${i+1}">F${i+1}</a></td>
				<td><a href="cinema22insertOK.do?id=admin&ciNm=22&seat=G${i+1}">G${i+1}</a></td>
			</tr>

		</c:forEach>
		
		<form action="index.do"><button type="submit" class="btn btn-info">홈페이지</button></form>

</body>
</html>
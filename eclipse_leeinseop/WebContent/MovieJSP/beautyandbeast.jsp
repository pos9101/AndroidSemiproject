<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<script type="text/javascript" src="./MovieJSP/jquery-3.1.1.js"></script>
	
	
	<%
	String id = request.getParameter("id");
	session.setAttribute("id", id);
	System.out.println("받음>>>>"+id);
	%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container-fluid">
  <div class="row">


	<hgroup>
	<div class="container">
	<h3><p class="text-center">영화관, 영화시간선택</p></h3>
	</div>
	</hgroup>
	
	
	<p class="text-center">
	<img src="./img/beautyandbeast.jpg" class="img-rounded" width="200px"
		height="267px">
	</p>
		
		
	<div class="container">
	<form action="cinema11select.do">
		<input type="hidden" name="id" value ="<%=id%>"/>
		<p class="text-center"><button type="submit" class="btn btn-info">1관 오전</button></p>
	</form>
	</div>
	
	
	<div class="container">
	<form action="cinema12select.do">
		<input type="hidden" name="id" value ="<%=id%>"/>
		<p class="text-center"><button type="submit" class="btn btn-info">1관 오후</button></p>
	</form>
	</div>
	
	<div class="container">
	<a><p class="text-center">아이디: <%=id%></p></a>
	</div>


</div>
</div>
</body>
</html>
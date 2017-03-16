<%@page import="test.com.SeatDAOimpl"%>
<%@page import="test.com.SeatDAO"%>
<%@page import="test.com.SeatVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String id = session.getAttribute("id").toString();
	String seat = request.getParameter("seat");
	String seat_ciNm = request.getParameter("seat_ciNm");
	int ciNm = Integer.parseInt(request.getParameter("ciNm"));
	String cinema = null;
	String cinemaNumber = null;
	System.out.println("search.jsp "+seat);
	System.out.println("search.jsp "+ciNm);

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
  
  
  
	<%if((ciNm%2)==1){cinema="오전";}else if((ciNm%2)==0){cinema="오후";}
	  if(ciNm<13){cinemaNumber="1관";}else if(ciNm<23&&ciNm>13){cinemaNumber="2관";}else if(ciNm<33&&ciNm>23){cinemaNumber="3관";}%>
		<h3><strong><mark><%=id%></mark></strong>회원님은 <strong><mark><%=cinemaNumber%> <%=cinema %></mark></strong> <strong><mark><%=seat%></mark></strong>를 선택하셨습니다.</h3>


		<div class="container">
		<form action="seatsearch.do">
			<p class="text-center"><button type="submit" class="btn btn-info">확인</button></p>
		</form>
		</div>
		
		
		<div class="container">
		<form action="cinema<%=ciNm%>deleteOK.do">
			<p class="text-center"><button type="submit" class="btn btn-info" name="deleteBtn" value=<%=seat_ciNm%>>취소</button></p>
		</form>
		</div>
		
		
		
<%-- 		<a>seat_ciNm : <%=seat_ciNm %></a> --%>
	
	
</div>
</div>	
</body>
</html>
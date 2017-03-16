<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<hgroup>
			<h1>영화선택</h1>
		</hgroup>
<!-- 		<form action="cinema11select.do"><button type="submit" class="btn btn-info">1관 오전</button></form> -->
<!-- 		<form action="cinema12select.do"><button type="submit" class="btn btn-info">1관 오후</button></form> -->
<!-- 		<form action="cinema21select.do"><button type="submit" class="btn btn-info">2관 오전</button></form> -->
<!-- 		<form action="cinema22select.do"><button type="submit" class="btn btn-info">2관 오후</button></form> -->
<!-- 		<form action="cinema31select.do"><button type="submit" class="btn btn-info">3관 오전</button></form> -->
<!-- 		<form action="cinema32select.do"><button type="submit" class="btn btn-info">3관 오후</button></form> -->
		<br/>
		
		<form action="kong.do"><button type="submit" class="btn btn-info">콩: 스컬 아일랜드</button></form>
		<form action="logan.do"><button type="submit" class="btn btn-info">로건</button></form>
		<form action="haebing.do"><button type="submit" class="btn btn-info">해빙</button></form><br/>
		
		<form action="cinemaALLjson.do"><button type="submit" class="btn btn-info">전 극장 전 좌석 제이슨</button></form>
<!-- 		<form action="cinemaSelectedjson.do"><button type="submit" class="btn btn-info">제이슨2</button></form> -->
		<form action="confirm.do"><button type="submit" class="btn btn-info">내 예매확인</button></form>
		<!-- <a href="/cinema11select.do">1관</a> -->
	</header>

</body>
</html>
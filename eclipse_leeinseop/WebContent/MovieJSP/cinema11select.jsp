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
			<h1>1관 오전</h1>
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
				<td id="A${i}"><a href="cinema11insertOK.do?id=admin&ciNm=11&seat=A${i+1}">A${i+1}</a></td>
				<td id="B${i}"><a href="cinema11insertOK.do?id=admin&ciNm=11&seat=B${i+1}">B${i+1}</a></td>
				<td id="C${i}"><a href="cinema11insertOK.do?id=admin&ciNm=11&seat=C${i+1}">C${i+1}</a></td>
				<td id="D${i}"><a href="cinema11insertOK.do?id=admin&ciNm=11&seat=D${i+1}">D${i+1}</a></td>
				<td id="E${i}"><a href="cinema11insertOK.do?id=admin&ciNm=11&seat=E${i+1}">E${i+1}</a></td>
				<td id="F${i}"><a href="cinema11insertOK.do?id=admin&ciNm=11&seat=F${i+1}">F${i+1}</a></td>
				<td id="G${i}"><a href="cinema11insertOK.do?id=admin&ciNm=11&seat=G${i+1}">G${i+1}</a></td>
			</tr>

		</c:forEach>
		
		<form action="index.do"><button type="submit" class="btn btn-info">홈페이지</button></form>
		
		
		
		<li role="presentation"><a id="target">좌석현황 <script type="text/javascript">
      $(function() {
         console.log("ready...");
         $("#target").on("click", function() {
            $.ajax({ //비동기통신
               url : "http://localhost:8090/Semiproject_movie/cinema1json.do",
               type : 'post',
               contentType : "application/x-www-form-urlencoded",
               data : {
                  method : "post", //키:값
                  program : "CHROME"
               },
               dataType : "json",
               success : function(data, status) {
                  console.log("Success!!");
//                   console.log(data.length);
//                   console.log(status);
//                   console.log(data);
                  var arr = [];
                  for(var i in data){
                     var id = data[i].seat.toString();
                     console.log("id:"+id);
                     arr.push(id);
                  }
                  for(var i in arr){
                     console.log(arr[i]);
                     $("#"+arr[i]).html("<strong><mark>X</mark></strong>");
                  }

               },
               error : function(xhr, desc, err) {
                  console.log(xhr);
                  console.log("Desc: " + desc + "\nErr:" + err);
               }
            });

         });

      });
   </script>
   </a></li>
	
	

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/bigdataShop/common/css/jqcloud.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript" src="/bigdataShop/common/js/jqcloud-1.0.4.js"></script>
<script type="text/javascript">

	var word_array = new Array();
	 
	<c:forEach var="words" items="${wordlist}">
		var data = new Object();
	
		data.text="${words.word}";
		data.weight=${words.hit};
		word_array.push(data);
	</c:forEach>
	
	$(function(){
		$("#example").jQCloud(word_array);
	})
</script>
<title>Insert title here</title>
</head>
<body>
	<div class="col-md-4">
		<h1>상품댓글분석</h1>
		<br />
		<table class="table">
			<thead>
				<tr>
					<th scope="col">키워드</th>
					<th scope="col">반복횟수</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${!empty wordlist}">
					<c:forEach var="words" items="${wordlist}">
						<tr>
							<td scope="row">${words.word}</td>
							<td scope="row">${words.hit}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
	 <div id="example" class="col-md-8" style="width: 550px; height: 350px;"></div>
</body>
</html>
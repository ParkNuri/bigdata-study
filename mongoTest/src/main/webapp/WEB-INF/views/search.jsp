<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,spring.data.mongodb.dto.*"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="/mongoTest/score/search">
		<h1>검색하기</h1>
		검색할 필드선택: <select name="field">
			<option value="name">성명</option>
			<option value="id">아이디</option>
			<option value="addr">주소</option>
			<option value="dept">부서</option>
			<option value="java">java</option>
			<option value="spring">spring</option>
			<option value="servlet">servlet</option>
			<option value="bonus">bonus</option>

		</select>
		<hr/> 
		
		숫자연산:<select name="operator">
			<option>=</option>
			<option>></option>
			<option>>=</option>
			<option>&lt;</option>
			<option>&lt;=</option>
			<option>&lt;&eq;</option>
		</select>
		<h3>검색값:</h3>
		<input type="text" name="value" />
		<input type="submit" value="검색"/>
	</form>
</body>
</html>











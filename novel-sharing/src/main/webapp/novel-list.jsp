<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="model.dao.*" %>
<%@ page import="model.bean.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>小説一覧</title>
<link rel="stylesheet" href="./css/sanitize.css">
</head>
<body>
	<div>
		<jsp:include page="/includes/navbar.jsp" />
	</div>
	
		<input type="text" name="search" placeholder="タイトルを検索">
	<div>
		<jsp:include page="/includes/author-list.jsp" />
		
		<!-- 小説一覧 -->
	</div>
</body>
</html>
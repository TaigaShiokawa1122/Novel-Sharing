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
<link rel="stylesheet" href="./css/novel-list.css">
</head>
<body>

	<jsp:include page="/includes/navbar.jsp" />
	
	<input type="text" name="search" placeholder="タイトルを検索">
	
	<jsp:include page="/includes/author-list.jsp" />

		
	<!-- 小説一覧 -->

	<% List<NovelBean> novelList = (List<NovelBean>) request.getAttribute("novelList"); %>
	<% String novelUnregistered = (String) request.getAttribute("novelUnregistered"); %>
		<% if (novelUnregistered != null) { %>
		<p><%= novelUnregistered %></p>
		<% } else { %>
			<% for (NovelBean novels : novelList) { %>
				<img alt="No Image" src="./images/<%=novels.getImage() %>">
				<p><%=novels.getTitle() %></p>
			<% } %>
		<% } %>
</body>
</html>
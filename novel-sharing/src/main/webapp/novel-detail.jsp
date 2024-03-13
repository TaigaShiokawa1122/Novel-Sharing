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
<title>小説詳細</title>
<link rel="stylesheet" href="./css/sanitize.css">
</head>
<body>
	<!-- ナビゲーション -->
	<jsp:include page="/includes/navbar.jsp" />
	
	<!-- 検索ボックス 全ての小説 -->
	<form action="NovelListServlet" method="get">
		<input type="text" name="search" placeholder="タイトルを検索">
	</form>
	
	<!-- 作家一覧 -->
	<jsp:include page="/includes/author-list.jsp" />

		
	<!-- 小説一覧 -->
	<% NovelBean novel = (NovelBean) request.getAttribute("novel"); %>
	<% String noDetails = (String) request.getAttribute("noDetails"); %>
	
	<% if (noDetails != null) { %>
	<p><%=noDetails %></p>
	<% } else { %>
		<img alt="No Image" src="./images/<%=novel.getImage() %>">
		<p><%=novel.getAuthor().getAuthorName() %></p>
		<p><%=novel.getTitle() %></p>
		<p><%=novel.getGenre().getGenre_name() %></p>
		<p><%=novel.getSummary() %></p>
	<% } %>

</body>
</html>
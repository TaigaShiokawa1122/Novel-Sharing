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
<title>ジャンル別 小説一覧</title>
<link rel="stylesheet" href="./css/sanitize.css">
</head>
<body>

	<!-- ナビゲーション -->
	<jsp:include page="/includes/navbar.jsp" />
	
	<!-- 検索ボックス 全ての小説 -->
	<% Integer genreId = (Integer)request.getSession().getAttribute("genreId");  %>
	<form action="NovelListByGenreServlet" method="get">
		<input type="text" name="search" placeholder="タイトルを検索">
		<input type="hidden" name="genreId" value="<%=genreId %>">
	</form>
	
	<!-- 作家一覧 -->
	<jsp:include page="/includes/author-list.jsp" />
	
	<!-- 小説一覧 -->
	<% List<NovelBean> novelList = (List<NovelBean>) request.getAttribute("novelList"); %>
	<% String novelUnregistered = (String) request.getAttribute("novelUnregistered"); %>
	
	<% if (novelUnregistered != null) { %>
	<p><%= novelUnregistered %></p>
	<% } else { %>
		<% for (NovelBean novel : novelList) { %>
		<a href="NovelDetailServlet?novelId=<%=novel.getNovelId() %>">
			<img alt="小説画像" src="./images/<%= novel.getImage() %>">
		</a>
		<p><%= novel.getTitle() %></p>
		<% } %>
	<% } %>
	
</body>
</html>
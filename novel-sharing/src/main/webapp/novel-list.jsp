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
	<!-- ナビゲーション -->
	<jsp:include page="/includes/navbar.jsp" />
	
	<!-- 検索ボックス 全ての小説 -->
	<form action="NovelListServlet" method="get">
		<input type="text" name="search" placeholder="タイトルを検索">
	</form>
	
	<!-- 作家一覧 -->
	<jsp:include page="/includes/author-list.jsp" />

		
	<!-- 小説一覧 -->
	<% List<NovelBean> novelList = (List<NovelBean>) request.getAttribute("novelList"); %>
	<% String novelUnregistered = (String) request.getAttribute("novelUnregistered"); %>
	<% String noSearchResult = (String) request.getAttribute("noSearchResult"); %>
	
	<% if (noSearchResult != null) { %>
	<p><%=noSearchResult %></p>
	<% } else if (novelUnregistered != null) { %>
	<p><%=novelUnregistered %></p>
	<% } else { %>
		<% for (NovelBean novels : novelList) { %>
		<a href="NovelDetailServlet?novelId=<%=novels.getNovelId() %>">
			<img alt="No Image" src="./images/<%=novels.getImage() %>">
		</a>
			<p><%=novels.getTitle() %></p>
			<p><%=novels.getAuthor().getAuthorName() %></p>
		<% } %>
	<% } %>

</body>
</html>
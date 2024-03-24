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
<title>管理画面 作家別 小説一覧</title>
<link rel="stylesheet" href="./css/sanitize.css">
</head>
<body>
	<!-- ナビゲーション -->
	<jsp:include page="/includes/admin-navbar.jsp" />
	
	<!-- 検索ボックス 全ての小説 -->
	<% Integer authorId = (Integer)request.getSession().getAttribute("authorId");  %>
	<form action="AdminNovelListByAuthorServlet" method="get">
		<input type="text" name="search" placeholder="タイトルを検索">
		<input type="hidden" name="authorId" value="<%=authorId %>">
	</form>
	
	<!-- 作家一覧 -->
	<jsp:include page="/includes/admin-author-list.jsp" />
	
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
	 	<a href="AdminNovelEditServlet?novelId=<%=novels.getNovelId() %>">
	 		<img alt="小説画像" src="./images/<%=novels.getImage() %>">
	 	</a>
	 	<p><%=novels.getTitle() %></p>
	 	<% } %>
	<% } %>
</body>
</html>
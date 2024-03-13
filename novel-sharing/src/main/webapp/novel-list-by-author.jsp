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
<title>作家別 小説一覧</title>
<link rel="stylesheet" href="./css/sanitize.css">
</head>
<body>
	<!-- ナビゲーション -->
	<jsp:include page="/includes/navbar.jsp" />
	
	<!-- 検索ボックス 全ての小説 -->
	<% Integer authorId = (Integer)request.getSession().getAttribute("authorId");  %>
	<form action="NovelListByAuthorServlet" method="get">
		<input type="text" name="search" placeholder="タイトルを検索">
		<input type="hidden" name="authorId" value="<%=authorId %>">
		<a href="aaa?aa=<%=authorId %>"></a>
	</form>
	
	<!-- 作家一覧 -->
	<jsp:include page="/includes/author-list.jsp" />
	
	<!-- 小説一覧 -->
	<% List<NovelBean> novelList = (List<NovelBean>) request.getAttribute("novelList"); %>
	<% String novelUnregistered = (String) request.getAttribute("novelUnregistered"); %>
	<% String noSearchResult = (String) request.getAttribute("noSearchResult"); %>
	
	<% if (noSearchResult != null) { %>
	<p><%=noSearchResult %></p>
	<% } else if (novelUnregistered) { %>
	<p><%=novelUnregistered %></p>
	<% } else { %>
	 	<% for (NovelBean novels : novelList) { %>
	 	<p><%=novels.getTitle() %></p>
	 	<% } %>
	<% } %>
</body>
</html>
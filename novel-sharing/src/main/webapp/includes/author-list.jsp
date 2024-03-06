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
<link rel="stylesheet" href="./css/sanitize.css">
</head>
<body>
	<div class="author-container">
		<h2 class="author">作家一覧</h2>
		<% List<AuthorBean> authorList = (List<AuthorBean>)request.getAttribute("authorList"); %>
		<% String authorUnregistered = (String)request.getAttribute("authorUnregistered"); %>
		
		<% if (authorUnregistered != null) { %>
		<p><%= authorUnregistered %></p>
		<% } else { %>
			<% for (AuthorBean author : authorList) { %>
			<ul>
				<li><a href="#"><%=author.getAuthorName() %></a></li>
			</ul>
			<% } %>
		<% } %>
	</div>
</body>
</html>
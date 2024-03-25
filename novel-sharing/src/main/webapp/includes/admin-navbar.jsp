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
<link rel="stylesheet" href="./css/navbar.css">
</head>
<body>
	<div class="header">
		<img alt="サイトロゴ" src="../images/">
		<div class="navbar">
			<ul>
				<li><a href="AdminNovelListServlet">トップ</a></li>
				<% List<GenreBean> genreList = (List<GenreBean>)request.getAttribute("genreList"); %>
				<% for (GenreBean genre : genreList) { %>
					<li><a href="AdminNovelListByGenreServlet?genreId=<%=genre.getGenreId() %>"><%=genre.getGenre_name() %></a></li>
				<% } %>
			</ul>
		</div>
	</div>
</body>
</html>
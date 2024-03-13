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
				<li><a href="NovelListServlet">トップ</a></li>
				<li><a href="NovelListByGenreServlet?genreId=1">恋愛</a></li>
				<li><a href="NovelListByGenreServlet?genreId=2">SF / ホラー / ファンタジー</a></li>
				<li><a href="NovelListByGenreServlet?genreId=3">ミステリー / サスペンス</a></li>
				<li><a href="NovelListByGenreServlet?genreId=4">現代文学</a></li>
			</ul>
		</div>
	</div>
</body>
</html>
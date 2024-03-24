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
<title>管理者 小説一覧</title>
<link rel="stylesheet" href="./css/sanitize.css">
<link rel="stylesheet" href="./css/novel-list.css">
</head>
<body>

	
	<form action="AdminNovelListServlet" method="get">
		<input type="text" name="search" placeholder="タイトルを検索">
	</form>
	
	<jsp:include page="/includes/admin-author-list.jsp" />
	
	<p>${message}</p>
		
	<!-- 小説一覧 -->
	<% List<NovelBean> novelList = (List<NovelBean>) request.getAttribute("novelList"); %>
	<% String novelUnregistered = (String) request.getAttribute("novelUnregistered"); %>
	<% String noSearchResult = (String) request.getAttribute("noSearchResult"); %>
	
	<% if (noSearchResult != null) { %>
	<p><%=noSearchResult %></p>
	<% } else if (novelUnregistered != null) { %>
	<p><%=novelUnregistered %></p>
	<% } else { %>
		<ul>
			<% for (NovelBean novels : novelList) { %>
				<li> <!-- 管理者小説詳細へ -->
					 <a href="AdminNovelEditServlet?novelId=<%= novels.getNovelId() %>">
						<img alt="No Image" src="./images/<%=novels.getImage() %>">
						<p><%=novels.getTitle() %></p>
					</a>
				</li>
			<% } %>
		</ul>
	<% } %>

</body>
</html>
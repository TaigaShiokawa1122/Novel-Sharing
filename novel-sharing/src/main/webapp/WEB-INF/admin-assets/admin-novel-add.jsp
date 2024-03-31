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
<title>管理者 小説追加</title>
<link rel="stylesheet" href="./css/sanitize.css">
<link rel="stylesheet" href="./css/novel-list.css">
<link rel="stylesheet" href="./css/admin-novel-edit.css">
<% List<GenreBean> genreList = (ArrayList <GenreBean>)request.getAttribute("genreList"); %>
<% List<AuthorBean> authorList = (ArrayList <AuthorBean>)request.getAttribute("authorList"); %>
<% NovelBean novel = (NovelBean)request.getAttribute("novel"); %>
	</head>
	<body>
	
	
		<jsp:include page="/includes/admin-navbar.jsp" />
		<jsp:include page="/includes/admin-author-list.jsp" />
		
		<p>${message}</p>
		
				<div class="novels_add">
						
						<div id="novel-detail">
							<form action="AdminNovelAddServlet" method="post">
								<label>画像の登録フォーム未実装</label><br>
								<label>タイトル</label> <input type="text" name="title" value="${title}" required><br>
								<label>ジャンル</label><br>
								<select id="genre" name="genreId" required>
					          		<% for ( GenreBean genre : genreList){ %>
									 <option value="<%= genre.getGenreId() %>"><%= genre.getGenre_name() %></option>
									<% } %> 
					           </select><br>
								<label>著者</label><br>
								<select id="author" name="authorId" required>
					          		<% for ( AuthorBean author : authorList){ %>
									 <option value="<%= author.getAuthorId() %>"><%= author.getAuthorName() %></option>
									<% } %> 
					           </select><br>
								<label>サマリー</label><input type="text" name="summary" value="${summary}" required><br>
								<input type="submit" value="送信">
							</form>
						</div>
						
	
	</body>
	<script>

	for( let j = 0 ; j <= 3 ; j++ ){
		const buttonOpen = document.getElementById("modalOpen" + j);
		const modal = document.getElementById("easyModal" + j);
		const buttonClose = document.getElementsByClassName('modalClose')[j];
	
		console.log(buttonOpen);
	
		// ボタンがクリックされた時
		buttonOpen.addEventListener('click', modalOpen);
		function modalOpen() {
		  modal.style.display = 'block';
		}
	
		// バツ印がクリックされた時
		buttonClose.addEventListener('click', modalClose);
		function modalClose() {
		  modal.style.display = 'none';
		}
	
		// モーダルコンテンツ以外がクリックされた時
		addEventListener('click', outsideClose);
		function outsideClose(e) {
		  if (e.target == modal) {
		    modal.style.display = 'none';
		  }

		}} 
	  
	</script>
</html>
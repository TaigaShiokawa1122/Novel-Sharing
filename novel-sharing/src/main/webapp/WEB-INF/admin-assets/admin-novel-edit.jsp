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
<title>管理者 小説編集</title>
<link rel="stylesheet" href="./css/sanitize.css">
<link rel="stylesheet" href="./css/novel-list.css">
<% List<GenreBean> genreList = (ArrayList <GenreBean>)request.getAttribute("genreList"); %>
<% List<AuthorBean> authorList = (ArrayList <AuthorBean>)request.getAttribute("authorList"); %>
	</head>
	<body>
	
		
		<form action="AdminNovelListServlet" method="get">
			<input type="text" name="search" placeholder="タイトルを検索">
		</form>
		
		<jsp:include page="/includes/admin-author-list.jsp" />
		
				<div class="novels_edit">
						<div class="novel_img">
							<% for ( AdminBean columns : novel){ 
						  		String img = columns.getImage();	
						  		if (img.isEmpty()){ %>
		                  	   		//画像の登録がない場合の処理設ける？
					        	<% } else { %>
					     	   		<img src="<%= columns.image() %>">
					       		<% } %>
						</div> <!-- novel_img 閉じタグ -->
						<div id="novel-detail">
							<p><%= columns.getTitle() %><p><label id="modalOpen0">編集</label>
							<p><%= columns.getGenre() %><p><label id="modalOpen1">編集</label>
							<p><%= columns.getAuthor() %><p><label id="modalOpen2">編集</label>
							<p><%= columns.getSummary() %><p><label id="modalOpen3">編集</label>
							
						</div>
						
							<div id="easyModal0" class="modal">
								 <div class="modal-content">
								     <div class="modal-body">
									        <div class="update_form">
										        <label class="modalClose">キャンセル</label>
										        <form action="AdminNovelEditServlet" method="post">
										           <input type="text" name="title" value="<%= columns.getTitle() %>">
										           <input type="hidden" name="novelId" value="<%= columns.getNovelId() %>">
										           <input type="hidden" name="type" value="title">
										           <input class="update_btn" type="submit" value="変更する">
										        </form>
									        </div>
								      </div>
								 </div>
							 </diV>
							 
							 <div id="easyModal1" class="modal">
								 <div class="modal-content">
								     <div class="modal-body">
									        <div class="update_form">
										        <label class="modalClose">キャンセル</label>
										        <form action="AdminNovelEditServlet" method="post">
										           <select id="genre" name="genre" required>
										          		<% for ( GenreBean genre : genreList){ %>
														 <option value="<%= genre.getGenreId() %>"><%= genre.getGenreName() %></option>
														<% } %> 
										           </select><br>
										           <!--  後ほどここでジャンル新規登録できるようにする -->
										           <input type="hidden" name="novelId" value="<%= columns.getNovelId() %>">
										           <input type="hidden" name="type" value="genre">
										           <input class="update_btn" type="submit" value="変更する">
										        </form>
									        </div>
								      </div>
								 </div>
							 </diV>
							 
							 <div id="easyModal2" class="modal">
								 <div class="modal-content">
								     <div class="modal-body">
									        <div class="update_form">
										        <label class="modalClose">キャンセル</label>
										        <form action="AdminNovelEditServlet" method="post">
										        	<select id="author" name="author" required>
										          		<% for ( AuthorBean author : authorList){ %>
														 <option value="<%= author.getAuthorId() %>"><%= author.getAuthorName() %></option>
														<% } %> 
										           </select><br>
										           <input type="hidden" name="novelId" value="<%= columns.getNovelId() %>">
										           <input type="hidden" name="type" value="author">
										           <!--  後ほどここで著者新規登録できるようにする -->
										           <input class="update_btn" type="submit" value="変更する">
										        </form>
									        </div>
								      </div>
								 </div>
							 </diV>
							 
							 <div id="easyModal3" class="modal">
								 <div class="modal-content">
								     <div class="modal-body">
									        <div class="update_form">
										        <label class="modalClose">キャンセル</label>
										        <form action="AdminNovelEditServlet" method="post">
										           <input type="text" name="summary" value="<%= columns.getSummary() %>">
										           <input type="hidden" name="novelId" value="<%= columns.getNovelId() %>">
										           <input type="hidden" name="type" value="summary">
										           <input class="update_btn" type="submit" value="変更する">
										        </form>
									        </div>
								      </div>
								 </div>
							 </diV>
						 <% } %> 
				</div>
	
	</body>
	<script>

	for( let j = 0 ; j < 3 ; j++ ){
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
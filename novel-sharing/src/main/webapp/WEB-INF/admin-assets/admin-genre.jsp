<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
<%@ page import="model.bean.*" %>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>ジャンル関係編集ページ</title>
		</head>
		<body>
			<%-- 作成後、コメントアウト外す
				<div>
					<jsp:include page="/includes/admin-navbar.jsp" />
				</div>
				<div>
					<jsp:include page="/includes/admin-author-list.jsp" />
				</div> 
			--%>
			<main>
				<div class="genre_list">
					<p>${message}</p>
					<% List<GenreBean> genreList = (List<GenreBean>)request.getAttribute("genreList"); %>
					<p>${genreDeleteError}</p>
					<% if(genreList == null) { %>
						<p>現在、登録されているジャンルはありません</p>
						<% } else {  %>	
								<% for(GenreBean genre : genreList) { %>
									<form action="AdminDeleteGenreServlet" method="post">
										<label>
											<%= genre.getGenre_name() %>
							    			<input type="hidden" name="genreId" value="<%=genre.getGenreId()%>">
							    			<input type="hidden" name="genreName" value="<%=genre.getGenre_name()%>">
							    			<button type="submit">削除</button><br>
							    		</label>
							    	</form>
								<% } %>
					<% } %>
				</div>
				
				<div class="ganre_add_form">
					<form action="AdminGenreServlet" method="post">
						<p class="error">${error}</p> <!-- 既に使用済みだった場合 -->
						<input type="text" id="genre" name="genre" placeholder="ジャンル名" value="" required><br> <!-- 失敗時、入力値残るようにする? -->
						<button type="submit">ジャンル新規登録</button><br>
					</form>
				</div>
			</main>
		</body>
	</html>
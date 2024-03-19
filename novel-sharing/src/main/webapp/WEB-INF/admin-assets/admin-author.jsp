<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
<%@ page import="model.bean.*" %>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>著者新規登録</title>
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
				<div class="author_list">
					<p>${message}</p>
					<% List<AuthorBean> authorList = (List<AuthorBean>)request.getAttribute("authorList"); %>
					<p>${authorDeleteError}</p>
					<% if(authorList == null) { %>
						<p>現在、登録されている著者はいません</p>
						<% } else {  %>	
								<% for(AuthorBean author : authorList) { %>
									<form action="AdminDeleteAuthorServlet" method="post">
										<label>
											<%= author.getAuthorName() %>　<%= author.getFurigana() %>
							    			<input type="hidden" name="authorId" value="<%= author.getAuthorId()%>">
							    			<input type="hidden" name="authorName" value="<%= author.getAuthorName()%>">
							    			<button type="submit">削除</button><br>
							    		</label>
							    	</form>
								<% } %>
					<% } %>
				</div>
				
				<div class="author_add_form">
					<form action="AdminAuthorServlet" method="post">
						<p class="error">${error}</p> <!-- 既に使用済みだった場合 -->
						<input type="text" id="authorName" name="authorName" placeholder="著者名" value="" required><br> <!-- 失敗時、入力値残るようにする? -->
						<input type="text" id="furigana" name="furigana" placeholder="ふりがな" value="" pattern="^[ぁ-ん]+$" required><br> <!-- 失敗時、入力値残るようにする? -->
						<button type="submit">著者新規登録</button><br>
					</form>
				</div>
			</main>
		</body>
	</html>
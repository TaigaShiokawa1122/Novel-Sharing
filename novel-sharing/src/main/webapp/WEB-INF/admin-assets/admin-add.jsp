<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
<%@ page import="model.bean.*" %>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>管理者新規登録</title>
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
				<div class="admin_add_form">
					<form action="AdminAddServlet" method="post">
						<p class="error">${error}</p> <!-- 既に使用済みのメールアドレスだった場合 -->
						<input type="text" id="adminName" name="adminName"  placeholder="管理者氏名" required><br> <!-- 失敗時、名前残るようにする? -->
						<input type="email" id="email" name="email"  placeholder="email" required><br> <!-- 失敗時、メアド残るようにする? -->		
						<input type="password" id="password" name="password" placeholder="password" required><br>
						<button type="submit">管理者新規登録</button><br>
					</form>
				</div>
			</main>
		</body>
	</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
<%@ page import="model.bean.*" %>
<!DOCTYPE html>
	<html>
		<head>
			<meta charset="UTF-8">
			<title>管理者ログイン</title>
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
				<div class="login_form">
					<form action="AdminLoginServlet" method="post">
						<p class="logmiss">${notFound}</p> <!-- ログイン失敗した時 -->
						<input type="email" id="email" name="email"  placeholder="email" required><br> <!-- 失敗時、メアド残るようにする? -->		
						<input type="password" id="password" name="password" placeholder="password" required><br>
						<button type="submit">login</button><br>
					</form>
				</div>
			</main>
		</body>
	</html>
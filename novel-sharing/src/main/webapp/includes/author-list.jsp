<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<%@ page import="model.dao.*" %>
<%@ page import="model.bean.*" %>
<%@ page import="java.util.*" %>
	
	<h2 class="author">作家一覧</h2>
	<% List<AuthorBean> authorList = (List<AuthorBean>)request.getAttribute("authorList"); %>
	<% String authorUnregistered = (String)request.getAttribute("authorUnregistered"); %>
	
	<% if (authorUnregistered != null) { %>
	<p><%= authorUnregistered %></p>
	<% } else { %>
		<ul>
			<li><a href="NovelListServlet">全ての作家</a></li>
		</ul>
		<% for (AuthorBean author : authorList) { %>
		<ul>
			<li><a href="NovelListByAuthorServlet?authorId=<%=author.getAuthorId() %>"><%=author.getAuthorName() %></a></li>
		</ul>
		<% } %>
	<% } %>

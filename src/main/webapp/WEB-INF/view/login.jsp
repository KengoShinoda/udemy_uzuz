<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<title>ログイン画面</title>
</head>
<body>
	<h1>ログイン</h1>
	<form action="<%=request.getContextPath()%>/ExecuteLogin" method="post">
		<p>
			ユーザーID：<br> <input type="text" name="USER_ID" id="ID_USER_ID"
				maxlength="20">
		</p>
		<p>
			パスワード：<br> <input type="password" name="PASSWORD"
				id="ID_PASSWORD" maxlength="20">
		</p>
		<input type="submit" value="ログイン" id="ID_SUBMIT">
	</form>
	<script type="text/javascript" src="js/brank-check.js"></script>
	<a href="<%=request.getContextPath()%>/NewAccount">新規アカウント作成</a>
</body>
</html>

<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<html>
<head>
<title>新規アカウント作成画面</title>
</head>
<body>
	<h2>新規アカウント作成</h2>
	<form action="<%=request.getContextPath()%>/NewAccountCreate" method="post">
		<p>
			ユーザーID: <input type= "text" name= "ID" maxlength="20">
		</p>
		<p>
			名前： <input type="text" name="NAME" maxlength="20">			
		</p>
		<p>
			年齢： <input type="number" name="AGE" id="ID_AGE" maxlength="3">
		</p>
		<p>
			性別： <input type="radio" name="SEX" value="1" checked>オス <input
				type="radio" name="SEX" value="2">メス
		</p>
		<p>
			パスワード：<input type= "password" name= "PASSWORD"  maxlength="20">
		</p>
		<input type="submit" value="完了" id="ID_SUBMIT">
	</form>
	<p>
		<a href="<%=request.getContextPath()%>/Login">ログイン画面に戻る</a>
	</p>
</body>
</html>




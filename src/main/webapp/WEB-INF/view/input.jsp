<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.MatchUserDto"%>

<%
String userName = (String)request.getAttribute("USER_NAME");
%>
<%
int age = (int)request.getAttribute("AGE");
%>
<%
int sex = (int)request.getAttribute("SEX");
%>


<html>
<head>
<%--<meta charset="UTF-8"> --%>
<title>回答入力</title>
</head>
<body>
	<h2>わんちゃん暮らし改善アンケートフォーム</h2>
	<form action="<%=request.getContextPath()%>/SaveSurvey" method="post">
		<p>
			名前： <%=userName%>
			<input type="hidden" name="NAME" value="<%=userName %>">
		</p>
		<p>
			年齢： <%=age%> 
			<input type="hidden" name="AGE" id="ID_AGE" maxlength="3" value="<%=age%>">
		</p>
		<p>
			性別：
		    <% 
			switch (sex) {
			case 1:
			%>
			オス
			<%
			break;
			case 2:
			%>
			メス
			<%
			break;
			}
			%>
			<input type="hidden" name="SEX" value="<%=sex%>">
		</p>
		<%--
		<p>
			年齢： <input type="number" name="AGE" id="ID_AGE" maxlength="3">
		</p>
		<p>
			性別： <input type="radio" name="SEX" value="1" checked>オス <input
				type="radio" name="SEX" value="2">メス
		</p>
		 --%>
		<p>
			満足度： <select name="SATISFACTION_LEVEL">
				<option value="5">とても満足</option>
				<option value="4">満足</option>
				<option value="3">普通</option>
				<option value="2">不満</option>
				<option value="1">とても不満</option>
			</select>
		</p>
		<p>
			飼い主へのご意見・ご感想をご記入ください：<br>
			<textarea name="MESSAGE" id="ID_MESSAGE" rows="4" cols="50"
				maxlength="250"></textarea>
		</p>
		<input type="submit" value="回答する(SaveSurveyを起動)" id="ID_SUBMIT">
	</form>

	<script type="text/javascript" src="js/illegal-check.js"></script>

	<p>
		<a href="<%=request.getContextPath()%>/ShowAllSurvey">回答一覧画面へ</a>
	</p>
	<p>
		<a href="<%=request.getContextPath()%>/ExecuteLogout">ログアウトする</a>
	</p>
</body>
</html>

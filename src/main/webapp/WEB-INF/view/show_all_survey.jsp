<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="model.ShowAllSurveyDto"%>
<%@ page import="controller.ShowAllSurvey"%>

<%
List<ShowAllSurveyDto> selectContentList = (List<ShowAllSurveyDto>) request.getAttribute("SELECT_CONTENT");
%>

<html>
<head>

<title>回答一覧</title>
</head>
<body>
	<h2>アンケート回答一覧</h2>

	<table class="list" border=1 id="TABLE">
		<tr bgcolor="#c0c0c0">
			<th>名前</th>
			<th>年齢</th>
			<th>性別</th>
			<th>満足度</th>
			<th>ご意見・ご要望</th>
			<th>回答時間</th>
		</tr>
		<%
		for (int i = 0; i < selectContentList.size(); i++) {
			ShowAllSurveyDto dto = selectContentList.get(i);
		%>
		<tr>
			<td><%=dto.getName()%></td>
			<td><%=dto.getAge()%></td>

			<%
			switch (dto.getSex()) {
			case 1:
			%>
			<td>オス</td>
			<%
			break;
			case 2:
			%>
			<td>メス</td>
			<%
			break;
			}
			%>

			<%
			switch (dto.getSatisfactionLevel()) {
			case 1:
			%>
			<td>とても不満</td>
			<%
			break;
			case 2:
			%>
			<td>不満</td>
			<%
			break;
			case 3:
			%>
			<td>普通</td>
			<%
			break;
			case 4:
			%>
			<td>満足</td>
			<%
			break;
			case 5:
			%>
			<td>とても満足</td>
			<%
			break;
			}
			%>
			<td><%=replaceEscapeChar(dto.getMessage())%></td>
			<td><%=dto.getTime()%></td>
		</tr>
		<%
		}
		%>
	</table>

	<p>
		<a href="<%=request.getContextPath()%>/InputSurvey">回答画面に戻る</a>
	</p>
</body>
</html>


<%!
   String replaceEscapeChar(String inputText) {
       String changeText = inputText.replace("<", "&lt;");
       changeText = changeText.replace(">", "&gt;");
       changeText = changeText.replace("\"", "&quot;");
       changeText = changeText.replace("'", "&#039;");
       return changeText;
   }
%>




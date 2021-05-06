<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mailForm</title>
</head>
<body>
	<form action="/mail/sendMail.do" method="post">
		<table border="1">
			<tr>
				<td>받는사람</td>
				<td><input type="text" name="toMail" style="width: 500px;"
					required></td>
			</tr>
			<tr>
				<td>메일제목</td>
				<td><input type="text" name="title" style="width: 500px;"
					required></td>
			</tr>
			<tr>
				<td>메일내용</td>
				<td><textarea name="contents" required
						style="width: 500px; height: 200px"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="발송">
					<input type="reset" value="다시작성"></td>
			</tr>

		</table>

	</form>

</body>
</html>



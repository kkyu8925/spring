<%@page import="poly.dto.UserInfoDTO"%>
<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	//컨트롤러에서 전달받은 msg
	String msg = CmmUtil.nvl((String) request.getAttribute("msg"));

	//컨트롤러에서 전달받은 웹 데이터 DTO
	UserInfoDTO pDTO = (UserInfoDTO) request.getAttribute("pDTO");

	if (pDTO == null) {
		pDTO = new UserInfoDTO();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message</title>
<script type="text/javascript">
	alert("<%=msg%>");
</script>
</head>
<body>
	

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="static poly.util.CmmUtil.nvl"%>
<%
	//Controller 에서 model.addattribute("res"),String.valueOf(res));로부터 저장된 값 불러오기
	//CmmUtil.nvl() 함수를 통해 model.addattribute("res") 저장한 값이 null인 경우, 0으로
	//0이면 메일 발송 실패하는 것으로 스프링의 Service에서 정의했기때문에 0으로 함.
	String jspRes = nvl((String) request.getAttribute("res"), "0");

	//웹 url으로붜 전달받은 값들
	String toMail = nvl(request.getParameter("toMail"));
	String title = nvl(request.getParameter("title"));
	String contents = nvl(request.getParameter("contents"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일 발송 결과 보기</title>
</head>
<body>
	<%
		// 메일 발송 성공시
		if (jspRes.equals("1")) {
			out.println(toMail + "로 메일 전송이 성공하였습니다.");
			out.println("메일 제목 : " + title);
			out.println("메일 내용 : " + contents);
		} else { // 메일 발송 실패시
			out.println(toMail + "로 메일 전송이 실패하였습니다.");
		}
	%>

</body>
</html>
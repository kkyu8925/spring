<%@page import="poly.dto.BoardDTO"%>
<%@page import="static poly.util.CmmUtil.nvl"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<BoardDTO> rList = (List<BoardDTO>) request.getAttribute("rList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <style>
    #paging {
        list-style-type:none;
	}
    #paging li {      
        margin:3px;
        cursor:pointer;
        float:left;
        color: #666;
        font-size: 1.1em;
	}
    #paging li.selected {      
        color: #0080ff;
        font-weight:bold;
	}
    #paging li:hover {      
        color: #0080ff;
	}

  </style>
<script src="http://code.jquery.com/jquery-1.11.0.js"> </script>
<script src="/js/paging.js"> </script>
</head>
<body>
	<div style="margin: auto; width: 800px;">
		<table border="1" style="width: 100%;">
			<thead>
				<tr>
					<td>글번호</td>
					<td>제목</td>
					<td>게시일</td>
					<td>게시자</td>
				</tr>
			</thead>
			<tbody>
				<%
					for (BoardDTO e : rList) {
				%>
				<tr>
					<td><%=nvl(e.getPost_no())%></td>
					<td><a href="/board/boardDetail.do?no=<%=e.getPost_no()%>"><%=nvl(e.getPost_title())%></a></td>
					<td><%=nvl(e.getReg_dt())%></td>
					<td><%=nvl(e.getReg_id())%></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
		<div style="width: 100%; text-align: right; margin-top: 5px;">
			<button type=button onclick="location.href='/board/newPost.do'">새글</button>
		</div>
	</div>

<!--현재는 페이징 영역 ID "paging" 으로 고정 -->
<ul id="paging">
</ul>

<script>
    //pager객체 생성
    var page = new pager();

    //클릭했을때 사용할 콜백함수 지정. 
    //여기서는 테스트용함수 지정.
    //게시판 같은경우 리스트를 호출하는 함수 지정하면된다.
    page.buttonClickCallback = listContent;
    
    //테스트용.
    function listContent () {
        //console.log(pageCurrent);
        //alert(pageCurrent);        
        
        //Ajax 작업 (S)


        //페이징 처리를 위해 총레코드수를 매개변수로 전달해야함.
        page.renderpager(1000,20); 
        
        //Ajax 작업 (E)
    }

    //최초 로딩시 실행.
    listContent();
</script>
</body>
</html>
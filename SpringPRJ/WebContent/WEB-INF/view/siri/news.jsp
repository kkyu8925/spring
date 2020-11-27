<%@ page import="poly.util.CmmUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: siri
  Date: 2020-11-20
  Time: 오후 5:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String SS_QUERY = CmmUtil.nvl((String)session.getAttribute("SS_QUERY"));
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Naver news 검색</title>
    <script type="text/javascript" src="/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript">

        $(document).ready(function(){

            getNaverNews();

            $("#execute").click(function(){
                getNaverNews();
            });

            $('#display').change(function(){
                getNaverNews();
            });

            $('#sort').change(function(){
                getNaverNews();
            });

        });

        function getNaverNews(){

                $.ajax({
                    url: "/getSearchNews.do",
                    type: "post",
                    dataType:"JSON",
                    data : $("form").serialize(),
                    success : function(json) {
                        let resHTML ="";
                        let resObj = json.res;

                        for(let i=0; i<resObj.length;i++) {
                            resHTML += '<tr>';
                            resHTML += '<td>'+resObj[i].title+'</td>'; // 개별 검색 결과
                            resHTML += '<td><a href='+resObj[i].originallink+'>신문사링크</a></td>'; // 검색 결과 문서의 제공 언론사 하이퍼텍스트 link
                            resHTML += '<td><a href='+resObj[i].link+'>네이버링크</a></td>'; // 검색 결과 문서의 제공 네이버 하이퍼텍스트 link
                            resHTML += '<td>'+resObj[i].description+'</td>'; // 검색 결과 문서의 내용을 요약한 패시지 정보
                            resHTML += '<td>'+resObj[i].pubDate+'</td>'; // 검색 결과 문서가 네이버에 제공된 시간
                            resHTML += '</tr>';
                        }
                        $('#newsRes').html(resHTML);

                    }, error: function(json){
                        console.log(json);
                        resHTML += '<td colspan="5">조회된 데이터가 없습니다.</td>';
                        $('#newsRes').html(resHTML);
                    }
                })
        }
    </script>
</head>
<body>
<table border="1" style="width: 100%;">
    <thead>
        <td>제목</td>
        <td>신문사링크</td>
        <td>네이버링크</td>
        <td>요약</td>
        <td>날짜</td>
    </thead>
    <tbody id="newsRes">

    </tbody>
</table>
<form name="form" method="post" onsubmit="return false;">
    <input type="search" id="query" name="query" value="<%=SS_QUERY%>">
    <input type="button" id="execute" value="검색">
    <select id="display" name="display">
        <option value="10" selected="selected">10</option>
        <option value="20">20</option>
        <option value="50">50</option>
        <option value="100">100</option>
    </select>
</form>

</body>
</html>

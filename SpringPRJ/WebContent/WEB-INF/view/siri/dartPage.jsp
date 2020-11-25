<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="poly.dto.DartDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %><%--
  Created by IntelliJ IDEA.
  User: siri
  Date: 2020-11-16
  Time: 오후 5:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dart API</title>
    <link rel="stylesheet" href="/css/paging.css">
    <script type="text/javascript" src="/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="/js/dartPage.js"></script>
    <style>
        .pblntf_detail_ty__option{
            display: none;
        }
    </style>
</head>
<body>
<div style="margin: auto; width: 80%;">
    <a href =></a>
    <table border="1" style="width: 100%;">
        <thead>
            <td>
                고유번호(corp_code)
            </td>
            <td>
                법인구분(corp_cls)
            </td>
            <td>
                종목코드(stock_code)
            </td>
            <td>
                종목명(법인명)(corp_name)
            </td>
            <td>
                보고서명(report_nm)
            </td>
            <td>
                접수번호(rcept_no)
            </td>
            <td>
                공시 제출인명(flr_nm)
            </td>
            <td>
                공시 접수일자(rcept_dt)
            </td>
            <td>
                비고(rm)
            </td>
        </thead>

        <tbody id="dartRes">

        </tbody>
    </table>
    <form name="form" method="post">
        corp_code(고유번호 8자리) : <input type="number" name="corp_code" minlength="8" maxlength="8"/><br/>

        bgn_de(시작일)(YYYYMMDD) : <input type="number" name="bgn_de" minlength="8" maxlength="8"/><br/>

        end_de(종료일)(YYYYMMDD) : <input type="number" name="end_de" minlength="8" maxlength="8"/><br/>

        pblntf_ty(공시유형): <select id="pblntf_ty" name="pblntf_ty">
                                <option value="" selected="selected">전체</option>
                                <option value="A">정기공시</option>
                                <option value="B">주요사항보고</option>
                                <option value="C">발행공시</option>
                                <option value="D">지분공시</option>
                                <option value="E">기타공시</option>
                                <option value="F">외부감사관련</option>
                                <option value="G">펀드공시</option>
                                <option value="H">자산유동화</option>
                                <option value="I">거래소공시</option>
                                <option value="J">공정위공시</option>
                            </select><br/>

        pblntf_detail_ty(공시상세유형) <select id="pblntf_detail_ty" name="pblntf_detail_ty">

                                        <option value="" selected="selected">전체</option>

                                        <option class="pblntf_detail_ty__option a_option" value="A001">사업보고서</option>
                                        <option class="pblntf_detail_ty__option a_option" value="A002">반기보고서</option>
                                        <option class="pblntf_detail_ty__option a_option" value="A003">분기보고서</option>
                                        <option class="pblntf_detail_ty__option a_option" value="A004">등록법인결산서류(자본시장법이전)</option>
                                        <option class="pblntf_detail_ty__option a_option" value="A005">소액공모법인결산서류</option>

                                        <option class="pblntf_detail_ty__option b_option" value="B001">주요사항보고서</option>
                                        <option class="pblntf_detail_ty__option b_option" value="B002">주요경영사항신고(자본시장법 이전)</option>
                                        <option class="pblntf_detail_ty__option b_option" value="B003">최대주주등과의거래신고(자본시장법 이전)</option>

                                        <option class="pblntf_detail_ty__option c_option" value="C001">증권신고(지분증권)</option>
                                        <option class="pblntf_detail_ty__option c_option" value="C002">증권신고(채무증권)</option>
                                        <option class="pblntf_detail_ty__option c_option" value="C003">증권신고(파생결합증권)</option>
                                        <option class="pblntf_detail_ty__option c_option" value="C004">증권신고(합병등)</option>
                                        <option class="pblntf_detail_ty__option c_option" value="C005">증권신고(기타)</option>
                                        <option class="pblntf_detail_ty__option c_option" value="C006">소액공모(지분증권)</option>
                                        <option class="pblntf_detail_ty__option c_option" value="C007">소액공모(채무증권)</option>
                                        <option class="pblntf_detail_ty__option c_option" value="C008">소액공모(파생결합증권)</option>
                                        <option class="pblntf_detail_ty__option c_option" value="C009">소액공모(합병등)</option>
                                        <option class="pblntf_detail_ty__option c_option" value="C010">소액공모(기타)</option>
                                        <option class="pblntf_detail_ty__option c_option" value="C011">호가중개시스템을통한소액매출</option>

                                        <option class="pblntf_detail_ty__option d_option" value="D001">주식등의대량보유상황보고서</option>
                                        <option class="pblntf_detail_ty__option d_option" value="D002">임원ㆍ주요주주특정증권등소유상황보고서</option>
                                        <option class="pblntf_detail_ty__option d_option" value="D003">의결권대리행사권유</option>
                                        <option class="pblntf_detail_ty__option d_option" value="D004">공개매수</option>

                                        <option class="pblntf_detail_ty__option e_option" value="E001">자기주식취득/처분</option>
                                        <option class="pblntf_detail_ty__option e_option" value="E002">신탁계약체결/해지</option>
                                        <option class="pblntf_detail_ty__option e_option" value="E003">합병등종료보고서</option>
                                        <option class="pblntf_detail_ty__option e_option" value="E004">주식매수선택권부여에관한신고</option>
                                        <option class="pblntf_detail_ty__option e_option" value="E005">사외이사에관한신고</option>
                                        <option class="pblntf_detail_ty__option e_option" value="E006">주주총회소집공고</option>
                                        <option class="pblntf_detail_ty__option e_option" value="E007">시장조성/안정조작</option>
                                        <option class="pblntf_detail_ty__option e_option" value="E008">합병등신고서(자본시장법 이전)</option>
                                        <option class="pblntf_detail_ty__option e_option" value="E009">금융위등록/취소(자본시장법 이전)</option>

                                        <option class="pblntf_detail_ty__option f_option" value="F001">감사보고서</option>
                                        <option class="pblntf_detail_ty__option f_option" value="F002">연결감사보고서</option>
                                        <option class="pblntf_detail_ty__option f_option" value="F003">결합감사보고서</option>
                                        <option class="pblntf_detail_ty__option f_option" value="F004">회계법인사업보고서</option>
                                        <option class="pblntf_detail_ty__option f_option" value="F005">감사전재무제표미제출신고서</option>

                                        <option class="pblntf_detail_ty__option g_option" value="G001">증권신고(집합투자증권-신탁형)</option>
                                        <option class="pblntf_detail_ty__option g_option" value="G002">증권신고(집합투자증권-회사형)</option>
                                        <option class="pblntf_detail_ty__option g_option" value="G003">증권신고(집합투자증권-합병)</option>

                                        <option class="pblntf_detail_ty__option h_option" value="H001">자산유동화계획/양도등록</option>
                                        <option class="pblntf_detail_ty__option h_option" value="H002">사업/반기/분기보고서</option>
                                        <option class="pblntf_detail_ty__option h_option" value="H003">증권신고(유동화증권등)</option>
                                        <option class="pblntf_detail_ty__option h_option" value="H004">채권유동화계획/양도등록</option>
                                        <option class="pblntf_detail_ty__option h_option" value="H005">수시보고</option>
                                        <option class="pblntf_detail_ty__option h_option" value="H006">주요사항보고서</option>

                                        <option class="pblntf_detail_ty__option i_option" value="I001">수시공시</option>
                                        <option class="pblntf_detail_ty__option i_option" value="I002">공정공시</option>
                                        <option class="pblntf_detail_ty__option i_option" value="I003">시장조치/안내</option>
                                        <option class="pblntf_detail_ty__option i_option" value="I004">지분공시</option>
                                        <option class="pblntf_detail_ty__option i_option" value="I005">증권투자회사</option>
                                        <option class="pblntf_detail_ty__option i_option" value="I006">채권공시</option>

                                        <option class="pblntf_detail_ty__option j_option" value="J001">대규모내부거래관련</option>
                                        <option class="pblntf_detail_ty__option j_option" value="J002">대규모내부거래관련(구)</option>
                                        <option class="pblntf_detail_ty__option j_option" value="J004">기업집단현황공시</option>
                                        <option class="pblntf_detail_ty__option j_option" value="J005">비상장회사중요사항공시</option>
                                        <option class="pblntf_detail_ty__option j_option" value="J006">기타공정위공시</option>

                                    </select><br/>

        corp_cls(법인구분) <select id="corp_cls" name="corp_cls">
                            <option value="" selected="selected">전체</option>
                            <option value="Y">유가</option>
                            <option value="K">코스닥</option>
                            <option value="N">코넥스</option>
                            <option value="E">기타</option>
                        </select><br/>

        <!-- 페이지번호 input hidden-->
        <input type="hidden" id="page_no" name="page_no" maxlength="5">

        page_count(페이지별 건수) : <select id="page_count" name="page_count">
                                        <option value="10" selected="selected">10</option>
                                        <option value="20">20</option>
                                        <option value="30">30</option>
                                    </select><br/>

        sort(정렬) : <select id="sort" name="sort">
                        <option value="date" selected="selected">접수일자</option>
                        <option value="crp">회사명</option>
                        <option value="rpt">보고서명</option>
                     </select><br/>

        sort_mth(정렬방법) : <select id="sort_mth" name="sort_mth">
                                <option value="desc" selected="selected">내림차순</option>
                                <option value="asc">오름차순</option>
                            </select><br/>

        <input type="button" id="execute" value="제출"/>
    </form>

    <div id="paging"></div>
</div>
</body>
</html>

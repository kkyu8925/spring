$(document).ready(function() {
    // 로딩시 다트 api 가져오기
    getDartAPI();

    // dartAPI 제출 버튼 클릭 ajax 실행
    $("#execute").click(function (){
        $('#page_no').val('1');
        getDartAPI();
    });

    /* ###### pblntf_detail_ty(공시상세유형) selected 조건설정 jquery start ########*/
    $('#pblntf_ty').change(function(){
        let state = $('#pblntf_ty option:selected').val();
        $('#pblntf_detail_ty option:eq(0)').prop("selected",true);
        console.log(state);

        if(state == 'A'){
            $('.a_option').show();
        } else {
            $('.a_option').hide();
        }

        if(state == 'B'){
            $('.b_option').show();
        } else {
            $('.b_option').hide();
        }

        if(state == 'C'){
            $('.c_option').show();
        } else {
            $('.c_option').hide();
        }

        if(state == 'D'){
            $('.d_option').show();
        } else {
            $('.d_option').hide();
        }

        if(state == 'E'){
            $('.e_option').show();
        } else {
            $('.e_option').hide();
        }

        if(state == 'F'){
            $('.f_option').show();
        } else {
            $('.f_option').hide();
        }

        if(state == 'G'){
            $('.g_option').show();
        } else {
            $('.g_option').hide();
        }

        if(state == 'H'){
            $('.h_option').show();
        } else {
            $('.h_option').hide();
        }

        if(state == 'I'){
            $('.i_option').show();
        } else {
            $('.i_option').hide();
        }

        if(state == 'J'){
            $('.j_option').show();
        } else {
            $('.j_option').hide();
        }
        $('#page_no').val('1');
        getDartAPI(); // pblntf_ty(공시유형) 변경시 다시 불러오기.
    });
    /* ###### pblntf_detail_ty(공시상세유형) selected 조건설정 jquery end ########*/

    // pblntf_detail_ty(공시상세유형) 변경시 다시불러오기
    $('#pblntf_detail_ty').change(function(){
        $('#page_no').val('1');
        getDartAPI();
    });
    // corp_cls(법인구분) 변경시 다시불러오기
    $('#corp_cls').change(function(){
        $('#page_no').val('1');
        getDartAPI();
    });
    // page_count(페이지별 건수) 변경시 다시불러오기
    $('#page_count').change(function(){
        $('#page_no').val('1');
        getDartAPI();
    });
    // sort(정렬) 변경시 다시불러오기
    $('#sort').change(function(){
        $('#page_no').val('1');
        getDartAPI();
    });
    // sort_mth(정렬방법) 변경시 다시불러오기
    $('#sort_mth').change(function(){
        $('#page_no').val('1');
        getDartAPI();
    });

});

/* ###### dart API 가져오기 ajax start ######*/
function getDartAPI() {
    let resHTML = "";

    // 페이징 변수
    let total_count = ""; // 총 데이터의 수(total_count)
    let page_count = ""; // 한 페이지에 나타낼 데이터 수(page_count) : 한 페이지당 게시글 수
    let page_no = ""; // 현재 페이지
    let pageBlock = ""; // 한 화면에 나타낼 페이지 수(pageBlock) : 페이지 그룹(ex. 5 -> < 1 2 3 4 5 >)

    $.ajax({
        url : '/getDartForJSON.do',
        type: 'post',
        dataType : "JSON",
        data : $("form").serialize(),
        success : function(json) {
            let resObj = json.res; // 배열형태

            total_count = json.total_count;
            page_count = json.page_count;
            page_no = json.page_no;
            pageBlock = json.total_page;

            for(let i=0; i<resObj.length;i++) {
                resHTML += '<tr>';
                resHTML += '<td>'+resObj[i].corp_code+'</td>'; // 공시대상회사의 고유번호(8자리)
                resHTML += '<td>'+resObj[i].corp_cls+'</td>'; // 법인구분 : Y(유가), K(코스닥), N(코넥스), E(기타)
                resHTML += '<td>'+resObj[i].stock_code+'</td>'; // 상장회사의 종목코드(6자리)
                resHTML += '<td>'+resObj[i].corp_name+'</td>'; // 공시대상회사의 종목명(상장사) 또는 법인명(기타법인)
                resHTML += '<td>'+resObj[i].report_nm+'</td>'; // 공시구분+보고서명+기타정보
                resHTML += "<td><a href=http://m.dart.fss.or.kr/html_mdart/MD1007.html?rcpNo="+resObj[i].rcept_no+">"+resObj[i].rcept_no+"</a></td>";  // 접수번호(14자리)
                resHTML += '<td>'+resObj[i].flr_nm+'</td>'; // 공시 제출인명
                resHTML += '<td>'+resObj[i].rcept_dt+'</td>'; // 공시 접수일자(YYYYMMDD)
                resHTML += '<td>'+resObj[i].rm+'</td>'; // 비고 - 각각문자의 의미가 있음 https://opendart.fss.or.kr/guide/detail.do?apiGrpCd=DS001&apiId=2019001
                resHTML += '</tr>';
            }
            $('#dartRes').html(resHTML);
            paging(total_count, page_count, pageBlock, page_no);

        }, error: function(json){
            console.log(json);
            resHTML += '<td colspan="9">조회된 데이터가 없습니다.</td>';
            $('#dartRes').html(resHTML);
        }
    })
}
/* ###### dart API 가져오기 ajax end ######*/

/* ############ paging start ############*/
function paging(total_count, page_count, pageBlock, page_no){

    if(pageBlock >= 10){
        pageBlock = 10;
    }
    let totalPage = Math.ceil(total_count/page_count);    // 총 페이지 수
    let pageGroup = Math.ceil(page_no/pageBlock);    // 페이지 그룹

    let last = pageGroup * pageBlock;    // 화면에 보여질 마지막 페이지 번호
    if(last > totalPage)
        last = totalPage;
    let first = last - (pageBlock-1);    // 화면에 보여질 첫번째 페이지 번호
    let next = last+1;
    let prev = first-1;

    let $pingingView = $("#paging");

    let html = "";

    if(prev > 0)
        html += "<a href=# id='prev'><</a> ";

    for(let i=first; i <= last; i++){
        html += "<a href='#' id=" + i + ">" + i + "</a> ";
    }

    if(last < totalPage)
        html += "<a href=# id='next'>></a>";

    $("#paging").html(html);    // 페이지 목록 생성
    $("#paging a").css("color", "black");
    $("#paging a#" + page_no).css({"text-decoration":"none",
        "color":"blue",
        "font-weight":"bold"});    // 현재 페이지 표시

    $("#paging a").click(function(){

        let $item = $(this);
        let $id = $item.attr("id");
        let selectedPage = $item.text();

        if($id == "next")    selectedPage = next;
        if($id == "prev")    selectedPage = prev;

        paging(total_count, page_count, pageBlock, selectedPage);
        $('#page_no').val(selectedPage);
        getDartAPI();
    });

}
/* ############## paging end ##############*/

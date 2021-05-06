package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import poly.dto.DartDTO;
import poly.service.IDartService;
import poly.util.CmmUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class DartController {

    private Logger log = Logger.getLogger(this.getClass());

    @Resource(name="DartService")
    private IDartService dartService;

    /**
     * 다트 api 호출 화면 이동
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value="dartPage")
    public String dartPage() throws Exception {
        log.info(this.getClass().getName()+".dartPage start");
        log.info(this.getClass().getName()+".dartPage end");
        return "/siri/dartPage";
    }

    /**
     * 다트 api ajax for json
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="getDartForJSON")
    @ResponseBody
    public Map<String,Object> getDartForJSON(HttpServletRequest request) throws Exception {

        // 시작 로그
        log.info(this.getClass().getName() + ".getDartForJSON start");

        // api url
        String url = "https://opendart.fss.or.kr/api/list.json?crtfc_key=5e3bdd22a57af33f29317959a5e502f20bb91852";

        // api 요청 피라미터 https://opendart.fss.or.kr/guide/detail.do?apiGrpCd=DS001&apiId=2019001
        String corp_code = CmmUtil.nvl(request.getParameter("corp_code")); // 공시대상회사의 고유번호(8자리)
        String bgn_de = CmmUtil.nvl(request.getParameter("bgn_de")); // 검색시작 접수일자(YYYYMMDD) : 없으면 종료일(end_de) 고유번호(corp_code)가 없는 경우 검색기간은 3개월로 제한
        String end_de = CmmUtil.nvl(request.getParameter("end_de")); // 검색종료 접수일자(YYYYMMDD) : 없으면 당일
        String pblntf_ty = CmmUtil.nvl(request.getParameter("pblntf_ty")); // 공시유형
        String pblntf_detail_ty = CmmUtil.nvl(request.getParameter("pblntf_detail_ty")); // 공시상세유형
        String corp_cls = CmmUtil.nvl(request.getParameter("corp_cls")); // 법인구분
        String page_no = CmmUtil.nvl(request.getParameter("page_no")); // 페이지 번호(default = 1)
        String page_count = CmmUtil.nvl(request.getParameter("page_count")); // 페이지당 건수(1~100) 기본값 : 10, 최대값 : 100
        String sort = CmmUtil.nvl(request.getParameter("sort")); // 정렬 - 접수일자(default): date, 회사명 : crp, 보고서명 : rpt
        String sort_mth = CmmUtil.nvl(request.getParameter("sort_mth")); // 정렬 방법 - asc,desc(default)

        // 파리미터 값 추가
        if (corp_code.length() > 0) {
            url += "&corp_code=" + corp_code;
        }
        if (bgn_de.length() > 0) {
            url += "&bgn_de=" + bgn_de;
        }
        if (end_de.length() > 0) {
            url += "&end_de=" + end_de;
        }
        if (pblntf_ty.length() > 0 && pblntf_detail_ty.length() == 0) {
            url += "&pblntf_ty=" + pblntf_ty;
        }
        if (pblntf_detail_ty.length() > 0) {
            url += "&pblntf_detail_ty=" + pblntf_detail_ty;
        }
        if (corp_cls.length() > 0) {
            url += "&corp_cls=" + corp_cls;
        }
        if (page_no.length() > 0) {
            url += "&page_no=" + page_no;
        }
        if (page_count.length() > 0) {
            url += "&page_count=" + page_count;
        }
        if (sort.length() > 0) {
            url += "&sort=" + sort;
        }
        if (sort_mth.length() > 0) {
            url += "&sort_mth=" + sort_mth;
        }

        log.info(this.getClass().getName() + ".request url : " + url);

        // API 호출을 위한 피라미터 저장
        DartDTO pDTO = new DartDTO();
        pDTO.setUrl(url);

        Map<String, Object> rMap = dartService.getDartforJSON(pDTO);

        if (rMap == null) {
            rMap = new HashMap<String, Object>();
        }

        // 종료 로그
        log.info(this.getClass().getName() + ".getDartForJSON end");

        return rMap;
    }

}

package poly.service.impl;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import poly.dto.DartDTO;
import poly.service.IDartService;
import poly.util.CmmUtil;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("DartService")
public class DartService implements IDartService {

    private Logger log = Logger.getLogger(this.getClass());

    /**
     * naver api url 요청받는 함수
     *
     * @param requestUrl
     * @return
     * @throws Exception
     */
    private String getUrlForJSON(String requestUrl) throws Exception {
        
        // 시작 로그
        log.info(this.getClass().getName() + ".getUrlForJSON start");
        
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;

        // json 결과값이 저장되는 변수
        String json = "";

        // SSL 적용된 사이트일 경우, 데이터 증명을 위해 사용
        HostnameVerifier allHostsValid = new HostnameVerifier() {

            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

        try {

            // 웹사이트 접속을 위한 URL 파싱
            URL url = new URL(requestUrl);

            // 접속
            urlConn = url.openConnection();

            // 접속하면, 응답을 60초(60*1000ms)동안 기다림
            if (urlConn != null) {
                urlConn.setReadTimeout(60 * 1000);
            }

            if (urlConn != null && urlConn.getInputStream() != null) {
                in = new InputStreamReader(urlConn.getInputStream(), Charset.forName("UTF-8"));

                BufferedReader bufferedReader = new BufferedReader(in);

                // 주어진 문자 입력 스트림 unoutStream에 대해 기본 크기의 버퍼를 갖는 객체를 생성.
                if (bufferedReader != null) {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) {
                        sb.append((char) cp);
                    }
                    bufferedReader.close();
                }
            }
            in.close();
        } catch (Exception e) {
            throw new RuntimeException("Exception URL : " + requestUrl, e);
        }

        json = sb.toString(); // json 결과 저장

        log.info("JSON result : " + json);
        
        // 종료 로그
        log.info(this.getClass().getName() + ".getUrlForJSON END!");

        return json;
    }

    /**
     * dart api json으로 가져오기
     *
     * @param pDTO DartAPI 정보가져오기 위한 파라미터
     * @return json 결과를 Map형태로 반환결과
     * @throws Exception
     */
    public Map<String, Object> getDartforJSON(DartDTO pDTO) throws Exception {

        // 시작 로그
        log.info(this.getClass().getName() + ".getDartforJSON Start");

        // JSON 읽은 값을 Controller에 전달하기 위한 결과 변수
        Map<String, Object> rMap = new HashMap<String, Object>();

        // JSON 결과 받아오기
        String json = getUrlForJSON(CmmUtil.nvl(pDTO.getUrl()));

        // String 변수의 문자열을 json 형태의 데이터 구조로 변경하기 위한 객체를 메모리에 올림
        JSONParser parser = new JSONParser();

        // String 변수의 문자열을 json 형태의 데이터 구조롤 변경하기 위해 object 변환
        Object obj = parser.parse(json);

        // 변환된 Object 객체를 json 데이터 구조로 변경
        JSONObject jsonObject = (JSONObject) obj;

        // json에 저장된 배열형태 데이터
        JSONArray resArr = (JSONArray) jsonObject.get("list");

        // json 배열에 저장된 데이터를 List<AccStatDTO> 구조로 변경하기 위해 메모리에 올림
        List<DartDTO> rList = new ArrayList<DartDTO>();

        // 걱 레코드마다 DTO로 저장
        DartDTO rDTO = null;

        log.info("resArr.size :"+resArr.size());
        for (int i = 0; i < resArr.size(); i++) {
            JSONObject result = (JSONObject) resArr.get(i);

            rDTO = new DartDTO(); // 데이터 저장을 위해 메모리에 올림

            log.info("corp_code : "+CmmUtil.nvl((String) result.get("corp_code"))); // 공시대상회사의 고유번호(8자리)
            log.info("corp_cls : "+CmmUtil.nvl((String) result.get("corp_cls"))); // 법인구분 : Y(유가), K(코스닥), N(코넥스), E(기타)
            log.info("stock_code : "+CmmUtil.nvl((String) result.get("stock_code"))); // 상장회사의 종목코드(6자리)
            log.info("corp_name : "+CmmUtil.nvl((String) result.get("corp_name"))); // 공시대상회사의 종목명(상장사) 또는 법인명(기타법인)
            log.info("report_nm : "+CmmUtil.nvl((String) result.get("report_nm"))); // 보고서명 - 공시구분+보고서명+기타정보
            log.info("rcept_no : "+CmmUtil.nvl((String) result.get("rcept_no"))); // 접수번호(14자리) 뷰여 - 모바일용 : http://m.dart.fss.or.kr/html_mdart/MD1007.html?rcpNo=접수번호
            log.info("flr_nm : "+CmmUtil.nvl((String) result.get("flr_nm"))); // 공시 제출인명
            log.info("rcept_dt : "+CmmUtil.nvl((String) result.get("rcept_dt"))); // 공시 접수일자(YYYYMMDD)
            log.info("rm : "+CmmUtil.nvl((String) result.get("rm"))); // 비고 - 각각문자의 의미가 있음 https://opendart.fss.or.kr/guide/detail.do?apiGrpCd=DS001&apiId=2019001

            rDTO.setCorp_code(CmmUtil.nvl((String) result.get("corp_code"))); // 공시대상회사의 고유번호(8자리)
            rDTO.setCorp_cls(CmmUtil.nvl((String) result.get("corp_cls"))); // 법인구분 : Y(유가), K(코스닥), N(코넥스), E(기타)
            rDTO.setStock_code(CmmUtil.nvl((String) result.get("stock_code"))); // 상장회사의 종목코드(6자리)
            rDTO.setCorp_name(CmmUtil.nvl((String) result.get("corp_name"))); // 공시대상회사의 종목명(상장사) 또는 법인명(기타법인)
            rDTO.setReport_nm(CmmUtil.nvl((String) result.get("report_nm"))); // 보고서명 - 공시구분+보고서명+기타정보
            rDTO.setRcept_no(CmmUtil.nvl((String) result.get("rcept_no"))); // 접수번호(14자리) 뷰여 - 모바일용 : http://m.dart.fss.or.kr/html_mdart/MD1007.html?rcpNo=접수번호
            rDTO.setFlr_nm(CmmUtil.nvl((String) result.get("flr_nm"))); // 공시 제출인명
            rDTO.setRcept_dt(CmmUtil.nvl((String) result.get("rcept_dt"))); // 공시 접수일자(YYYYMMDD)
            rDTO.setRm(CmmUtil.nvl((String) result.get("rm"))); // 비고 - 각각문자의 의미가 있음 https://opendart.fss.or.kr/guide/detail.do?apiGrpCd=DS001&apiId=2019001

            // 저장된 DTO를 list에 저장
            rList.add(rDTO);
        }
        
        // 페이징 피라미터
        log.info("total_count : "+ jsonObject.get("total_count"));
        log.info("total_page : "+ jsonObject.get("total_page"));
        log.info("page_count : "+ jsonObject.get("page_count"));
        log.info("page_no : "+ jsonObject.get("page_no"));
        
        // controller에 저장할 데이터 저장
        rMap.put("res", rList);
        rMap.put("total_count", jsonObject.get("total_count"));
        rMap.put("total_page", jsonObject.get("total_page"));
        rMap.put("page_count", jsonObject.get("page_count"));
        rMap.put("page_no", jsonObject.get("page_no"));

        // 종료 로그
        log.info(this.getClass().getName() + ".getAccStatForJSON end");

        return rMap;
    }
}

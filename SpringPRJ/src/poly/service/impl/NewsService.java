package poly.service.impl;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import poly.dto.NewsDTO;
import poly.service.INewsService;
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

@Service("NewsService")
public class NewsService implements INewsService {

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

        String clientId = "sTX4YSK_I7lwwj4agAaA";
        String clientSecret = "hiYCDCBHxc";

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
                urlConn.setRequestProperty("X-Naver-Client-Id", clientId);
                urlConn.setRequestProperty("X-Naver-Client-Secret", clientSecret);
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
     * naver news api 결과 json으로 가져오기
     *
     * @param pDTO 
     * @return 
     * @throws Exception
     */
    public Map<String, Object> getNaverNews(NewsDTO pDTO) throws Exception {

        // 시작 로그
        log.info(this.getClass().getName() + ".getNaverNews Start");

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
        JSONArray resArr = (JSONArray) jsonObject.get("items");

        // json 배열에 저장된 데이터를 List<AccStatDTO> 구조로 변경하기 위해 메모리에 올림
        List<NewsDTO> rList = new ArrayList<>();

        // 걱 레코드마다 DTO로 저장
        NewsDTO rDTO = null;

        log.info("resArr.size :"+resArr.size());
        for (int i = 0; i < resArr.size(); i++) {
            JSONObject result = (JSONObject) resArr.get(i);

            rDTO = new NewsDTO(); // 데이터 저장을 위해 메모리에 올림

            log.info("title : "+CmmUtil.nvl((String) result.get("title"))); // 제목
            log.info("originallink : "+CmmUtil.nvl((String) result.get("originallink"))); // 검색 결과 문서의 제공 언론사 하이퍼텍스트 link
            log.info("link : "+CmmUtil.nvl((String) result.get("link"))); // 검색 결과 문서의 제공 네이버 하이퍼텍스트 link
            log.info("description : "+CmmUtil.nvl((String) result.get("description"))); // 검색 결과 문서의 내용을 요약한 패시지 정보
            log.info("pubDate : "+CmmUtil.nvl((String) result.get("pubDate"))); // 검색 결과 문서가 네이버에 제공된 시간

            rDTO.setTitle(CmmUtil.nvl((String) result.get("title"))); // 제목
            rDTO.setOriginallink(CmmUtil.nvl((String) result.get("originallink"))); // 검색 결과 문서의 제공 언론사 하이퍼텍스트 link
            rDTO.setLink(CmmUtil.nvl((String) result.get("link"))); // 검색 결과 문서의 제공 네이버 하이퍼텍스트 link
            rDTO.setDescription(CmmUtil.nvl((String) result.get("description"))); // 검색 결과 문서의 내용을 요약한 패시지 정보
            rDTO.setPubDate(CmmUtil.nvl((String) result.get("pubDate"))); // 검색 결과 문서가 네이버에 제공된 시간

            // 저장된 DTO를 list에 저장
            rList.add(rDTO);
        }

        // controller에 저장할 데이터 저장
        rMap.put("res", rList);

        // 종료 로그
        log.info(this.getClass().getName() + ".getNaverNews end");

        return rMap;
    }
}

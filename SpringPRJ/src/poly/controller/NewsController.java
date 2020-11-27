package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import poly.dto.NewsDTO;
import poly.service.INewsService;
import poly.util.CmmUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


@Controller
public class NewsController {

    private Logger log = Logger.getLogger(this.getClass());

    @Resource(name="NewsService")
    private INewsService newsService;

    /**
     * 뉴스 검색 페이지 이동
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value="news")
    public String news() throws Exception{
        log.info(this.getClass().getName()+".news start");
        log.info(this.getClass().getName()+".news end");
        return "/siri/news";
    }

    /**
     * 네이버 뉴스 검색 api ajax for Json
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="getSearchNews")
    @ResponseBody
    public Map<String,Object> getSearchNews(HttpServletRequest request, HttpSession session) throws Exception {
        
        // 시작 로그
        log.info(this.getClass().getName() + ".getSearchNews start");

        // api url
        String url = "https://openapi.naver.com/v1/search/news.json?1=1";

        // api 요청 피라미터  https://developers.naver.com/docs/search/news/
        String query = CmmUtil.nvl(request.getParameter("query")); // 검색 결과 출력 건수 지정
        String display = CmmUtil.nvl(request.getParameter("display")); // 검색수

        // 피라미터 값 추가
        if(query.length() > 0){
            String text = URLEncoder.encode(query,"UTF-8");
            url += "&query=" + text;
            session.setAttribute("SS_QUERY",query);
        }
        if(display.length() > 0){
            url += "&display=" + display;
        }

        log.info(this.getClass().getName() + ".request url : "+ url);

        // api 호출을 위한 url 저장
        NewsDTO pDTO = new NewsDTO();
        pDTO.setUrl(url);
        
        Map<String,Object> rMap = newsService.getNaverNews(pDTO);

        if(rMap == null) {
            rMap = new HashMap<String, Object>();
        }

        // 종료 로그
        log.info(this.getClass().getName() + ".getSearchNews end");

        return rMap;
    }

}

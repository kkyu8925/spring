package poly.service;

import poly.dto.NewsDTO;

import java.util.Map;

public interface INewsService {

    /**
     * naver news api 결과 json으로 가져오기
     *
     * @param pDTO
     * @return
     * @throws Exception
     */
    Map<String,Object> getNaverNews(NewsDTO pDTO) throws Exception;
}

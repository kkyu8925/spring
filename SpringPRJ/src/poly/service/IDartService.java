package poly.service;

import poly.dto.DartDTO;

import java.util.Map;

public interface IDartService {

    /**
     * Dart API로 JSON 데이터 가져오기
     *
     * @param pDTO
     * @return json
     * @throws Exception
     */
    Map<String, Object> getDartforJSON(DartDTO pDTO) throws Exception;
}

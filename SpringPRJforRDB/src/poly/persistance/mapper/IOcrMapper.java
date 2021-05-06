package poly.persistance.mapper;

import config.Mapper;
import poly.dto.OcrDTO;

@Mapper("OcrMapper") // Service에서 mapper를 찾을 수 있도록
public interface IOcrMapper {

	// 등록
	void insertOcrInfo(OcrDTO pDTO) throws Exception;
}

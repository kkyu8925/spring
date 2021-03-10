package poly.service;

import java.util.List;

import poly.dto.NoticeDTO;

//Controller는 인터페이스에 있는 함수 명을 기반으로 실행 됨
public interface INoticeService {

	List<NoticeDTO> getNoticeList() throws Exception;

	void insertNoticeInfo(NoticeDTO pDTO) throws Exception;

	NoticeDTO getNoticeInfo(NoticeDTO pDTO) throws Exception;

	void updateNoticeReadCnt(NoticeDTO pDTO) throws Exception;

	void updateNoticeInfo(NoticeDTO pDTO) throws Exception;

	void deleteNoticeInfo(NoticeDTO pDTO) throws Exception;

}

package poly.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.NoticeDTO;
import poly.persistance.mapper.INoticeMapper;
import poly.service.INoticeService;

@Service("NoticeService") // Controller에서 Service를 찾을 수 있는 이름.
public class NoticeService implements INoticeService {

	// Mapper 호출을 위한 객체 생성
	@Resource(name = "NoticeMapper")
	private INoticeMapper noticeMapper;

	@Override
	public List<NoticeDTO> getNoticeList() throws Exception {
		return noticeMapper.getNoticeList();

	}

	@Override
	public void insertNoticeInfo(NoticeDTO pDTO) throws Exception {
		noticeMapper.insertNoticeInfo(pDTO);

	}

	@Override
	public NoticeDTO getNoticeInfo(NoticeDTO pDTO) throws Exception {
		return noticeMapper.getNoticeInfo(pDTO);

	}

	@Override
	public void updateNoticeReadCnt(NoticeDTO pDTO) throws Exception {
		noticeMapper.updateNoticeReadCnt(pDTO);

	}

	@Override
	public void updateNoticeInfo(NoticeDTO pDTO) throws Exception {
		noticeMapper.updateNoticeInfo(pDTO);

	}

	@Override
	public void deleteNoticeInfo(NoticeDTO pDTO) throws Exception {
		noticeMapper.deleteNoticeInfo(pDTO);

	}
}

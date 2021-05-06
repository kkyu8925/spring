package poly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import poly.dto.MailDTO;
import poly.dto.UserInfoDTO;
import poly.persistance.mapper.IUserInfoMapper;
import poly.service.IMailService;
import poly.service.IUserInfoService;
import poly.util.CmmUtil;
import poly.util.DateUtil;
import poly.util.EncryptUtil;

@Service("UserInfoService")
public class UserInfoService implements IUserInfoService {

	@Resource(name = "UserInfoMapper")
	private IUserInfoMapper userInfoMapper;

	// 메일 발송을 위한 MailService 자바객체 가져오기
	@Resource(name = "MailService")
	private IMailService mailService;

	@Override
	public int insertUserInfo(UserInfoDTO pDTO) throws Exception {

		// 회원가입 성공 :1, 아이디 중복으로인한 실패:2, 기타에러:0
		int res = 0;

		// controller에서 같이 정상적으로 못 넘어오는 경우 대비
		if (pDTO == null) {
			pDTO = new UserInfoDTO();
		}

		// 회원 가입 중복 방지를 위해 DB에서 테이터 조회
		UserInfoDTO rDTO = userInfoMapper.getUserExists(pDTO);

		// mapper에서 같이 정상적으로 못 넘어오는 경우 대비
		if (rDTO == null) {
			rDTO = new UserInfoDTO();
		}

		// 중복된 회원정보가 있는 경우, 결과값을 2로 변경, 더이상 작업 진행안함
		if (CmmUtil.nvl(rDTO.getExists_yn()).equals("Y")) {
			res = 2;

		} else { // 중복이 아니면 계속 진행

			// 회원 가입
			int success = userInfoMapper.insertUserInfo(pDTO);

			// db에 데이터가 등록
			if (success > 0) {

				res = 1; // 1:성공

				/*
				 * ###################################################
				 * 
				 * 메일 발송 로직 추가 시작
				 * 
				 * ###################################################
				 */

				MailDTO mDTO = new MailDTO();

				// 회원가입화면에서 입력받은 이메일 변수(암호화되어 넘어오기 떄문에 복호화 수행)
				mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(pDTO.getEmail())));

				// 제목
				mDTO.setTitle("회원가입을 축하합니다.");
				// 내용 가입자 이름넣어 발송
				mDTO.setContents(CmmUtil.nvl(pDTO.getUser_name()) + "님의 회원가입을 진심으로 축하드립니다.");

				// 회원 가입이 성공했기 때문에 발송
				mailService.doSendMail(mDTO);

				/*
				 * ###################################################
				 * 
				 * 메일 발송 로직 추가 끝
				 * 
				 * ###################################################
				 */
			} else {
				res = 0;
			}
		}
		return res;
	}

	/**
	 * 로그인을 위해 아이디,비밀번호 일치확인
	 * 
	 * @param UserInfoDTO 로그인을 위해 아이디,비밀번호
	 * @return UserInfoDTO 로그인된 회원아이디 정보
	 */
	@Override
	public int getUserLoginCheck(UserInfoDTO pDTO) throws Exception {

		// 로그인 성공 : 1, 실패 : 0
		int res = 0;

		// 로그인을 위해 아이디,비밀번호가 일치 확인을 위한 mapper 호출
		UserInfoDTO rDTO = userInfoMapper.getUserLoginCheck(pDTO);

		if (rDTO == null) {
			rDTO = new UserInfoDTO();
		}

		/*
		 * ##############################################
		 * 
		 * 로그인 성공 여부 체크 시작
		 * 
		 * ##############################################
		 */

		/*
		 * userInfoMapper로 부터 SELECT 쿼리의 결과로 회원아이디 받아왔다면, 로그인 성공!
		 * 
		 * DTO의 변수에 값이 있는지 확인하기 처리속도 측면에서 가장 좋은 방법은 변수의 길이를 가져오는 것이다. 따라서, .length() 함수를
		 * 통해 회원아이와 글자수를 가져와 0보다 큰지 비교한다. 0보다 크다면, 글자가 존재하는 것이기 때문에 값이 존재한다.
		 */
		if (CmmUtil.nvl(rDTO.getUser_id()).length() > 0) {
			res = 1;
			/*
			 * ###################################################
			 * 
			 * 메일 발송 로직 추가 시작
			 * 
			 * ###################################################
			 */

			MailDTO mDTO = new MailDTO();

			// 아이디, 패스워드 일치하는 지 체크하는 쿼리에서 이메일 값 받아오기(아직 암호화되어 넘어오기 때문에 복호화수행
			mDTO.setToMail(EncryptUtil.decAES128CBC(CmmUtil.nvl(rDTO.getEmail())));

			// 제목
			mDTO.setTitle("로그인 알림!");
			// 날짜 내용 가입자 이름넣어 발송
			mDTO.setContents(DateUtil.getDateTime("yyyy-mm-dd 24h:mm:ss") + "에 " + CmmUtil.nvl(rDTO.getUser_name())
					+ "님이 로그인하였습니다.");

			// 회원 가입이 성공했기 때문에 발송
			mailService.doSendMail(mDTO);

			/*
			 * ###################################################
			 * 
			 * 메일 발송 로직 추가 끝
			 * 
			 * ###################################################
			 */
		}
		/*
		 * ##############################################
		 * 
		 * 로그인 성공 여부 체크 끝!
		 * 
		 * ##############################################
		 */
		return res;
	}

}

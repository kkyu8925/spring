package poly.service;

import poly.dto.UserInfoDTO;

public interface IUserInfoService {

	// 회원 가입하기(회원정보 등록)
	int insertUserInfo(UserInfoDTO pDTO) throws Exception;

	// 로그인을 위해 아이디,비밀번호 일치 확인
	int getUserLoginCheck(UserInfoDTO pDTO) throws Exception;
}

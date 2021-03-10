package poly.persistance.mapper;

import config.Mapper;
import poly.dto.UserInfoDTO;

@Mapper("UserInfoMapper")
public interface IUserInfoMapper {

	// 회원 가입하기(회원정보 등록)
	int insertUserInfo(UserInfoDTO pDTO) throws Exception;

	// 회원 가입 전 중복 체크 하기(DB조회)
	UserInfoDTO getUserExists(UserInfoDTO pDTO) throws Exception;

	// 로그인을 위해 아이디와 비밀번호 일치확인
	UserInfoDTO getUserLoginCheck(UserInfoDTO pDTO) throws Exception;
}

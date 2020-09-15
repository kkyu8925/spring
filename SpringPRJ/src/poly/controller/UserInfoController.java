package poly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.dto.UserInfoDTO;
import poly.service.IUserInfoService;
import poly.util.CmmUtil;
import poly.util.EncryptUtil;

/*
 * Controller 선언해야만 Spring 프레임워크에서 Controller인지 인식 가능
 * 자바 서블릿 역할 수행
 * */
@Controller
public class UserInfoController {

	// 로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(this.getClass());

	/*
	 * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨) static 선언 방식 기반의 싱글톤패턴)
	 */
	@Resource(name = "UserInfoService")
	private IUserInfoService userInfoService;

	/**
	 * 회원 가입 화면으로 이동
	 */
	@RequestMapping(value = "user/userRegForm")
	public String userRegForm() {
		log.info(this.getClass().getName() + ".user/userRegForm start");
		log.info(this.getClass().getName() + ".user/userRegForm end");

		return "/user/UserRegForm";
	}

	/**
	 * 회원가입 로직 처리
	 */
	@RequestMapping(value = "user/insertUserInfo", method = RequestMethod.POST)
	public String insertUserInfo(HttpServletRequest request, HttpServletResponse respose, ModelMap model)
			throws Exception {

		log.info(this.getClass().getName() + ".insertUserInfo start");

		// 회원가입 결과에 대한 메세지를 전달할 변수
		String msg = "";

		// 웹(회원정보 입력화면)에서 받는 정보를 저장할 변수
		UserInfoDTO pDTO = null;

		try {
			/*
			 * ####################################################
			 * 
			 * 웹(회원정보 입력화면)에서 받는 정보를 String변수에 저장시작!
			 * 
			 * 무조적 웹으로 받는 정보는 DTO에 저장하기 위해 String 변수에 저장
			 * 
			 * #####################################################
			 */
			String user_id = CmmUtil.nvl(request.getParameter("user_id")); // 아이디
			String user_name = CmmUtil.nvl(request.getParameter("user_name")); // 이름
			String password = CmmUtil.nvl(request.getParameter("password")); // 비밀번호
			String email = CmmUtil.nvl(request.getParameter("email")); // 이메일
			String addr1 = CmmUtil.nvl(request.getParameter("addr1")); // 주소
			String addr2 = CmmUtil.nvl(request.getParameter("addr2")); // 상세주소
			/*
			 * ####################################################
			 * 
			 * 웹(회원정보 입력화면)에서 받는 정보를 String변수에 저장끝!
			 * 
			 * 무조적 웹으로 받는 정보는 DTO에 저장하기 위해 String 변수에 저장
			 * 
			 * #####################################################
			 */

			/*
			 * ####################################################
			 * 
			 * 반드시, 받은 값을 확인!
			 * 
			 * #####################################################
			 */
			log.info("user_id : " + user_id);
			log.info("user_name : " + user_name);
			log.info("password : " + password);
			log.info("email : " + email);
			log.info("addr1 : " + addr1);
			log.info("addr2 : " + addr2);

			/*
			 * ####################################################
			 * 
			 * 웹(회원정보 입력화면)에서 받는 정보를DTO 저장시작
			 * 
			 * #####################################################
			 */

			// 웹(회원정보 입력화면)에서 받는 정보를 저장할 변수를 메모리에 올리기
			pDTO = new UserInfoDTO();
			pDTO.setUser_id(user_id);
			pDTO.setUser_name(user_name);

			// 비밀번호는 절대로 복호화되지 않도록 해시 알고리즘으로 암호화
			pDTO.setPassword(EncryptUtil.encHashSHA256(password));

			// 민감 정보인 이메일은 AES128-CBC로 암호화함
			pDTO.setEmail(EncryptUtil.encAES128CBC(email));

			pDTO.setAddr1(addr1);
			pDTO.setAddr2(addr2);
			/*
			 * ####################################################
			 * 
			 * 웹(회원정보 입력화면)에서 받는 정보를 DTO 저장끝
			 * 
			 * #####################################################
			 */

			/*
			 * 회원가입
			 */
			int res = userInfoService.insertUserInfo(pDTO);

			if (res == 1) {
				msg = "회원가입되었습니다.";
			} else if (res == 2) {
				msg = "이미 가입된 이메일 주소입니다.";
			} else {
				msg = "오류로 인해 회원가입이 실패하였습니다.";
			}

		} catch (Exception e) {
			// 예외로 실패시
			msg = "실패하였습니다" + e.toString();
			log.info(e.toString());
			e.printStackTrace();

		} finally {

			log.info(this.getClass().getName() + ".insertUserInfo end!");

			// 결과 메세지 전달하기
			model.addAttribute("msg", msg);

			// 웹(회원정보 입력화면)으로부터 입력받은 데이터 전달하기
			model.addAttribute("pDTO", pDTO);

			pDTO = null;
		}

		return "/user/Msg";

	}
}
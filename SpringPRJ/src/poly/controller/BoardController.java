package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import poly.dto.BoardDTO;
import poly.service.IBoardService;
import static poly.util.CmmUtil.nvl;

/*
 * Controller 선언해야만 Spring 프레임워크에서 Controller인지 인식 가능
 * 자바 서블릿 역할 수행
 * */
@Controller
public class BoardController {

	// 로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(this.getClass());

	/*
	 * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨) static 선언 방식 기반의 싱글톤패턴
	 */
	@Resource(name = "BoardService") // Service 자바 파일중 BoardService 찾아서 연결
	private IBoardService boardService; // Service 호출을 위한 객체 생성

	/*
	 * 함수명 위의 value="board/boardList.do" => /notice/NoticeList.do로 호출되는 url은 무조건 이
	 * 함수가 실행된다. method=RequestMethod.GET => 폼 전송방법을 지정하는 것으로 get방식은 GET, post방식은
	 * POST이다. method => 기입안하면 GET, POST 모두 가능하나, 가급적 적어주는 것이 좋다.
	 */
	@RequestMapping(value = "board/boardList.do", method = RequestMethod.GET)
	public String boardList(ModelMap model) throws Exception {

		// 시작 로그
		log.info(this.getClass().getName() + ".boardList start");

		// 리스트 가져오기 + 로그찍기 + null값 처리
		List<BoardDTO> rList = boardService.getBoardList();
		log.info(rList); // 확인 로그

		if (rList == null) {
			rList = new ArrayList<BoardDTO>();
		}

		// 결과 데이터 넣어주기 jsp 파일로 전송
		model.addAttribute("rList", rList);

		// 변수 초기화(메모리 효율화 시키기 위해 사용함)
		rList = null;

		// 종료 로그
		log.info(this.getClass().getName() + ".boardList end!");

		// 함수 처리가 끝나고 보여줄 JSP 파일명(/WEB-INF/view/board/boardList.jsp)
		return "/board/boardList";
	}

	/**
	 * 새글 작성 페이지
	 */
	@RequestMapping(value = "/board/newPost.do", method = RequestMethod.GET)
	public String newPost() {

		// 시작로그
		log.info(this.getClass().getName() + ".newPost start!");

		// 종료 로그
		log.info(this.getClass().getName() + ".newPost end!");

		return "/board/newPost";
	}

	/**
	 * 새글 등록
	 */
	@RequestMapping(value = "/board/doPost.do", method = RequestMethod.POST)
	public String doPost(HttpServletRequest request, ModelMap model) throws Exception {

		// 시작 로그
		log.info(this.getClass().getName() + ".doPost start!");

		// 임시로 설정하는 아이디
		String id = "admin";

		// 요청으로부터 받은 파라미터를 변수에 저장
		String post_title = nvl(request.getParameter("post_title").replaceAll("(?!)<script", "&It;script"));
		String post_content = nvl(request.getParameter("post_content").replaceAll("(?!)<script", "&It;script"));
		log.info("post_title : " + post_title); // 확인 로그
		log.info("post_content : " + post_content);// 확인 로그

		// 게시자, 게시글 제목, 게시글 내용을 담아 서비스에 전송할 DTO객체 생성
		BoardDTO pDTO = new BoardDTO();

		pDTO.setReg_id(id);
		pDTO.setPost_title(post_title);
		pDTO.setPost_content(post_content);

		// 게시글 등록하기 위한 비즈니스 로직 호출
		int res = boardService.insertPost(pDTO);

		String msg;
		String url = "/board/boardList.do";

		// 변수 초기화(메모리 효율화 시키기 위해 사용함)
		pDTO = null;

		// 게시글 등록 성공할 경우 res에 0보다 큰 수가 저장됨
		if (res > 0) {
			msg = "등록에 성공했습니다.";
		} else { // res가 0일 경우 등록에 실패한 것
			msg = "등록에 실패했습니다.";
		}

		// 결과 메세지 전달하기 jsp 파일로 전송
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		// 종료 로그
		log.info(this.getClass().getName() + ".doPost end!");

		return "/redirect";

	}

	/**
	 * 글 상세보기
	 */
	@RequestMapping(value = "/board/boardDetail.do", method = RequestMethod.GET)
	public String boardDetail(HttpServletRequest request, ModelMap model) throws Exception {

		// 시작 로그
		log.info(this.getClass().getName() + ".boardDet start!");

		// 요청으로부터 받은 파라미터를 변수에 저장
		String post_no = nvl(request.getParameter("no").replaceAll("(?!)<script", "&It;script")); // 글 번호(PK)
		log.info(post_no); // 확인 로그

		// 게시글 번호을 담아 서비스에 전송할 DTO객체 생성
		BoardDTO pDTO = new BoardDTO();
		pDTO.setPost_no(post_no);

		// 게시글 상세정보 가져오기
		BoardDTO rDTO = boardService.getBoardDetail(pDTO);

		if (rDTO == null) {
			model.addAttribute("msg", "존재하지않는 게시물입니다.");
			model.addAttribute("url", "/board/boardList.do");
			return "/redirect";
		}

		// 결과 데이터 넣어주기 jsp 파일로 전송
		model.addAttribute("rDTO", rDTO);

		// 변수 초기화(메모리 효율화 시키기 위해 사용함)
		pDTO = null;
		rDTO = null;

		// 종료 로그
		log.info(this.getClass().getName() + ".boardDetail end!");

		return "/board/boardDetail";
	}

	/**
	 * 수정할 글 보기
	 */
	@RequestMapping(value = "/board/editPost.do", method = RequestMethod.GET)
	public String editPost(HttpServletRequest request, ModelMap model) throws Exception {

		// 시작 로그
		log.info(this.getClass().getName() + ".editPost start!");

		// 요청으로부터 받은 파라미터를 변수에 저장
		String post_no = nvl(request.getParameter("no").replaceAll("(?!)<script", "&It;script")); // 글번호(PK)
		log.info("post_no : " + post_no); // 확인 로그

		// 게시글 번호을 담아 서비스에 전송할 DTO객체 생성
		BoardDTO pDTO = new BoardDTO();
		pDTO.setPost_no(post_no);

		// 게시글 상세정보 가져오기
		BoardDTO rDTO = boardService.getBoardDetail(pDTO);

		if (rDTO == null) {
			model.addAttribute("msg", "존재하지않는 게시물입니다.");
			model.addAttribute("url", "/board/boardList.do");
			return "/redirect";
		}

		// 결과 데이터 넣어주기 jsp 파일로 전송
		model.addAttribute("rDTO", rDTO);

		// 변수 초기화(메모리 효율화 시키기 위해 사용함)
		pDTO = null;
		rDTO = null;

		// 종료 로그
		log.info(this.getClass().getName() + ".editPost end!");

		return "/board/editPost";
	}

	/**
	 * 글 수정
	 */
	@RequestMapping(value = "/board/doEditPost.do", method = RequestMethod.POST)
	public String doEditPost(HttpServletRequest request, ModelMap model) throws Exception {

		// 시작 로그
		log.info(this.getClass().getName() + ".doEditPost start!");

		// 요청으로부터 받은 파라미터를 변수에 저장
		String post_no = nvl(request.getParameter("post_no").replaceAll("(?!)<script", "&It;script"));
		String post_title = nvl(request.getParameter("post_title").replaceAll("(?!)<script", "&It;script"));
		String post_content = nvl(request.getParameter("post_content").replaceAll("(?!)<script", "&It;script"));
		log.info("post_no : " + post_no); // 확인 로그
		log.info("post_title : " + post_title); // 확인 로그
		log.info("post_content : " + post_content); // 확인 로그

		// 게시글 정보를 담아 서비스에 전송할 DTO객체 생성
		BoardDTO pDTO = new BoardDTO();
		pDTO.setPost_no(post_no);
		pDTO.setPost_title(post_title);
		pDTO.setPost_content(post_content);

		// 게시글 수정 하기
		int res = boardService.updatePost(pDTO);

		String msg;
		String url = "/board/boardList.do";

		// 게시글 등록 성공할 경우 res에 0보다 큰 수가 저장됨
		if (res > 0) {
			msg = "게시글 수정 성공";
		} else { // res가 0일 경우 등록에 실패한 것
			msg = "게시글 수정 실패";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		// 결과 데이터 넣어주기 jsp 파일로 전송

		// 변수 초기화(메모리 효율화 시키기 위해 사용함)
		pDTO = null;

		// 종료 로그
		log.info(this.getClass().getName() + ".doEditPost end!");

		return "/redirect";

	}

	/**
	 * 글 삭제
	 */
	@RequestMapping(value = "/board/deletePost.do")
	public String deletePost(HttpServletRequest request, ModelMap model) throws Exception {

		// 시작 로그
		log.info(this.getClass().getName() + ".deletePost start!");

		// 요청으로부터 받은 파라미터를 변수에 저장
		String post_no = nvl(request.getParameter("no").replaceAll("(?!)<script", "&It;script"));
		log.info("post_no : " + post_no); // 확인 로그

		// 게시글 번호를 담아 서비스에 전송할 DTO객체 생성
		BoardDTO pDTO = new BoardDTO();
		pDTO.setPost_no(post_no);

		// 글 삭제 하기
		int res = boardService.deletePost(pDTO);

		// 변수 초기화(메모리 효율화 시키기 위해 사용함)
		pDTO = null;

		String msg;
		String url = "/board/boardList.do";

		if (res > 0) {
			msg = "게시글 삭제 성공";
		} else {
			msg = "게시글 삭제 실패";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		// 종료 로그
		log.info(this.getClass().getName() + ".deletePost end!");

		return "/redirect";
	}
}

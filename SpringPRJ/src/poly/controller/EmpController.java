package poly.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.dto.EmpDTO;
import poly.service.IEmpService;

/*
 * Controller 선언해야만 Spring 프레임워크에서 Controller인지 인식 가능
 * 자바 서블릿 역할 수행
 * */
@Controller
public class EmpController {

	// 로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(this.getClass());

	/*
	 * 비즈니스 로직(중요 로직을 수행하기 위해 사용되는 서비스를 메모리에 적재(싱글톤패턴 적용됨) static 선언 방식 기반의 싱글톤패턴
	 */
	@Resource(name = "EmpService")
	private IEmpService empService;

	@RequestMapping(value = "scott/empList")
	public String empList(ModelMap model) {
		log.info("empList start!!");

		List<EmpDTO> rList = empService.getEmpList();

		if (rList == null) {
			rList = new ArrayList<>();
		}

		for (EmpDTO eDTO : rList) {
			log.info("ename : " + eDTO.getEname());
			log.info("empno : " + eDTO.getEmpno());
		}

		model.addAttribute("rList", rList);

		return "/scott/empList";
	}

	@RequestMapping(value = "scott/managerList")
	public String managerList(ModelMap model) {
		log.info("managerList start!!");

		List<EmpDTO> rList = empService.getManagerList();

		if (rList == null) {
			rList = new ArrayList<>();
		}

		for (EmpDTO eDTO : rList) {
			log.info("ename : " + eDTO.getEname());
			log.info("empno : " + eDTO.getEmpno());
		}

		model.addAttribute("rList", rList);

		return "/scott/empList";
	}

}

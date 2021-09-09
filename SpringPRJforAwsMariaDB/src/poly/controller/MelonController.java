package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * Controller 선언해야만 Spring 프레임워크에서 Controller인지 인식 가능
 * 자바 서블릿 역할 수행
 * */
@Controller
public class MelonController {
	
	private final Logger log = Logger.getLogger(this.getClass());

	@RequestMapping("/")
	public String index() {
		log.info("index start");
		return "/index";
	}

}
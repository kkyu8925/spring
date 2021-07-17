package spring.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
// 스프링이 자동으로 스프링 빈으로 등록한다 (내부의 @Component 어노테이션이 있어서 컴포넌트 스캔의 대상이 됨)
// 스프링 MVC에서 어노테이션 기반의 컨트롤러로 인식한다. > RequestMappingHandlerMapping
// RequestMappingHandlerMapping 은 스프링 빈중에서 @RequestMapping, @Controller 를 인식한다.
// RequestMappingHandlerAdapter 으로 RequestMappingHandlerMapping 을 처리
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}

package spring.servlet.web.springmvc.v1;

import spring.servlet.domain.member.Member;
import spring.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
// 스프링이 자동으로 스프링 빈으로 등록한다 (내부의 @Component 어노테이션이 있어서 컴포넌트 스캔의 대상이 됨)
// 스프링 MVC에서 어노테이션 기반의 컨트롤러로 인식한다. > RequestMappingHandlerMapping
// RequestMappingHandlerMapping 은 스프링 빈중에서 @RequestMapping, @Controller 를 인식한다.
// RequestMappingHandlerAdapter 으로 RequestMappingHandlerMapping 을 처리
public class SpringMemberSaveControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
    }
}

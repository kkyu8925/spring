package spring.servlet.web.springmvc.v2;

import spring.servlet.domain.member.Member;
import spring.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
// 스프링이 자동으로 스프링 빈으로 등록한다 (내부의 @Component 어노테이션이 있어서 컴포넌트 스캔의 대상이 됨)
// 스프링 MVC에서 어노테이션 기반의 컨트롤러로 인식한다. > RequestMappingHandlerMapping
// RequestMappingHandlerMapping 은 스프링 빈중에서 @RequestMapping, @Controller 를 인식한다.
// RequestMappingHandlerAdapter 으로 RequestMappingHandlerMapping 을 처리
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
    }

    @RequestMapping
    public ModelAndView members() {

        List<Member> members = memberRepository.findAll();

        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);
        return mv;
    }
}

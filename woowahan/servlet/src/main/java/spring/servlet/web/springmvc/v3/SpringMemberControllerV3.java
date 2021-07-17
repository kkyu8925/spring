package spring.servlet.web.springmvc.v3;

import spring.servlet.domain.member.Member;
import spring.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
// 스프링이 자동으로 스프링 빈으로 등록한다 (내부의 @Component 어노테이션이 있어서 컴포넌트 스캔의 대상이 됨)
// 스프링 MVC에서 어노테이션 기반의 컨트롤러로 인식한다. > RequestMappingHandlerMapping
// RequestMappingHandlerMapping 은 스프링 빈중에서 @RequestMapping, @Controller 를 인식한다.
// RequestMappingHandlerAdapter 으로 RequestMappingHandlerMapping 을 처리
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }

    @GetMapping
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members";
    }
}

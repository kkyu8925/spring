package spring.servlet.web.frontcontroller.v4.controller;

import spring.servlet.domain.member.Member;
import spring.servlet.domain.member.MemberRepository;
import spring.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    private final MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        List<Member> members = memberRepository.findAll();

        // v3
//        ModelView mv = new ModelView("members");
//        mv.getModel().put("members", members);
//        return mv;

        // v4
        model.put("members", members);
        return "members";
    }
}

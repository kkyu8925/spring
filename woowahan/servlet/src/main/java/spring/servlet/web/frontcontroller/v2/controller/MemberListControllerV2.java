package spring.servlet.web.frontcontroller.v2.controller;

import spring.servlet.domain.member.Member;
import spring.servlet.domain.member.MemberRepository;
import spring.servlet.web.frontcontroller.MyView;
import spring.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Member> members = memberRepository.findAll();

        // Model 데이터를 보관한다.
        request.setAttribute("members", members);

//        v1
//        String viewPath = "/WEB-INF/views/members.jsp";
//        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
//        dispatcher.forward(request, response);

//        v2
        return new MyView("/WEB-INF/views/members.jsp");
    }
}

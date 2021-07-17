package spring.servlet.web.frontcontroller.v4.controller;

import spring.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        // V1
//        String viewPath = "/WEB-INF/views/new-form.jsp";
//        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
//        dispatcher.forward(request, response);

        // v2
//        return new MyView("/WEB-INF/views/new-form.jsp");

        // v3
//        return new ModelView("new-form");

        // v4
        return "new-form";
    }
}

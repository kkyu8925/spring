package spring.servlet.web.frontcontroller.v3.controller;

import spring.servlet.web.frontcontroller.ModelView;
import spring.servlet.web.frontcontroller.MyView;
import spring.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {

        // V1
//        String viewPath = "/WEB-INF/views/new-form.jsp";
//        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
//        dispatcher.forward(request, response);

        // v2
//        return new MyView("/WEB-INF/views/new-form.jsp");

        // v3
        return new ModelView("new-form");
    }
}

package spring.servlet.web.frontcontroller.v3;

import spring.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {

    // v1
//    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    // v2
//    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    // v3
    ModelView process(Map<String, String> paramMap);
}

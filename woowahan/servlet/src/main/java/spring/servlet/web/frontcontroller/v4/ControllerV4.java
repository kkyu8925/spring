package spring.servlet.web.frontcontroller.v4;


import java.util.Map;

public interface ControllerV4 {

    // v1
//    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    // v2
//    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    // v3
//    ModelView process(Map<String, String> paramMap);

    /**
     * v4
     *
     * @param paramMap
     * @param model
     * @return viewName
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}

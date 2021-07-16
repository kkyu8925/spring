package spring.servlet.web.frontcontroller.v2;

import spring.servlet.web.frontcontroller.MyView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {

    // v1
//    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

    // v2
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}

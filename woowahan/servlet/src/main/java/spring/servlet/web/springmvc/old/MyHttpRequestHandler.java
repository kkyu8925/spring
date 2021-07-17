package spring.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 스프링 부트가 자동 등록하는 핸들러 매핑
// 0. RequestMappingHandlerMapping : 어노테이션 기반의 컨트롤러인 @RequestMapping
// 1. BeanNameUrlHandlerMapping : 스프링 빈의 이름
// 스프링 부트가 자동 등록하는 핸들러 어뎁터
// 0. RequestMappingHandlerAdapter : 어노테이션 기반의 컨트롤러인 @RequestMapping
// 1. HttpRequestHandlerAdapter : HttpRequestHandler
// 2. SimpleControllerHandlerAdapter : Controller 인터페이스(어노테이션 x), 과거의 사용
@Component("/springmvc/request-handler")
public class MyHttpRequestHandler implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest");
    }
}
package spring.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 톰켓이 실행되면 서블릿 컨테이너에 서블릿을 싱글톤으로 생성함.
// HTTP 요청을 통해 매핑된 URL 호출되면 서블릿의 service 메서드 실행
@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override // HttpServletRequest, HttpServletResponse 객체는 HTTP 요청이 오면 WAS 가 만들어서 실행할 때 넣어줌
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // [status-line]
        response.setStatus(HttpServletResponse.SC_OK);

        // [response-headers]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");

        // [Header 편의 메서드]
//        content(response);
//        cookie(response);
        redirect(response);

        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }

    private void content(HttpServletResponse response) {
        // Content-Type: text/plain;charset=utf-8
        // Content-Length: 2

        // 1번 방식
        // response.setHeader("Content-Type", "text/plain;charset=utf-8");

        // 2번 방식
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
//        response.setContentLength(2); // (생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
        // Set-Cookie: myCookie=good; Max-Age=600;

        // 1번 방식
        // response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");

        // 2번 방식
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); // 600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        // Status Code 302
        // Location: /basic/hello-form.html

        // 1번 방식
        // response.setStatus(HttpServletResponse.SC_FOUND); // 302
        // response.setHeader("Location", "/basic/hello-form.html");

        // 2번 방식
        response.sendRedirect("/basic/hello-form.html");
    }
}

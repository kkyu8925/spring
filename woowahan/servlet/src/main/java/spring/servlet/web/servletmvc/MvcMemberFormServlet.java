package spring.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 톰켓이 실행되면 서블릿 컨테이너에 서블릿을 싱글톤으로 생성함.
// HTTP 요청을 통해 매핑된 URL 호출되면 서블릿의 service 메서드 실행
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override // HttpServletRequest, HttpServletResponse 객체는 HTTP 요청이 오면 WAS 가 만들어서 실행할 때 넣어줌
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response); // 다른 서블릿이나 JSP 이동하는 기능. 서버 내부에서 호출이 발생한다. 웹브라우저에서 요청x,
        // redirect 는 웹브라우저에서 다시 응답해 요청한다. 따라서 클라이언트가 인지가능
        // 반면에 포워드는 서버내부에서 일어나는 호출이기 때문에 클라이언트가 인지하지 못함
    }
}
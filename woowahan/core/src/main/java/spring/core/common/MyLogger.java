package spring.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//@Scope(value = "request")
// http 요청 당 하나씩 생성되고, http 요청이 끝나는 시점에 종료됨
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
// Http request 와 상관없이 CGLIB 라는 라이브러로 클래스를 상속 받은 가짜 프록시 객체를 다른 빈에 미리 주입한다.
// 가짜 객체는 실제 요청이 오면 그때 내부에서 실제 빈을 요청하는 위임 로직이 들어있다.
// 가짜 객체는 원본 클래스를 상속 받아서 만들어졌기 때문에 객체를 사용하는 클라이언트 입장에서는 아무 상관없다(다형성)
// 진짜 객체 조회를 필요한 시점까지 지연처리 하는 것
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " +
                message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}

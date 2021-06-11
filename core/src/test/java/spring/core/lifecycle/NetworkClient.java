package spring.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// InitializingBean, DisposableBean 은 오래된 방법으로 이제는 잘 사용하지 않는다.
//public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
//        connect();
//        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작 시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String mes) {
        System.out.println("Call: " + url + " mes = " + mes);
    }

    //  서비스 종료 시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

//    // DI 끝나면 실행행
//   @Override
//    public void afterPropertiesSet() throws Exception {
//       System.out.println("NetworkClient.afterPropertiesSet");
//       connect();
//       call("초기화 연결 메시지");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }

    // DI 끝나면 실행행
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}

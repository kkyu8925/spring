package spring.mvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
// @slf4j = private final Logger log = LoggerFactory.getLogger(getClass());
@RestController
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        // 이 방식은 연산이 일어난다.
        // 연산이 일어나면서 리소스 낭비
        log.trace(" trace my log=" + name);

        // 올바른 사용법
        // 파라미터로 넘기면서 출력이 일어나지 않는다.
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}

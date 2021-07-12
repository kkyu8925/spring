package spring.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        // 1. 상태를 유지하는 설계
//        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
//        StatefulService statefulService1 = ac.getBean(StatefulService.class);
//        StatefulService statefulService2 = ac.getBean(StatefulService.class);
//
//        // Thread A : A 사용자 10000원 주문
//        statefulService1.order("userA", 10000);
//        // Thread B : B 사용자 20000원 주문
//        statefulService2.order("userB", 20000);
//
//        // A 사용자 주문 금액 조회
//        int price = statefulService1.getPrice();
//        // 상태를 유지하는 설계로 A 사용자는 10000원을 기대했지만, 20000원 출력
//        System.out.println("price = " + price);
//
//        assertThat(price).isEqualTo(20000);

        // 2. 무상태를 유지하는 설계
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // Thread A : A 사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // Thread B : B 사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // 무상태를 유지하는 설계로 A 사용자가 기대한 10000원이 나온다.
        System.out.println("userAPrice = " + userAPrice);

        assertThat(userAPrice).isEqualTo(10000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}
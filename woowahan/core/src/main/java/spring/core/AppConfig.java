// 1. 스프링 컨테이너에 빈 등록(생성)
// 2. 의존관계 주입(DI)
// 스프링은 빈을 생성하고, 의존관계 주입하는 단계가 나누어져 있다.
// 그런데 이렇게 자바 코드로 스프링 빈을 등록하면 생성자를 호출하면서 의존관계 주입도 한번에 처리된다.
package spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.discount.DiscountPolicy;
import spring.core.discount.RateDiscountPolicy;
import spring.core.member.MemberRepository;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.core.member.MemoryMemberRepository;
import spring.core.order.OrderService;
import spring.core.order.OrderServiceImpl;

@Configuration // 싱글톤을 보장하기위해 바이트코드 조작 기술인 CGLIB 를 사용한다.
// @Bean 이 호출하는 메서드의 빈이 존재하면 존재하는 빈을 반환, 없으면 등록하고 반환하는 코드가 동적으로 만들어짐
public class AppConfig {

    @Bean // 기본적으로 메서드 이름으로 등록
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean(name = "memberRepository") // 빈 이름 변경
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}

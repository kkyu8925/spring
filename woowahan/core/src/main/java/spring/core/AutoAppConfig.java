package spring.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 검토넌트스캔 범위 지정
        basePackages = "spring.core",
//        basePackages = {"spring.core","spring"} // 여러개도 가능
        basePackageClasses = AutoAppConfig.class,
        // 지정하지 않으면 @ComponentScan 이 붙은 클래스의 패키지가 시작위치

        // 제외 필터 / @Configuration 도 @Component 포함한다.
        // 보통 설정 정보를 컴포넌트 스캔대상에서 제외하지는 않지만, 기존 예제 코드를 유지하기 위해 제외함
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
//@ComponentScan > @Component 를 싱글톤 패턴으로 스프링 컨테이너에 등록한다.
public class AutoAppConfig {
    // 기존의 AppConfig 와 다르게 @Bean 이 없다!
}

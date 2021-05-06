package config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Mapper {
	/**
	 * The value may indicate a suggestion for a logical mapper name, to be turned
	 * into a Spring bean in case of an autodetected component.
	 * 
	 * @return the suggested mapper name, if any
	 */
	String value() default "";

}
// 기존 스프링프레임워크에서는 Mapper 인터페이스를 제공하지않는다.
// 스프링 프레임워크와 MyBatis를 연동하기 위해 Mapper 인터페이스를 생성함.
// MyBatis은 오라클등 데이터베이스에서 사용되는 SQL을 쉽게 사용하기 위한 프레임워크로 스프링과 같이 사용됨.
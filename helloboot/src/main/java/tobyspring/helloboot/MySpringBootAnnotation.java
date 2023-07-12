package tobyspring.helloboot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RetentionPolicy.Class
 * 실제 리텐션의 기본값
 * 컴파일된 클래스 파일까지는 살아있지만 그 클래스를 런타임때 메모리를 로딩할 때는 정보가 사라짐
 */

/**
 * TYPE
 * 클래스,인터페이스, 이넘 대상에게 부여할 수 있는 어노테이션
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration
@ComponentScan
public @interface MySpringBootAnnotation {

}

package tobyspring.helloboot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import tobyspring.helloboot.config.EnableMyAutoConfiguration;
import tobyspring.helloboot.config.autoconfig.DispatcherServletConfig;
import tobyspring.helloboot.config.autoconfig.TomcatWebServerConfig;

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

/**
 * component에노테이션이 붙은 클래스들을 임포트로 추가해주면 직접 추가할 수 있음
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration
@ComponentScan
@EnableMyAutoConfiguration
//@Import({DispatcherServletConfig.class, TomcatWebServerConfig.class }) 나중에 오토컨픽이 많아지면 최상위 클래스가 많이 나열되는 거 피하는게 좋음
public @interface MySpringBootApplication {

}

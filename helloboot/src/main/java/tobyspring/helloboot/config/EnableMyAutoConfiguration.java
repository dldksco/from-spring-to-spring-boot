package tobyspring.helloboot.config;

import org.springframework.context.annotation.Import;
import tobyspring.helloboot.config.autoconfig.DispatcherServletConfig;
import tobyspring.helloboot.config.autoconfig.TomcatWebServerConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Auto configuration이란
 * 미리 준비한 config을 스프링 부트가 우리가 어떤게 필요한지 판단하고 자동으로 판단해서 선택함
 */

/**
 * 애플리케이션 빈이란?
 * 개발자가 어떤 빈을 사용하겠다고 명시적으로 구성정보를 제공한 걸 말함
 *
 * 애플리케이션 로직 빈
 * 애플리케이션을 개발한다고 할 때 그때의 코드 (controller, service등)
 * 애플리케이션 인프라스트럭처 빈
 * 대부분 기술과 관련됨 보통 직접 작성하지 않음 이미 만들어져있는 거를 이 애플리케이션에는 이거이거 쓰겠다고 함
 * 컨테이너 인프라스트럭처 빈이란?
 * 스프링 컨테이너 자신이거나 스프링 컨테이너가 계속 기능을 확장하면서 추가해온 것을 빈으로 등록해서 사용한 것
 * 이러한 빈은 컨테이너가 스스로 빈으로 등록해서 동작시킴
 *
 * 스프링컨테이너가 하는 가장 중요한 일 빈오브젝트를 생성하고 초기화하고 관계를 맺는 라이프사이클임
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({DispatcherServletConfig.class, TomcatWebServerConfig.class})
public @interface EnableMyAutoConfiguration {
}

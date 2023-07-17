package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class Config {

    /**
     * 서블릿컨테이너를 따로 설치해서 실행을 시켜서 빈으로 등록을 안했었음
     * 애플리케이션 인프라스트럭처 빈
     * @return
     */
    @Bean
    public ServletWebServerFactory serverFactory(){
        return new TomcatServletWebServerFactory();
    }

    /**
     * 스프링 mvc를 사용하면 필요하긴 했지만 직접 컨테이너한테 서블릿으로 등록해서 썼었음
     * 애플리케이션 인프라스트럭처 빈
     * @return
     */
    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }
}

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
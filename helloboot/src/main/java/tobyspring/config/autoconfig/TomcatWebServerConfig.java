package tobyspring.config.autoconfig;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatWebServerConfig {

    /**
     * 서블릿컨테이너를 따로 설치해서 실행을 시켜서 빈으로 등록을 안했었음
     * 애플리케이션 인프라스트럭처 빈
     * @return
     */
    @Bean
    public ServletWebServerFactory serverFactory(){
        return new TomcatServletWebServerFactory();
    }
}

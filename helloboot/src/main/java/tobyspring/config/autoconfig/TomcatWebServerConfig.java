package tobyspring.config.autoconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {

    /**
     * 이거 바로 안됨 치환해주는 후처리해주는 어떤걸 추가해줘야함
     */
    @Value("${contextPath:}")
    String contextPath;

    @Value("${port:8080}")
    int port;
    /**
     * 서블릿컨테이너를 따로 설치해서 실행을 시켜서 빈으로 등록을 안했었음
     * 애플리케이션 인프라스트럭처 빈
     * @return
     */
    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean
    public ServletWebServerFactory serverFactory (){
        TomcatServletWebServerFactory facory = new TomcatServletWebServerFactory();
        facory.setContextPath(this.contextPath);
        facory.setPort(this.port);
        return facory;
    }

}

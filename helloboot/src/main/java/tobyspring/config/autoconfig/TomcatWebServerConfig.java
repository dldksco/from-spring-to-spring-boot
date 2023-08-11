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
import tobyspring.config.EnableMyAutoConfiguration;
import tobyspring.config.EnableMyConfigurationProperties;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
@EnableMyConfigurationProperties(ServerProperties.class)
public class TomcatWebServerConfig {


    /**
     * 서블릿컨테이너를 따로 설치해서 실행을 시켜서 빈으로 등록을 안했었음
     * 애플리케이션 인프라스트럭처 빈
     * @return
     */
    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean

    public ServletWebServerFactory serverFactory (ServerProperties properties){
        TomcatServletWebServerFactory facory = new TomcatServletWebServerFactory();
        facory.setContextPath(properties.getContextPath());
        facory.setPort(properties.getPort());
        return facory;
    }

}

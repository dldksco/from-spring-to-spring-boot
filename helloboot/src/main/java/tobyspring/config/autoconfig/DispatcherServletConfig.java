package tobyspring.config.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
/**
 * 유저 구성정보에 들어가면 안됨
 * 얘네는 자동으로 등록이 되게 만들어야함 즉 컴포넌트 스캔 대상에서 제외해야함
 * 컴포넌트스캔은 기본적으로 선언된 패키지를 베이스로 스캔을함 즉 다른 패키지로 보내면됨
 */
public class DispatcherServletConfig {

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

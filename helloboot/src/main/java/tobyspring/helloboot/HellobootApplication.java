package tobyspring.helloboot;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Configuration을 이용하는 Application에 처음 등록됨
 * 이 클래스는 빈 팩토리 메소드를 가지는 것 이상으로 전체 애플리케이션을 구성하는데 주요한 정보를 많이 넣을 수 있현
 *
 */

/**
 * @CoponentScan이 붙어있으면
 * 이클래스가 있는 패키지부터 하위패키지를 뒤져서 @Component가 들어있는 모든 것들을 빈으로 등록함
 * 장점 : 새로운 빈 만들어서 추가할때 매번 어디다가 구성정보를 다시 등록해줄 필요없이 알아서 @Component만 달면 해결됨
 * 단점 : 정말 빈으로 등록하는 클래스가 많아진다면 정확하게 어떤 것들이 등록되는가 찾는게 매우번거로움 하지만 패키지구성과 모듈화를 잘하면
 */
@Configuration
@ComponentScan
public class HellobootApplication {

	@Bean
	public ServletWebServerFactory serverFactory(){
		return new TomcatServletWebServerFactory();
	}

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}

	public static void main(String[] args) {
		//자바 컨픽을 읽을 수 없음
		//		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();

				ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
				DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
//				dispatcherServlet.setApplicationContext(this); 없어도 스프링이 직접 주입해줌

				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet",
							new DispatcherServlet(this)
					).addMapping("/*");
				});
				webServer.start();
			}
		};
		applicationContext.register(HellobootApplication.class);
		applicationContext.refresh();
		//톰캣 외의 제티 같은 다른 웹서버도 이용가능


	}

}

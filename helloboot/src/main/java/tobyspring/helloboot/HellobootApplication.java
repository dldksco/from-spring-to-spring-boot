package tobyspring.helloboot;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
public class HellobootApplication {

	public static void main(String[] args) {
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
		/**
		 * DI란? 인터페이스를 중간에 잘 두고 코드레벨의 의존관계를 제거하고 동적으로 스프링 컨테이너(어셈블러)를 통해서 둘 사이의 연관관계를 주입을 통해 지정하도록 만들
		 */
		applicationContext.registerBean(HelloController.class);
		/**
		 * 심플헬로우 서비스를 어떻게 헬로우 컨트롤러에 넣을까?
		 * 원래는 xml에서 빈을 정의해서 클래스이름 정의하고 생성자에 어떤 빈을 넣어줄지 정했음
		 * 그럼 컨트롤러보다 서비스가 먼저 만들어져야 되는 거 아니야?
		 * 그건 스프링 컨테이너가 알아서 잘 처리함
		 */
		applicationContext.registerBean(SimpleHelloService.class);

		applicationContext.refresh();
		//톰캣 외의 제티 같은 다른 웹서버도 이용가능
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet("dispatcherServlet",
						new DispatcherServlet(applicationContext)
					).addMapping("/*");
				});
		webServer.start();

	}

}

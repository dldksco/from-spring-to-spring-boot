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

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
public class HellobootApplication {

	public static void main(String[] args) {
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		applicationContext.registerBean(HelloController.class);
		applicationContext.refresh();
		//톰캣 외의 제티 같은 다른 웹서버도 이용가능
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {

			servletContext.addServlet("frontcontroller", new HttpServlet() {
				//요청과 응답을 만드는데 필요한 오브젝트를 파라미터로 넣음
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

					//인증, 보안과 같은 공통기능을 프론트 컨트롤러가 하고
					//서블릿 컨테이너의 매핑기능을 프론트 컨트롤러가 담당함
					if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())){
						String name = req.getParameter("/hello");
						/**
						 * Front Controller가 직접 new를 선언해서 쓰는 거랑 뭐가 다를까?
						 * 여러가지 주요한 스프링 컨테이너들이 할 수 있는 계속 적용 가능한 기본적인 구조를 짜놓음
						 * 스프링 컨테이너는 오브젝트를 만들 때 딱 한 번만 만들음
						 * 실제로는 여러 서블릿이 있을 수 있음 해당 오브젝트를 계속 동일한 오브젝트를 리턴하게 할 수 있음
						 * 스프링 컨테이너를 Singletone registry라고도 함
						 * 매 요청마다 새 오브젝트를 만들지않고 만들어둔 오브젝트를 재사용함
						 */
						HelloController helloController = applicationContext.getBean(HelloController.class);
						String ret = helloController.hello(name);

						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println(ret);
					}
					else{
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}
				}
			}).addMapping("/*");
		});
		webServer.start();

	}

}

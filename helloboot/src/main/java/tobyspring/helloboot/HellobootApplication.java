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

			servletContext.addServlet("frontcontroller", new HttpServlet() {
				//요청과 응답을 만드는데 필요한 오브젝트를 파라미터로 넣음


				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					/**
					 * 서블릿 컨테이너를 관리하지 않도록 개발을 해야함
					 * 하지만 애플리케이션 로직과 긴밀하게 연관이 된 것이 서블릿에 나옴
					 * ex) 매핑 웹요청을 가지고 이거를 처리 해줄 컨트롤러 컨트롤러 메소드가 어떤 것인지 연결 시켜줌
					 */
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

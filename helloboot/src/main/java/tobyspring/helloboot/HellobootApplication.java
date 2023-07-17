package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;

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
@MySpringBootApplication
public class HellobootApplication {

	public static void main(String[] args) {
		//자바 컨픽을 읽을 수 없음
		//		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext() {
		SpringApplication.run(HellobootApplication.class, args);


	}

}

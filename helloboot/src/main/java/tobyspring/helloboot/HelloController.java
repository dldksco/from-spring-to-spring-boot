package tobyspring.helloboot;

import java.util.Objects;

public class HelloController {
    private final HelloService helloService;

    /**
     * 스프링 컨테이너가 헬로우서비스 오브젝트를 만들려면 생성자를 만들어야되는데 생성자에 주입해야될 파라미터 타입이 인터페이스라는 것을 알 수 있음
     * 그 후, 컨테이너를 다뒤져서 헬로우서비스를 구현한 인터페이스가 있는지 확인함
     * @param helloService
     */
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name){
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}

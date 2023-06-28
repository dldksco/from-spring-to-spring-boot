package tobyspring.helloboot;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 디스패치서블릿이 매핑정보를 만들때 클래스에 붙어있는 정보를 확인하고
 * 그 후, 메소드 레벨에 붙어있는 정보를 추가
 */

/**
 * 왜 굳이 어노테이션을 정의해줄까?
 * 컴포넌트가 붙었다는건 빈 오브젝트인데
 * 그 빈 오브젝트가 어떤 종류다라고 구체적으로 명시하고 싶음
 * 전통적인 계층형아키텍쳐에서 웹인지, 서비스인지 구분해놓을 수 있음
 * 스프링이 전형적인 타입(스테레오 타입)이란 어노테이션들을 만들어둠
 */


@RestController //메타어노테이션도 컴포넌트로 인식
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


    /**
     * @ResponseBody를 안붙이고,
     * 이런 식으로 스트링을 리턴한다면
     * 그 스트링값에 해당하는 뷰가 있는지 체크함
     * 우리는 뷰가 없으니 404가나
     */

    @GetMapping("/hello")
    public String hello(String name){
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}

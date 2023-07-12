package tobyspring.helloboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Primary
public class HelloDecorator implements HelloService{
    /**
     * 데코레이터 패턴이란?
     * 다른 서비스들과 같이 인터페이스를 구현함 동시에 같은 타입의 어떠한 인터페이스 오브젝트를 의존하고 있음
     * 즉 그 인터페이스를 구현한 다른 오브젝트를 호출할 수 있음
     * 런타임시 주요한 기능을 가진 코드들을 건드리지 않은채로 어떤 기능, 어떤 책임을 추가하고 싶을 때 사용
     */

    /**
     * 프록시 패턴이란?
     * 실제 실체 대신 다른 것을 두어서, 여러 가지 부가적인 효과를 제공할 수 있음
     * 실제 로직을 가진 오브젝트가 비용이 많은 오브젝트라면, 서버가 시작 될 때 만들어둘 필요가 없고 정말 필요할 때 첫 번째 요청이 들어오면, 온디멘드로 오브젝트를 만들어줌 레이지 로딩이라고함
     * 로컬에 구현했다고 생각했지만, 서버에 있는 오브젝트를 사용할 때가 있음, 이때 프록시를 이용해 프록시는 로컬인척하고, 서버에 있는 데이터를 불러와 리턴을 함, 이러면 요청자가 뒷쪽 단을 전혀 신경쓰지 않아도됨 REMOTE ACCESS
     **/
    private final HelloService helloService;

    public HelloDecorator(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name){
        return "*" + helloService.sayHello(name) + "*";
    }
}

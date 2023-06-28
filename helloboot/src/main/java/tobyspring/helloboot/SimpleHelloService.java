package tobyspring.helloboot;

import org.springframework.stereotype.Component;

/**
 * 실제로 구현체를 바라보게 한다면,
 * 컨트롤러가 서비스에 대한 의존 관계가 발생한다.
 * 만약 서비스의 명이 바뀐다면?
 * 코드들을 다 바꿔줘야함
 * 서비스 인터페이스를 의존하게 만듬
 * 문제는 HelloController가 해당 인터페이스의 구현체를 뭘 써야할지 모름
 * 이걸 해결해주는 작업과정을 Dependency Injection이라고함 이걸 해주는 애를 Assembler라고함
 * 이 어셈블러가 spring container임
 * 스프링 컨테이너의 역할은 메타정보를 주면 그걸 가지고 클래스의 싱글톤 오브젝트를 만드는데
 * 이 오브젝트가 사용할 다른 의존 오브젝트가 있다면 그거까지 주입해줌
 *
 * 주입을 해준다는 것은 레퍼런스를 넘겨준다는 것
 * 제일 쉬운 방법은 HelloController를 만들 때 생성자 파라미터로 심플헬로우 서비스의 오브젝트를 집어넣어줌
 * 이때 파라미터의 타입은 인터페이스타입임
 *
 * 또 다른 방법은 팩토리 메소드 같은 걸로 빈을 만들면서 파라미터로 넘김
 *
 * 컨트롤러 클래스에 프로퍼티를 정의해서 세터머소드를 정의해서 사용해야될 서비스 오브젝트를 정의해줌
 */
@Component
public class SimpleHelloService implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello" + name;
    }
}

package tobyspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * 스프링이 컨테이너 시작할때 프록시를 클래스를 생산하고
 * @Configuration이 붙은 빈 오브젝트로 사용함
 * 만약 팩토리메소드로 통해서 여러번 만들어도 딱 한 개만만듬
 * 근데 자바코드로 한다 하나의 빈을 두개이상의 다른 빈에서 의존한다면 그 팩토리 메소드를 호출할때마다 새로 만듬
 * 이를 해결하기위해 프록시를 만들어서 해결함
 *
 * 근데 이걸 끌 수 있도록 만들음
 * 빈 메소드를 통해서 빈을 만들때 또다른 빈 메소드를 호출해서 의존오브젝트를 가져오는 식으로 가져오지 않았다면
 * 시간이드는 프록시 방식을 사용할 필요가 없음
 */
public class ConfigurationTest {
    @Test
    void beanConfiguration(){
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(Myconfig.class);
        ac.refresh();
        Bean1 bean1 = ac.getBean(Bean1.class);
        Bean2 bean2 = ac.getBean(Bean2.class);

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    @Test
    void proxyCommonMethod(){
        /**
         * 데코레이터처럼 타켓오브젝트를 따로두고 동적으로 끼어들어서 중계하는 역할을하는게 아니라
         * 확장해서 대체하는 방식으로감
         */
        MyConfigProxy myConfigProxy = new MyConfigProxy();
        Bean1 bean1 = myConfigProxy.bean1();
        Bean2 bean2 = myConfigProxy.bean2();

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);

    }

    @Test
    void configuration(){
        Myconfig myconfig = new Myconfig();
        Bean1 bean1 = myconfig.bean1();

        Bean2 bean2 = myconfig.bean2();

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);

    }




    static class MyConfigProxy extends Myconfig{
        private Common common;
        @Override
         Common common(){
            if(this.common==null) this.common = super.common();
            return this.common;
        }
    }


    /**
     * proxybean methods가 default가 true가 돼있으면
     * 클래스가 빈으로 등록될때 프록시 오브젝트를 하나 두고 그게 빈으로 등록이됨
     */
    @Configuration
    static class Myconfig{
        @Bean
        Common common(){
            return new Common();
        }
        @Bean
        Bean1 bean1(){
            return new Bean1(common());
        }
        @Bean
        Bean2 bean2(){
            return new Bean2(common());
        }
    }

    static  class Bean1{
        private final Common common;
        Bean1(Common common){
            this.common=common;
        }
    }

    static  class Bean2{
        private final Common common;
        Bean2(Common common){
            this.common=common;
        }
    }
    static class Common{

    }
    // Bean1 <-- Common
    // Bean2 <-- Common
}

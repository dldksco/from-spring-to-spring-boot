package tobyspring.config.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
public class PropertyPlaceholderConfig {
    /**
     * 들어가서보면 BeanFactoryPostProcessor라는 애를 구현하고있음
     * 빈을 정의하고있는 기본적인 정보를 모은다음에 후처리기를 돌림
     * 앞에서 진행했던 작업에 대해 부가적인 작업을 할 수 있게함
     * @return
     */
    @Bean
    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}

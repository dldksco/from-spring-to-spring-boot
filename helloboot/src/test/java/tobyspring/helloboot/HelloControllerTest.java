package tobyspring.helloboot;
import java.util.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HelloControllerTest {
    @Test
    void helloController() {
        HelloController helloController= new HelloController(name -> name);

        String ret = helloController.hello("test");

        assertThat(ret).isEqualTo("test");
    }

    @Test
    void failsHelloController() {
        HelloController helloController= new HelloController(name -> name);

        assertThatThrownBy(() ->{
            String ret = helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() ->{
            String ret = helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }


}

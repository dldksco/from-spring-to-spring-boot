package tobyspring.helloboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//이걸로 모든 빈들 다 긁어옴
@HelloBootTest
public class DataSourceTest {
    @Autowired DataSource dataSource;

       @Test
    void connect() throws SQLException{
           Connection connection = dataSource.getConnection();
           connection.close();
       }

}

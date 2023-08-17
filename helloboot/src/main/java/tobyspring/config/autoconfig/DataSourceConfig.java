package tobyspring.config.autoconfig;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.EnableMyConfigurationProperties;
import tobyspring.config.MyAutoConfiguration;

import javax.sql.DataSource;
import java.sql.Driver;

@MyAutoConfiguration
@EnableTransactionManagement
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(MyDataSourceProperty.class)
public class DataSourceConfig {

    @Bean
    @ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource")
    DataSource hikariDataSource(MyDataSourceProperty myDataSourceProperty){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(myDataSourceProperty.getDriverClassName());
        dataSource.setJdbcUrl(myDataSourceProperty.getUrl());
        dataSource.setUsername(myDataSourceProperty.getUsername());
        dataSource.setPassword(myDataSourceProperty.getPassword());
        return dataSource;
    }
    @Bean
    @ConditionalOnMissingBean
    DataSource dataSource(MyDataSourceProperty myDataSourceProperty) throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(myDataSourceProperty.getDriverClassName()));
        dataSource.setUrl(myDataSourceProperty.getUrl());
        dataSource.setUsername(myDataSourceProperty.getUsername());
        dataSource.setPassword(myDataSourceProperty.getPassword());

        return dataSource;
    }

    /**
     * 빈 메소드가 실행이 될 때 스프링 컨ㄴ테이너 빈 구성정보에 그 데이터소스가 딱 하나만 있다면
     * 그떄 그 하나의 데이터소스를 가져와서 사용함
     * @param dataSource
     * @return
     */
    @Bean
    @ConditionalOnSingleCandidate(DataSourceConfig.class)
    @ConditionalOnMissingBean
    JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @ConditionalOnSingleCandidate(DataSourceConfig.class)
    @ConditionalOnMissingBean
    JdbcTransactionManager jdbcTransactionManager(DataSource dataSource){
        return new JdbcTransactionManager(dataSource);
    }

}

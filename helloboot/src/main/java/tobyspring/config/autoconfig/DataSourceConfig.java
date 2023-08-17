package tobyspring.config.autoconfig;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.EnableMyConfigurationProperties;
import tobyspring.config.MyAutoConfiguration;

import javax.sql.DataSource;
import java.sql.Driver;

@MyAutoConfiguration
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

}

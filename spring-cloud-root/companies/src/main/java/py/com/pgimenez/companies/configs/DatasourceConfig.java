package py.com.pgimenez.companies.configs;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = "classpath:application.properties")
public class DatasourceConfig {

    @Bean(name = "hikariConfigPostgres")
    @ConfigurationProperties(prefix = "spring.datasource.companies-db-postgres")
    public HikariConfig hikariConfigPostgres() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name = "companiesDataSourcePostgres")
    @DependsOn("hikariConfigPostgres")
    @Primary
    public DataSource companiesDataSourcePostgres(@Qualifier("hikariConfigPostgres") HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

}

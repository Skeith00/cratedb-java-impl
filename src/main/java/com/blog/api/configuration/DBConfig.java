package com.blog.api.configuration;

import com.blog.api.configuration.properties.DBProperties;
import com.blog.api.model.converter.MetadataConverter;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.crate.client.jdbc.CrateDriver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.data.relational.core.dialect.PostgresDialect;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

//https://spring.io/blog/2020/05/20/migrating-to-spring-data-jdbc-2-0
@Configuration
@EnableJdbcRepositories(basePackages = "com.blog.api.repository")
public class DBConfig extends AbstractJdbcConfiguration {

    private static final String QUALIFIER = "search";

    @Bean
    @Qualifier(QUALIFIER)
    public DataSource dataSource(DBProperties dbProperties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbProperties.url());
        config.setUsername(dbProperties.user());
        config.setPassword(dbProperties.password());
        config.setSchema(dbProperties.schema());
        config.setDriverClassName(CrateDriver.class.getName());
        //config.addDataSourceProperty("prepStmtCacheSize", "250");
        return new HikariDataSource(config);
    }

    @Override
    public Dialect jdbcDialect(NamedParameterJdbcOperations operations) {
        return PostgresDialect.INSTANCE;
    }

    @Bean
    public NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    @Qualifier(QUALIFIER)
    public PlatformTransactionManager transactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean
    public JdbcCustomConversions jdbcCustomConversions() {
        final List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(MetadataConverter.EntityWritingConverter.INSTANCE);
        converters.add(MetadataConverter.EntityReadingConverter.INSTANCE);
        return new JdbcCustomConversions(converters);
    }
}

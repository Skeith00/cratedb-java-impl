package com.blog.api.configuration;

import com.blog.api.configuration.properties.DBProperties;
import io.crate.shade.org.postgresql.ds.PGConnectionPoolDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class DBConfig {

    @Bean
    public DataSource dataSource(DBProperties dbProperties) throws SQLException {
        PGConnectionPoolDataSource dataSource = new PGConnectionPoolDataSource();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl(dbProperties.url());
        dataSource.setUsername(dbProperties.user());
        dataSource.setPassword("");

        Properties properties = new Properties();
        properties.put("user", dbProperties.user());
        Connection conn = DriverManager.getConnection(
                dbProperties.url(), properties
        );
        conn.setSchema( dbProperties.schema());
        return conn;
    }
}

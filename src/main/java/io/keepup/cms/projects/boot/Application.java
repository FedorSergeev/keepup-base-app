package io.keepup.cms.projects.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * Launcher class
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class}, scanBasePackages = {"io.keepup",})
@EnableR2dbcRepositories(basePackages = {"io.keepup.cms.core.datasource.sql.repository"})
@EnableCaching
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class);
    }
}

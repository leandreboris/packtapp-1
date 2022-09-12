package com.packt.project.configuration;

import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Component
@Slf4j
public class LiquidbaseConfiguration {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SpringLiquibase liquibase() {

        String changelogFile = "classpath:/liquidbase/changelog/db-changelog-master.xml";

        SpringLiquibase liquidbase = new SpringLiquibase();
        liquidbase.setChangeLog(changelogFile);
        liquidbase.setDataSource(dataSource);
        liquidbase.setDropFirst(false);
        liquidbase.setShouldRun(true);

        Map<String, String> params = new HashMap<>();
        params.put("verbose", "true");
        liquidbase.setChangeLogParameters(params);

        log.info("--------------- PACKT liquidbase is loaded --------------------------");

        return liquidbase;
    }

}

package com.packt.project.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaAuditing
@PropertySource(value = { "classpath:application.properties" })
@Slf4j
@EnableJpaRepositories(
        basePackages = "com.packt.project",
        entityManagerFactoryRef = "localContainerEntityManagerFactoryBean",
        transactionManagerRef = "platformTransactionManager"
)
public class HibernateConfiguration {

    @Autowired
    private Environment environment;


    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("packt.jdbc.db.driver"));
        dataSource.setUrl(environment.getProperty("packt.jdbc.db.url"));
        dataSource.setUsername(environment.getProperty("packt.jdbc.db.username"));
        dataSource.setPassword(environment.getProperty("packt.jdbc.db.password"));

        log.info("--------------- PACKT datasource is loaded --------------------------");

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(getDataSource());
        entityManagerFactoryBean.setPersistenceUnitName("PACKT.DB");
        entityManagerFactoryBean.setPackagesToScan(new String[] {"com.packt.project.entity"});

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
                environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect",
                environment.getProperty("hibernate.dialect"));
        entityManagerFactoryBean.setJpaPropertyMap(properties);

        log.info("--------------- PACKT entity manager is loaded --------------------------");

        return entityManagerFactoryBean;

    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(localContainerEntityManagerFactoryBean().getObject());

        log.info("--------------- PACKT transaction manager is loaded --------------------------");

        return jpaTransactionManager;

    }


}

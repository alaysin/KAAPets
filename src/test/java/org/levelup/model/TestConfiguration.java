package org.levelup.model;


import org.levelup.web.AppJPAConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@ComponentScan(basePackages = "org.levelup.model", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = AppJPAConfiguration.class)
})
@EnableJpaRepositories(basePackages = "org.levelup.model")
@EnableTransactionManagement
@EnableWebMvc
@EnableAutoConfiguration
@EntityScan("org.levelup.model")
public class TestConfiguration {

//    @Bean
//    public EntityManagerFactory entityManagerFactory() {
//        return Persistence.createEntityManagerFactory("TestPersistenceUnit");
//    }
//    @Bean
//    public TransactionManager transactionManager(EntityManagerFactory factory) {
//        return new JpaTransactionManager(factory);
//    }
}

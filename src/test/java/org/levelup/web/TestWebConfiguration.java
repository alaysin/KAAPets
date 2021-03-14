package org.levelup.web;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManager;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.levelup.web", "org.levelup.model"}, excludeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {
                        AppJPAConfiguration.class,
                        PetsBookingApplication.class}
        )
})
public class TestWebConfiguration {
    @MockBean
    private EntityManager manager;
}
package org.levelup.web;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import javax.persistence.EntityManager;

@Configuration
@ComponentScan(basePackages = "org.levelup", excludeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = { AppJPAConfiguration.class, PetsBookingApplication.class }
        )
})
public class TestWebConfiguration {
    @MockBean
    private EntityManager manager;
}
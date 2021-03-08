package org.levelup.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.levelup.model.Pets;
import org.levelup.model.PetsDAO;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestWebConfiguration.class)
@AutoConfigureMockMvc
public class StartPageControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PetsDAO petsDAO;

    @Test
    public void testNoPets() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("subTitle", "Hello, %anonymous%, you are using my Pet Booking Service!"))
                .andExpect(model().attribute("pets", Collections.emptyList()))
                .andExpect(model().attribute("isAdmin", false))
                .andExpect(model().attribute("isLoggedIn", false));
    }

    @Test
    public void testNoPetsWithLoggedInAdmin() throws Exception {
        UserSession userSession = new UserSession();
        userSession.setUserLogin("admin");
        userSession.setAdmin(true);
        mvc.perform(get("/").sessionAttr("user-session", userSession))
                .andExpect(status().isOk())
                .andExpect(model().attribute("subTitle", "Hello, admin, you are using my Pet Booking Service!"))
                .andExpect(model().attribute("pets", Collections.emptyList()))
                .andExpect(model().attribute("isAdmin", true))
                .andExpect(model().attribute("isLoggedIn", true));
    }

    @Test
    public void testHaveSomePets() throws Exception {
        List<Pets> expectedPets = Arrays.asList(
                new Pets("Pet1", "Breed1"),
                new Pets("Pet2", "Breed2")
        );
        Mockito.when(petsDAO.findAll()).thenReturn(expectedPets);

        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("subTitle", "Hello, %anonymous%, you are using my Pet Booking Service!"))
                .andExpect(model().attribute("pets", expectedPets));
    }

}
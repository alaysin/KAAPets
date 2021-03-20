package org.levelup.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.levelup.model.User;
import org.levelup.model.UsersDAO;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.matches;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestWebConfiguration.class)
@AutoConfigureMockMvc
public class RegistrationFormControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsersDAO usersDAO;

    @MockBean
    private EntityTransaction tx;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void registration() throws Exception {
        User added = new User("login", "password");
        Mockito.when(entityManager.getTransaction()).thenReturn(tx);
        Mockito.when(usersDAO.saveNewUserWithName(matches("login"), matches("password"), matches("name")))
                .thenReturn(added);

        mvc.perform(post("/registration")
                .param("userLogin", "login")
                .param("password", "password")
                .param("userName", "name")
                .with(csrf())
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("userLogin", "login"));

        Mockito.verify(usersDAO, Mockito.atLeast(1))
                .saveNewUserWithName("login", "password", "name");
    }

}
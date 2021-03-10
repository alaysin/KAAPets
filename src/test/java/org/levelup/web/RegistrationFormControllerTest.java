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
        Mockito.when(usersDAO.saveNewUser(matches("login"), matches("password")))
                .thenReturn(added);
        UserSession userSession = new UserSession();

        mvc.perform(post("/registration")
                .param("login", "login")
                .param("password", "password")
                .sessionAttr("user-session", userSession)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("login", "login"));

        Mockito.verify(usersDAO, Mockito.atLeast(1))
                .saveNewUser("login", "password");
    }

}
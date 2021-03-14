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

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.matches;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestWebConfiguration.class)
@AutoConfigureMockMvc
public class AddFormControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PetsDAO petsDAO;

    @MockBean
    private EntityTransaction tx;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void add() throws Exception {
        Pets added = new Pets("NicknameOne", "BreedOne");
        Mockito.when(entityManager.getTransaction()).thenReturn(tx);
        Mockito.when(petsDAO.saveNewPetWithoutBD(matches("NicknameOne"), matches("BreedOne")))
                .thenReturn(added);

        UserSession userSession = new UserSession();
        userSession.setUserLogin("admin");
        userSession.setAdmin(true);

        mvc.perform(post("/add")
                .param("petsName", "NicknameOne")
                .param("petsBreed", "BreedOne")
                .sessionAttr("user-session", userSession)
        )
                .andExpect(status().isOk())
                .andExpect(model().attribute("petsName", "NicknameOne"));

        Mockito.verify(petsDAO, Mockito.atLeast(1))
                .saveNewPetWithoutBD("NicknameOne", "BreedOne");

    }
}
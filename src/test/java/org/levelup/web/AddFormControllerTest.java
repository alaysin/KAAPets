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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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


        DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    this.dateOfBirth = LocalDate.parse(date, formatter);
        LocalDate date = LocalDate.now().minusDays(10);
        Pets add = new Pets("NicknameOne", "BreedOne", date);
        Pets added = new Pets("NicknameOne", "BreedOne");
        System.out.println("oy wse");

/*
        Mockito.when(entityManager.getTransaction()).thenReturn(tx);
        Mockito.when(petsDAO.saveNewPet(matches("NicknameOne"), matches("BreedOne"), date))
                //LocalDate.parse("11/11/2020", DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                .thenReturn(add);
//        System.out.println("-----");
//        UserSession userSession = new UserSession();
//        userSession.setUserLogin("admin");
//        userSession.setAdmin(true);

        System.out.println("-----");
        mvc.perform(post("/admin/pets/add")
                .with(user("admin").roles("ADMIN"))
                .param("petsName", "NicknameOne")
                .param("petsBreed", "BreedOne")
                
                .param("petsBirthDay", "11/11/2020")
                .with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(model().attribute("petsName", "NicknameOne"));

        Mockito.verify(petsDAO, Mockito.atLeast(1))
                .saveNewPetWithoutBD("NicknameOne", "BreedOne");
*/
    }

    //Добавить секьюрити, пересмотреть тест

}
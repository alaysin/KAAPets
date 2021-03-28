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
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        LocalDate date = LocalDate.now();
        String one = "NicknameOne";
        String two = "BreedOne";
        Pets add = new Pets("NicknameOne", "BreedOne", date);


        Mockito.when(entityManager.getTransaction()).thenReturn(tx);
        Mockito.when(petsDAO.saveNewPet(one, two, date))
                .thenReturn(add);


        mvc.perform(post("/admin/pets/add")
                .with(user("admin").roles("ADMIN"))
                .param("petsName", "NicknameOne")
                .param("petsBreed", "BreedOne")
                .param("petsBirthDay", "2021-03-25")
                .with(csrf())
        )
                .andExpect(status().isOk())
                .andExpect(model().attribute("petsName", "NicknameOne"));

        Mockito.verify(petsDAO, Mockito.atLeast(1))
                .saveNewPet(one, two, date);


    }

    //Добавить секьюрити, пересмотреть тест

}
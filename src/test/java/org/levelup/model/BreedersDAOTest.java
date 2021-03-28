package org.levelup.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class BreedersDAOTest {

//    @PersistenceContext
//    private EntityManager manager;

    @Autowired
    private BreedersDAO breedersDAO;

    @Before
    public void configure() {
        Breeder breeder = new Breeder("Sanych");
        breedersDAO.save(breeder);
    }

    @Test
    public void findByName() {
        assertNull(breedersDAO.findByBreederName("not"));

        Breeder found = breedersDAO.findByBreederName("Sanych");
        assertNotNull(found);
        assertEquals("Sanych", found.getBreederName());
    }

    @Test
    public void saveNewBreeder() {
        Breeder breeder = breedersDAO.saveNewBreeder("test");
        assertTrue(breedersDAO.findById(breeder.getId()).isPresent());
    }


}
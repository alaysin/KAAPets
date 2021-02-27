package org.levelup.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BreedersDAOTest {

    @Autowired
    private EntityManager manager;

    @Autowired
    private BreedersDAO breedersDAO;

    @Before
    public void configure(){
        Breeder breeder = new Breeder("Sanych");

        manager.getTransaction().begin();

        manager.persist(breeder);

        manager.getTransaction().commit();
    }

    @Test
    public void findByName() {
        assertNull(breedersDAO.findByName("not"));

        Breeder found = breedersDAO.findByName("Sanych");
        assertNotNull(found);
        assertEquals("Sanych", found.getBreederName());
    }
}
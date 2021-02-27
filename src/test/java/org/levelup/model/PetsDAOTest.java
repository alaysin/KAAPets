package org.levelup.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode =  DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PetsDAOTest {

    @Autowired
    private EntityManager manager;

    @Autowired
    private PetsDAO petsDAO;

    private LocalDate date = LocalDate.of(2020,1,1);

    @Before
    public void configure(){

        Breeder breeder = new Breeder("Sanych");

        Pets pet = new Pets("Tabi", "Munchkin", date, breeder);

        User user = new User("loginTest", "password", false, "Alex");

        pet.setNewOwner(user);

        manager.getTransaction().begin();
        manager.persist(breeder);
        manager.persist(user);
        manager.persist(pet);
        manager.getTransaction().commit();
    }

    @Test
    public void testFindingByPetsNickname() {
        Assert.assertNull(petsDAO.findingByPetsNickname("Test_test"));
        Pets found = petsDAO.findingByPetsNickname("Tabi");
        assertNotNull(found);
        assertEquals("Tabi", found.getNickname());
    }
    @Test
    public void testFindingByPetsBreed(){
        Assert.assertNull(petsDAO.findingByPetsBreed("breed"));
        Pets found = petsDAO.findingByPetsBreed("Munchkin");
        assertNotNull(found);
        assertEquals("Munchkin", found.getBreed());
    }

    @Test
    public void findingByPetsNickname() {
        assertTrue(petsDAO.findingByPetsBreeder("Sanych11").isEmpty());
        assertEquals("Tabi", petsDAO.findingByPetsBreeder("Sanych").get(0).getNickname());
    }


    @Test
    public void testFindingByPetsNickname1() {

        assertTrue(petsDAO.findingByPetsNewOwner("test").isEmpty());
        assertEquals("Tabi", petsDAO.findingByPetsNewOwner("Alex").get(0).getNickname());

    }
}
package org.levelup;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class PetsDAOTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private PetsDAO petsDAO;
    private LocalDate date = LocalDate.of(2020,1,1);

    @Before
    public void configure(){
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        petsDAO = new PetsDAO(manager);

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
    @After
    public void cleanup(){
        if(manager != null){
            manager.close();
        }
        if (factory != null){
            factory.close();
        }
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
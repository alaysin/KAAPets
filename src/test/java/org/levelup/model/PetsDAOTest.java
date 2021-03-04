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
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PetsDAOTest {

    @Autowired
    private EntityManager manager;

    @Autowired
    private PetsDAO petsDAO;

    private LocalDate date = LocalDate.of(2020, 1, 1);

    @Before
    public void configure() {

        Breeder breeder = new Breeder("Sanych");

        Pets pet = new Pets("Tabi", "Munchkin", date, breeder);
        Pets pet2 = new Pets("Tati", "Sphynx", date.plusDays(10), breeder);

        User user = new User("loginTest", "password", false, "Alex");

        pet.setNewOwner(user);
        pet.setReserverd(true);
        pet2.setReserverd(true);

        manager.getTransaction().begin();
        manager.persist(breeder);
        manager.persist(user);
        manager.persist(pet);
        manager.persist(pet2);
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
    public void testFindingByPetsBreed() {
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

    @Test
    public void findingByReserved() {
        assertTrue(petsDAO.findingByReserved(false).isEmpty());
        assertEquals("Tabi", petsDAO.findingByReserved(true).get(0).getNickname());
    }

    @Test
    public void findByBirthDate() {
        List<Pets> found = petsDAO.findByBirthDate(date);
        assertEquals("Tabi", found.get(0).getNickname());
        List<Pets> found2 = petsDAO.findByBirthDate(date.plusDays(9));
        assertEquals(1, found2.size());
    }
}
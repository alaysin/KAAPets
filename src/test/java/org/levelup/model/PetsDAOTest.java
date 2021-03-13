package org.levelup.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@SpringBootTest
public class PetsDAOTest {

    @PersistenceContext
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

        manager.persist(breeder);
        manager.persist(user);
        petsDAO.save(pet);
        petsDAO.save(pet2);
    }

    @Test
    public void testFindingByPetsNickname() {
        Assert.assertNull(petsDAO.findByNickname("Test_test"));
        Pets found = petsDAO.findByNickname("Tabi");
        assertNotNull(found);
        assertEquals("Tabi", found.getNickname());
    }

    @Test
    public void testFindingByPetsBreed() {
        Assert.assertNull(petsDAO.findByBreed("breed"));
        Pets found = petsDAO.findByBreed("Munchkin");
        assertNotNull(found);
        assertEquals("Munchkin", found.getBreed());
    }

    @Test
    public void findingByPetsNickname() {
        assertTrue(petsDAO.findByBreeder_BreederName("Sanych11").isEmpty());
        assertEquals("Tabi", petsDAO.findByBreeder_BreederName("Sanych").get(0).getNickname());
    }


    @Test
    public void testFindingByPetsNickname1() {

        assertTrue(petsDAO.findByNewOwner_Name("test").isEmpty());
        assertEquals("Tabi", petsDAO.findByNewOwner_Name("Alex").get(0).getNickname());

    }

    @Test
    public void findingByReserved() {
        assertTrue(petsDAO.findByIsReserved(false).isEmpty());
        assertEquals("Tabi", petsDAO.findByIsReserved(true).get(0).getNickname());
    }

    @Test
    public void findByBirthDate() {
        List<Pets> found = petsDAO.findByBirthDay(date);
        assertEquals("Tabi", found.get(0).getNickname());
        List<Pets> found2 = petsDAO.findByBirthDay(date.plusDays(10));
        assertEquals(1, found2.size());
    }

    @Test
    public void saveNewPet() {
        Pets added = petsDAO.saveNewPet("Test", "Test", date.minusDays(10));

//        manager.refresh(added);
    }

    @Test
    public void saveNewPetWithoutBD() {
        Pets added = petsDAO.saveNewPetWithoutBD("Test", "Test");

//        manager.refresh(added);
    }
}
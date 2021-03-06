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
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class UsersDAOTest {

//    @PersistenceContext
//    private EntityManager manager;

    @Autowired
    private UsersDAO usersDAO;

    @Before
    public void configure() {
        User newUser = new User("login777", "pass", false, "sanych");
        usersDAO.save(newUser);
    }

    @Test
    public void findByLogin() {
        assertNull(usersDAO.findByLogin("non existing user"));

        User found = usersDAO.findByLogin("login777");
        assertNotNull(found);
        assertEquals("login777", found.getLogin());
    }

    @Test
    public void findByLoginAndPassword() {
        assertNull(usersDAO.findByLoginAndPassword("some user", "ppp"));
        assertNull(usersDAO.findByLoginAndPassword("login777", "pass1"));

        User found = usersDAO.findByLoginAndPassword("login777", "pass");
        assertNotNull(found);
        assertEquals("login777", found.getLogin());
        assertEquals("pass", found.getPassword());
    }

    @Test
    public void findByIsAdmin() {
        assertEquals("login777", usersDAO.findByIsAdmin(false).get(0).getLogin());
        assertTrue(usersDAO.findByIsAdmin(true).isEmpty());
    }

    @Test
    public void saveNewUserWithName() {
        User added = usersDAO.saveNewUserWithName("Login", "Password", "Name");
    }

    @Test
    public void saveNewUser() {
        User added = usersDAO.saveNewUser("Login", "Password");
    }


}
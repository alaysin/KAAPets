package org.levelup.model;

import org.junit.Test;
import org.levelup.model.Breeder;
import org.levelup.model.Pets;
import org.levelup.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class EntityManagerTest {
    private EntityManagerFactory factory;
    private EntityManager manager;

    @Test
    public void smokeTest() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            User user = new User("test", "aaa", true, "Test1");

            manager.persist(user);

            Breeder breeder = new Breeder("Sanych");
            manager.persist(breeder);

            Pets pet = new Pets("Tabi", "Munchkin", LocalDate.of(2020, 1, 1), breeder);
            manager.persist(pet);


            manager.getTransaction().commit();


        } finally {
            manager.close();
            factory.close();
        }
    }
}
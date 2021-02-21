package org.levelup;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class EntityManagerTest {
    private EntityManagerFactory factory;
    private EntityManager manager;

    @Test
    public void smokeTest(){
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();

        try {
            manager.getTransaction().begin();
            User user = new User ("test", "aaa", true);
            manager.persist(user);
            manager.getTransaction().commit();

            manager.getTransaction().begin();
            Pets pet = new Pets ("Tabi", "Munchkin", LocalDate.of(2020,1,1), user);
            manager.persist(pet);
            manager.getTransaction().commit();


        }finally{
            manager.close();
            manager.clear();
        }
    }
}
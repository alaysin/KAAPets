package org.levelup.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class PetsDAO {
    private EntityManager manager;

    @Autowired
    public PetsDAO(@Autowired EntityManager manager) {
        this.manager = manager;
    }

    public Pets findingByPetsNickname(String nickname) {
        try {
            return manager.createQuery(
                    "from Pets where nickname = :nickname",
                    Pets.class)
                    .setParameter("nickname", nickname)
                    .getSingleResult();
        } catch (NoResultException notFound) {
            return null;
        }
    }

    public Pets findingByPetsBreed(String breed) {
        try {
            return manager.createQuery(
                    "from Pets where breed = :breed",
                    Pets.class)
                    .setParameter("breed", breed)
                    .getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        }
    }

    public List<Pets> findingByReserved(boolean isReserved) {
        try {
            return manager.createQuery(
                    "from Pets where isReserved =:isReserved",
                    Pets.class)
                    .setParameter("isReserved", isReserved)
                    .getResultList();
        } catch (NoResultException noResultException) {
            return null;
        }
    }


    public List<Pets> findingByPetsBreeder(String breederName) {

        return manager.createQuery(
                "from Pets p where p.breeder.breederName = :breederName",
                Pets.class)
                .setParameter("breederName", breederName)
                .getResultList();
    }

    public List<Pets> findingByPetsNewOwner(String name) {

        return manager.createQuery(
                "from Pets u where u.newOwner.name = :name",
                Pets.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Pets> findByBirthDate(LocalDate referenceDate) {
        return manager.createQuery(
                "from Pets where birthDay <= :referenceDate",
                Pets.class)
                .setParameter("referenceDate", referenceDate)
                .getResultList();
    }

    public List<Pets> findAll(){
        return manager.createQuery("from Pets", Pets.class).getResultList();
    }

}

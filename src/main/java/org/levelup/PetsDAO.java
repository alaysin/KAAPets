package org.levelup;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class PetsDAO {
    private EntityManager manager;

    public PetsDAO(EntityManager manager) {
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

    public Pets findingByPetsBreed(String breed){
        try {
            return manager.createQuery(
                    "from Pets where breed = :breed",
                    Pets.class)
                    .setParameter("breed", breed)
                    .getSingleResult();
        }catch (NoResultException noResultException){
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

    public List<Pets> findingByPetsNewOwner(String name){

        return manager.createQuery(
                "from Pets u where u.newOwner.name = :name",
                Pets.class)
                .setParameter("name", name)
                .getResultList();
    }


}

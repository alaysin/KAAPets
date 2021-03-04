package org.levelup.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class BreedersDAO {
    private EntityManager manager;

    public BreedersDAO(@Autowired EntityManager manager) {
        this.manager = manager;
    }

    public Breeder findByName(String breederName) {
        try {
            return manager.createQuery(
                    "from Breeder where breederName = :breederName", Breeder.class)
                    .setParameter("breederName", breederName)
                    .getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        }
    }
//
//    public Breeder findByPetName(String nickname) {
//        try {
//
//
//            return manager.createQuery(
//                     "from Breeder b, Pets p where b.pets.nickname = :nickname", Breeder.class)
//                    .setParameter("nickname", nickname)
//                    .getResultList();
//        } catch (Exception e) {
//            return null
//        }
//    }

}

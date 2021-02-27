package org.levelup.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class BreedersDAO {
    private EntityManager manager;

    @Autowired
    public BreedersDAO(EntityManager manager) {
        this.manager = manager;
    }

    public Breeder findByName(String breederName) {
        try {
            return manager.createQuery(
                    "from Breeder where breederName = :breederName", Breeder.class)
                    .setParameter("breederName", breederName)
                    .getSingleResult();
        }catch (NoResultException noResultException){
            return null;
        }
    }

}

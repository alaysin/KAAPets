package org.levelup.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface BreedersDAO extends JpaRepository<Breeder, Integer> {

    public Breeder findByBreederName(String breederName);

    @Transactional
    public default Breeder saveNewBreeder(String breederName){
        Breeder breeder = new Breeder(breederName);
        save(breeder);
        return breeder;
    }
}

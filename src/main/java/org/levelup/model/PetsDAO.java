package org.levelup.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PetsDAO extends JpaRepository<Pets, Integer> {


    public Pets findByNickname(String nickname);

    public Pets findByBreed(String breed);

    public List<Pets> findByIsReserved(boolean isReserved);

    @Query("from Pets p where p.breeder.breederName = :breederName")
    public List<Pets> findByBreeder_BreederName(@Param("breederName") String breederName);

    @Query("from Pets u where u.newOwner.name = :name")
    public List<Pets> findByNewOwner_Name(@Param("name") String name);


    public List<Pets> findByBirthDay(LocalDate birthDate);

    @Transactional
    public default Pets saveNewPet (String nickname, String breed, LocalDate birthday) {
        Pets newPet = new Pets(nickname, breed, birthday);
        save(newPet);
        return newPet;
    }

    @Transactional
    public default Pets saveNewPetWithoutBD (String nickname, String breed) {
        Pets newPet = new Pets(nickname, breed);
        save(newPet);
        return newPet;
    }

}

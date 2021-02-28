package org.levelup.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Pets {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, updatable = false, length = 50)
    private String nickname;

    @Column(nullable = false, updatable = false, length = 50)
    private String breed;

    @Column(nullable = false, updatable = false, length = 50)
    private LocalDate birthDay;

    @Column(nullable = false, updatable = true)
    private boolean isReserved;

    @Enumerated
    private PetStatus status = PetStatus.READYFORBOOKING;

    @ManyToOne
    private Breeder breeder;

    @ManyToOne
    private User newOwner;


    public Pets() {

    }

    public Pets(String nickname, String breed, LocalDate birthDay, Breeder breeder) {
        this.nickname = nickname;
        this.breed = breed;
        this.birthDay = birthDay;
        this.breeder = breeder;
        this.isReserved = false;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public Breeder getBreeder() {
        return breeder;
    }

    public void setBreeder(Breeder breeder) {
        this.breeder = breeder;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserverd(boolean isReserved) {
        this.isReserved = isReserved;
    }

    public User getNewOwner() {
        return newOwner;
    }

    public void setNewOwner(User newOwner) {
        this.newOwner = newOwner;
    }

    public PetStatus getStatus() {
        return status;
    }

    public void setStatus(PetStatus status) {
        this.status = status;
    }
}

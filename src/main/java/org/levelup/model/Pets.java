package org.levelup.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Entity
public class Pets {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, updatable = false, length = 50)
    @javax.validation.constraints.NotBlank
    @NotBlank
    private String nickname;

    @NotBlank
    @Column(nullable = false, updatable = false, length = 50)
    private String breed;

    @Column(nullable = false, updatable = false, length = 50)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Past
    private LocalDate birthDay  = LocalDate.now();

    @Column(nullable = false, updatable = true)

    private boolean isReserved;

    @Column(updatable = false, length = 50)
    private LocalDate reservationDate;// = LocalDate.now();

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

    public Pets(String nickname, String breed, LocalDate birthDay) {
        this.nickname = nickname;
        this.breed = breed;
        this.birthDay = birthDay;
        this.isReserved = false;
//        this.breeder = new Breeder("Default Breeder name");
    }

    public Pets(String nickname, String breed) {
        this.nickname = nickname;
        this.breed = breed;
        this.birthDay = LocalDate.now().minusMonths(10);
        this.isReserved = false;
//        this.breeder = new Breeder("Default Breeder name");

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

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }
}

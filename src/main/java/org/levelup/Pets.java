package org.levelup;

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
    private Boolean isReserverd;

    @ManyToOne
    private User owner;


    public Pets() {

    }

    public Pets(String nickname, String breed, LocalDate birthDay, User owner) {
        this.nickname = nickname;
        this.breed = breed;
        this.birthDay = birthDay;
        this.owner = owner;
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

    public Boolean getReserverd() {
        return isReserverd;
    }

    public void setReserverd(Boolean reserverd) {
        isReserverd = reserverd;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}

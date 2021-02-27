package org.levelup.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Breeder {

    @Id
    @GeneratedValue
    private int id;

    @Column (nullable = false, length = 100)
    private String breederName;

    @OneToMany (mappedBy = "breeder")
    private List<Pets> pets;

    public Breeder() {
    }

    public Breeder(String breederName) {
        this.breederName = breederName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBreederName() {
        return breederName;
    }

    public void setBreederName(String breederName) {
        this.breederName = breederName;
    }

    public List<Pets> getPets() {
        return pets;
    }

    public void setPets(List<Pets> pets) {
        this.pets = pets;
    }
}

package org.levelup.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
//@Table(name = "Users")
@NamedQueries({
        @NamedQuery(name = "findByIsAdmin", query = "from User where isAdmin = :isAdmin"),
})
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true, length = 100)
    @NotBlank
    private String login;

    @Column(nullable = false, unique = false, length = 100)
    @NotBlank
    private String password;

    @Column(nullable = false)
    private boolean isAdmin;

    @Column(length = 50)
    private String name;


    @OneToMany(mappedBy = "newOwner")
    private List<Pets> pets;


    public User() {
    }

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.isAdmin = false;
    }

    public User(String login, String password, boolean isAdmin, String name) {
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
        this.name = name;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.isAdmin = false;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pets> getPets() {
        return pets;
    }

    public void setPets(List<Pets> pets) {
        this.pets = pets;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}

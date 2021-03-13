package org.levelup.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface UsersDAO extends JpaRepository<User, Integer> {

    public User findByLogin(String login);

    public User findByLoginAndPassword(String login, String password);

    @Query(name = "findByIsAdmin")
    public List<User> findByIsAdmin(boolean isAdmin);

    public default User saveNewUserWithName (String login, String password, String name) {
        User newUser = new User(login, password, name);
        save(newUser);
        return newUser;
    }

    public default User saveNewUser (String login, String password) {
        User newUser = new User(login, password);
        save(newUser);
        return newUser;
    }


}

package org.levelup.model;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class UsersDAO {
    private EntityManager manager;

    public UsersDAO(EntityManager manager) {
        this.manager = manager;
    }

    public User findByLogin(String login) {
        try {
            return manager.createQuery(
                    "from User where login = :loginParameter",
                    User.class)
                    .setParameter("loginParameter", login)
                    .getSingleResult();
        } catch (NoResultException notFound) {
            return null;
        }
    }

    public User findByLoginAndPassword(String login, String password) {
        try {
            return manager.createQuery(
                    "from User where login = :loginParameter and password = :password",
                    User.class)
                    .setParameter("loginParameter", login)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException notFound) {
            return null;
        }

    }

    public List<User> findByIsAdmin(boolean isAdmin) {
        return manager.createNamedQuery("findByIsAdmin", User.class)
                .setParameter("isAdmin", isAdmin)
                .getResultList();
    }


}

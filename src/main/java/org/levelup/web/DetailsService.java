package org.levelup.web;

import org.levelup.model.UsersDAO;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DetailsService implements UserDetailsService {

    private final UsersDAO usersDAO;
    private final PasswordEncoder encoder;

    public DetailsService(UsersDAO usersDAO, PasswordEncoder encoder) {
        this.usersDAO = usersDAO;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.equals("admin")) {
            return User.withUsername("admin")
                    .password(encoder.encode("admin"))
                    .roles("ADMIN", "USER")
                    .build();
        }
        org.levelup.model.User found = usersDAO.findByLogin(username);

        if (found == null) {
            throw new UsernameNotFoundException( "User "+ username + "not found!");
        }

        return User.withUsername(username)
                .password(found.getPassword())
                .roles("USER")
                .build();
    }
}

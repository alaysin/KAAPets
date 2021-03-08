package org.levelup.web;

import org.levelup.model.User;
import org.levelup.model.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;

@Controller
@SessionAttributes("user-session")
public class RegistrationFormController {
    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private EntityManager manager;


    @PostMapping("/registration")
    public String registration(
            Model model,
            @RequestParam String login,
            @RequestParam String password
            //@RequestParam String name
    ) {
        User registered;
        manager.getTransaction().begin();
        try {
            registered = usersDAO.saveNewUser(login, password);
            manager.getTransaction().commit();
        } finally {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
        }
        model.addAttribute("login", registered.getLogin());
        return "index";
    }


    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        model.addAttribute("users", new User());

        return "registration";
    }

//    @PostMapping ("/register")
//    public RedirectView submitRegisterForm (
//            @RequestParam String login,
//            @RequestParam String password,
//            @ModelAttribute("user-session") UserSession session
//    ) {
//        return new RedirectView("/");
//    }

}

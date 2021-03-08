package org.levelup.web;

import org.levelup.model.User;
import org.levelup.model.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityManager;

@Controller
@SessionAttributes("user-session")
public class RegisterFormController {
    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private EntityManager manager;


    @PostMapping("/register")
    public String submitRegisterForm(
            Model model,
            @RequestParam String login,
            @RequestParam String password
            //@RequestParam String name
    ) {
        User added;
        manager.getTransaction().begin();
        try {
            added = usersDAO.saveNewUser(login, password);
            manager.getTransaction().commit();
        } finally {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
        }
//        model.addAttribute("login", added.getLogin());
        return "register";
    }


    @GetMapping("/register")
    public String showRegisterForm(Model model) {
//        model.addAttribute("users", new User());

        return "register";
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

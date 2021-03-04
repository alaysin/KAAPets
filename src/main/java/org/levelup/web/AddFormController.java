package org.levelup.web;

import org.levelup.model.BreedersDAO;
import org.levelup.model.PetsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.persistence.EntityManager;

@Controller
@SessionAttributes("user-session")
public class AddFormController {

    @Autowired
    private PetsDAO petsDAO;

    @Autowired
    private EntityManager manager;

    @PostMapping("/add")
    public String add(Model model, @RequestParam String nickname) {
        model.addAttribute("nickname", nickname);
        return "added";
    }
}

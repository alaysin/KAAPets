package org.levelup.web;

import org.levelup.model.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class StartPageController {

    private final PetsDAO petsDAO;

    public StartPageController(PetsDAO petsDAO) {
        this.petsDAO = petsDAO;
    }

    @GetMapping("/")
    public String index(
            Model model,
            @RequestParam(defaultValue = "10") int count,
            Authentication authentication
    ) {
        String subTitle;
        boolean isAdmin = false;
        if (authentication == null) {
            subTitle = "Hello, %anonymous%, you are using my Pet Booking Service!";
        } else {
            subTitle = "Hello, " + authentication.getName() + ", you are using my Pet Booking Service!";
            isAdmin = authentication.getAuthorities().contains(
                    new SimpleGrantedAuthority("ROLE_ADMIN")
            );
        }

        model.addAttribute("pets", loadPets(10));
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isLoggedIn", authentication != null);
        model.addAttribute("subTitle", subTitle);
        model.addAttribute("title", "Pet Booking Service");
        model.addAttribute("error", "You are here, cause something goes wrong.");

        model.addAttribute("pets", loadPets(count));

        return "index";
    }

    private List<Pets> loadPets(int count) {
        return petsDAO.findAll();
    }

}

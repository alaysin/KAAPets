package org.levelup.web;

import org.levelup.model.Breeder;
import org.levelup.model.Pets;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StartPageController {
    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "10") int count) {
        model.addAttribute("title", "Hello, you are using my Pet Booking Service.");
        model.addAttribute("pets", loadPets(count));
        return "index";
    }

    private List<Pets> loadPets(int count) {
        ArrayList<Pets> pets = new ArrayList<>();
        LocalDate date = LocalDate.of(2019, 1, 1);
        Breeder breeder = new Breeder("test");
        for (int i = 0; i < count; i++) {
            pets.add(new Pets("nickname" + i, "breed" + i, date.plusDays(i), breeder));

        }
        return pets;
    }
}

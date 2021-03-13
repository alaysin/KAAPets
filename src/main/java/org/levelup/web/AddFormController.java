package org.levelup.web;

import org.levelup.model.BreedersDAO;
import org.levelup.model.Pets;
import org.levelup.model.PetsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@SessionAttributes("user-session")
public class AddFormController {
    @ModelAttribute
    LocalDate initLocalDate() {
        return LocalDate.now();
    }

    @Autowired
    private PetsDAO petsDAO;


    @PostMapping("/add")
    @Transactional
    public String add(Model model,
                      @RequestParam String nickname,
                      @RequestParam String breed,
//                      @RequestParam
//                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//                      @ModelAttribute LocalDate birthDay
                      @ModelAttribute("user-session") UserSession session
    ) {
        if (session.getUserLogin() == null || !session.isAdmin()) {
            throw new RuntimeException("User is not admin");
        }

        Pets added = petsDAO.saveNewPetWithoutBD(nickname, breed);

//            added = petsDAO.saveNewPet(nickname, breed, birthDay);




        model.addAttribute("nickname", added.getNickname());
        model.addAttribute("breed", added.getBreed());
        return "added";
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException{
                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }

            @Override
            public String getAsText() throws IllegalArgumentException {
                return DateTimeFormatter.ofPattern("yyyy-MM-dd").format((LocalDate) getValue());
            }
        });
    }
}

package org.levelup.web;

import org.hibernate.exception.ConstraintViolationException;
import org.levelup.model.BreedersDAO;
import org.levelup.model.Pets;
import org.levelup.model.PetsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
//@SessionAttributes("user-session")
public class AddPetsController {
    @ModelAttribute
    LocalDate initLocalDate() {
        return LocalDate.now();
    }

    @Autowired
    private PetsDAO petsDAO;

    @GetMapping("/admin/pets/add")
    public String viewAddForm(
            Model model,
            @ModelAttribute("form") AddPetsForm form,
            BindingResult bindingResult
    ) {
        model.addAttribute("form", form);
        model.addAttribute("bindingResult", bindingResult);
        return "addPet";
    }

    @PostMapping("/admin/pets/add")
    //@Transactional
    public String add(Model model,
                      @Valid @ModelAttribute("form") AddPetsForm form,
//                      @RequestParam
//                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//                      @ModelAttribute LocalDate birthDay
                      //@ModelAttribute("user-session") UserSession session,
                      BindingResult bindingResult
    ) {
//        model.addAttribute("form", form);
//        model.addAttribute("bindingResult", bindingResult);

//        if (session.getUserLogin() == null || !session.isAdmin()) {
//            throw new RuntimeException("User is not admin");
//        }

        if (bindingResult.hasErrors()) {
            return "addPet";
        }

        Pets added;

//        try {
//            added = petsDAO.saveNewPetWithoutBD(form.getPetsName(), form.getPetsBreed());
//        } catch (ConstraintViolationException constraintViolationException) {
//            bindingResult.addError(new FieldError("form",
//                    "petsNickname", "Nickname is not correct"));
//            return "addPet";
//        }

        try {
            added = petsDAO.saveNewPet(form.getPetsName(), form.getPetsBreed(), form.getPetsBirthDate());
        } catch (ConstraintViolationException constraintViolationException) {
            bindingResult.addError(new FieldError("form",
                    "petsNickname", "Nickname is not correct"));
            return "addPet";
        }

        model.addAttribute("petsName", added.getNickname());
        model.addAttribute("petsBreed", added.getBreed());
        model.addAttribute("petsBirthDate", added.getBirthDay());
        return "added";
    }

    @GetMapping("/admin/pets/{id}/reserve")
    public ModelAndView reserve(@PathVariable int id) {
      Pets pets = petsDAO.findById(id).get();
        pets.setReserve();
        petsDAO.save(pets);

        return new ModelAndView("redirect:/");
    }


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            }

            @Override
            public String getAsText() throws IllegalArgumentException {
                return DateTimeFormatter.ofPattern("yyyy-MM-dd").format((LocalDate) getValue());
            }
        });
    }
}

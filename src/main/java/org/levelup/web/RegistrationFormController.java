package org.levelup.web;

import org.hibernate.exception.ConstraintViolationException;
import org.levelup.model.User;
import org.levelup.model.UsersDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class RegistrationFormController {
    @ModelAttribute
    LocalDate initLocalDate() {
        return LocalDate.now();
    }

    @Autowired
    private UsersDAO usersDAO;


    @PostMapping("/registration")
    @Transactional
    public String registration(
            Model model,
            @Valid AddUserForm form,
            @ModelAttribute("user-session") UserSession session,
            BindingResult bindingResult
    ) {
        model.addAttribute("form", form);
        model.addAttribute("bindingResult", bindingResult);


        if (bindingResult.hasErrors()) {
            return "registration";
        }

        User registered;

        try {
            registered = usersDAO.saveNewUserWithName(form.getUserLogin(), form.getPassword(), form.getUserName());
        } catch (ConstraintViolationException constraintViolationException) {
            bindingResult.addError(new FieldError("form",
                    "userLogin", "Login is not available"
            ));
            return "registration";
        }
        model.addAttribute("userLogin", registered.getLogin());
        model.addAttribute("userPassword", registered.getPassword());
        model.addAttribute("isAdmin", registered.isAdmin());
        model.addAttribute("userName", registered.getName());
        return "/login" ;
    }

    @GetMapping("/registration")
    public String registration(
            Model model,
            @ModelAttribute AddUserForm form,
            BindingResult bindingResult
    ) {
        model.addAttribute("form", form);
        model.addAttribute("bindingResult", bindingResult);

        return "registration";
    }

}

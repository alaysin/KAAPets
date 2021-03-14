package org.levelup.web;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class AddPetsForm {

    @NotBlank
    @Length(max = 50)
    private String petsName;

    @NotBlank
    @Length(max = 50)
    private String petsBreed;
    private LocalDate petsBirthDate;

    public AddPetsForm() {
    }

    public AddPetsForm(String petsName, String petsBreed) {
        this.petsName = petsName;
        this.petsBreed = petsBreed;
    }

    public AddPetsForm(String petsName, String petsBreed, LocalDate petsBirthDate) {
        this.petsName = petsName;
        this.petsBreed = petsBreed;
        this.petsBirthDate = petsBirthDate;
    }

    public String getPetsName() {
        return petsName;
    }

    public void setPetsName(String petsName) {
        this.petsName = petsName;
    }

    public String getPetsBreed() {
        return petsBreed;
    }

    public void setPetsBreed(String petsBreed) {
        this.petsBreed = petsBreed;
    }

    public LocalDate getPetsBirthDate() {
        return petsBirthDate;
    }

    public void setPetsBirthDate(LocalDate petsBirthDate) {
        this.petsBirthDate = petsBirthDate;
    }
}

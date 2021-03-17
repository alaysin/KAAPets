package org.levelup.web;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

public class AddPetsForm {

    @NotBlank
    @Length(max = 50)
    private String petsName;

    @NotBlank
    @Length(max = 50)
    private String petsBreed;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @javax.validation.constraints.PastOrPresent
    private LocalDate petsBirthDate = LocalDate.now();

    private boolean isReserved;

    public LocalDate date = LocalDate.now().minusDays(10);

    public AddPetsForm() {
    }

    public AddPetsForm(String petsName, String petsBreed) {
        this.petsName = petsName;
        this.petsBreed = petsBreed;
        this.petsBirthDate = date;
        this.isReserved = false;
    }

    public AddPetsForm(String petsName, String petsBreed, LocalDate petsBirthDate) {
        this.petsName = petsName;
        this.petsBreed = petsBreed;
        this.petsBirthDate = petsBirthDate;
        this.isReserved = false;
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

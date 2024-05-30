package com.faketri.market.infastructure.user.payload.user.dto;

import java.time.LocalDate;

public class UserUpdateRequest {

    private String name;
    private String surname;
    private LocalDate dateOfBirthday;
    private String city;
    private String email;

    public UserUpdateRequest() {
    }

    public UserUpdateRequest(String name, String surname, LocalDate dateOfBirthday, String city, String email) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirthday = dateOfBirthday;
        this.city = city;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDate dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

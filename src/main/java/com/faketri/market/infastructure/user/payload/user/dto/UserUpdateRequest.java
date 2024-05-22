package com.faketri.market.infastructure.user.payload.user.dto;

import java.time.LocalDateTime;

public class UserUpdateRequest {

    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirthday;
    private String email;

    public UserUpdateRequest() {
    }

    public UserUpdateRequest(String firstName, String lastName, LocalDateTime dateOfBirthday, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirthday = dateOfBirthday;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDateTime dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

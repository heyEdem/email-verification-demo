package com.edem.emailverificationdemo.Registration;

public record RegistrationRequest(String firstname, String lastname, String email, String password, String role) {

}

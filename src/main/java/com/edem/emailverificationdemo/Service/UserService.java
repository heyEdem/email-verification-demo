package com.edem.emailverificationdemo.Service;

import com.edem.emailverificationdemo.Registration.RegistrationRequest;
import com.edem.emailverificationdemo.User.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<Users> getUsers();

    Users registerUser(RegistrationRequest request);

    Optional<Users> findByEmail(String email);


    void saveUserVerificationToken(Users theUsers,String verificationToken);
}

package com.edem.emailverificationdemo.Service;

import com.edem.emailverificationdemo.Exception.UserAlreadyExistsException;
import com.edem.emailverificationdemo.Registration.RegistrationRequest;
import com.edem.emailverificationdemo.Repository.UserRepository;
import com.edem.emailverificationdemo.Token.VerificationToken;
import com.edem.emailverificationdemo.Token.VerificationTokenRepository;
import com.edem.emailverificationdemo.User.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder  passwordEncoder;
    private final VerificationTokenRepository tokenRepository;
    @Override
    public List<Users> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users registerUser(RegistrationRequest request) {
        Optional<Users> user = this.findByEmail(request.email());
        if(user.isPresent())
            throw new UserAlreadyExistsException("Users user with email "+request.email()+ " already exists");

        Users newUsers = new Users();
        newUsers.setFirstname(request.firstname());
        newUsers.setLastname(request.lastname());
        newUsers.setEmail(request.email());
        newUsers.setPassword(request.password());
        newUsers.setPassword(passwordEncoder.encode(request.password()));
        newUsers.setRole(request.role());
        return userRepository.save(newUsers);
    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUserVerificationToken( Users theUsers, String verificationToken) {
        var token = new VerificationToken(verificationToken, theUsers);
        tokenRepository.save(token);
    }
}
//id,firstname, lastname,naturalid email, password,role,isEnabled

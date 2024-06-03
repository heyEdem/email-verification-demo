package com.edem.emailverificationdemo.Registration;

import com.edem.emailverificationdemo.Event.RegistrationCompleteEvent;
import com.edem.emailverificationdemo.Service.UserService;
import com.edem.emailverificationdemo.Token.VerificationToken;
import com.edem.emailverificationdemo.Token.VerificationTokenRepository;
import com.edem.emailverificationdemo.User.Users;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;

    @PostMapping
    public String registerUser(@RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request){
        Users users = userService.registerUser(registrationRequest);

        //publish a registration event after saving the users
        publisher.publishEvent(new RegistrationCompleteEvent(users, applicationUrl(request)));
        return "Successful! check your email to complete registration";

    }

    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token){
        VerificationToken theToken = tokenRepository.findByToken(token);
        if(theToken.getUsers().isEnabled())
            return "This account has already been verified.. Please login";

        String verificationResult = userService.validateToken(token);
        return null;
    }
    public String applicationUrl(HttpServletRequest request) {
        return "https://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }

}

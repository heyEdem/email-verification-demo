package com.edem.emailverificationdemo.Event.Listener;

import com.edem.emailverificationdemo.Event.RegistrationCompleteEvent;
import com.edem.emailverificationdemo.Service.UserService;
import com.edem.emailverificationdemo.User.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private Users theUsers;
    private final UserService userService;


    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // get newly created user
        theUsers = event.getUsers();

        // create verification token for user
        String verificationToken = UUID.randomUUID().toString();


        // save the verification token
        userService.saveUserVerificationToken(theUsers, verificationToken);


        //build verification url to be sent to user
        String url = event.getApplicationUrl()+"/register/verifyEmail?token="+verificationToken;


        //send the email to user
        log.info("click the link to verify your registration: {}", url);

    }
}

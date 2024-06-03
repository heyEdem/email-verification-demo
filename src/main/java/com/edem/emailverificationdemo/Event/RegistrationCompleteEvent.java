package com.edem.emailverificationdemo.Event;

import com.edem.emailverificationdemo.User.Users;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    private Users users;
    private String applicationUrl;

    public RegistrationCompleteEvent(Users users, String applicationUrl) {
        super(users);
        this.users = users;
        this.applicationUrl  =  applicationUrl;
    }
}

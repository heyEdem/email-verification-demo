package com.edem.emailverificationdemo.Controller;


import com.edem.emailverificationdemo.Service.UserService;
import com.edem.emailverificationdemo.User.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<Users> getUsers(){
        return userService.getUsers();
    }
}

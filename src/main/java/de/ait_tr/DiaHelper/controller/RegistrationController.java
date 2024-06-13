package de.ait_tr.DiaHelper.controller;

import de.ait_tr.DiaHelper.domain.entity.User;
import de.ait_tr.DiaHelper.exception_handling.Response;
import de.ait_tr.DiaHelper.service.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {
    private UserServiceImpl service;

    public RegistrationController(UserServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public Response register(@RequestBody User user) {
        service.register(user);
        return new Response("Registration complete. Please check your email.");
    }


}
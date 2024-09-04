package com.platform.controller;


import com.platform.model.LoginRequest;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Hidden
public class AuthenticationController {

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public @ResponseBody String login(@RequestBody LoginRequest loginRequest) {

        return null;
    }

}

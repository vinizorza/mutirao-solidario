package com.vfl.mutirao_solidario.controller;

import com.vfl.mutirao_solidario.controller.dto.Signin;
import com.vfl.mutirao_solidario.controller.dto.Signup;
import com.vfl.mutirao_solidario.controller.dto.Token;
import com.vfl.mutirao_solidario.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    @RequestMapping(method = RequestMethod.POST, value = "/signin")
    public ResponseEntity<Token> signin(@RequestBody Signin user){
        return ResponseEntity.ok(authenticationService.signin(user));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<Token> signup(@RequestBody Signup user){
        return ResponseEntity.ok(authenticationService.signup(user));
    }

}

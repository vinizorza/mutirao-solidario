package com.vfl.mutirao_solidario.controller;

import com.vfl.mutirao_solidario.controller.dto.Signin;
import com.vfl.mutirao_solidario.controller.dto.Signup;
import com.vfl.mutirao_solidario.controller.dto.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(method = RequestMethod.POST, value = "/signin")
    public ResponseEntity<Token> signin(@RequestBody Signin user){
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<Token> signup(@RequestBody Signup user){
        return null;
    }

}

package com.vfl.mutirao_solidario.controller;

import com.vfl.mutirao_solidario.controller.dto.SigninDTO;
import com.vfl.mutirao_solidario.controller.dto.SignupDTO;
import com.vfl.mutirao_solidario.controller.dto.TokenDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(method = RequestMethod.POST, value = "/signin")
    public ResponseEntity<TokenDTO> signin(@RequestBody SigninDTO user){
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<TokenDTO> signup(@RequestBody SignupDTO user){
        return null;
    }

}

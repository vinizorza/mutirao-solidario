package com.vfl.mutirao_solidario.service;

import com.vfl.mutirao_solidario.controller.dto.Signin;
import com.vfl.mutirao_solidario.controller.dto.Signup;
import com.vfl.mutirao_solidario.controller.dto.Token;
import com.vfl.mutirao_solidario.model.User;
import com.vfl.mutirao_solidario.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public Token signup(Signup request) {
        var user = User.builder().name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .phoneNumber(request.phoneNumber())
                .build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return Token.builder().token(jwt).build();
    }

    public Token signin(Signin request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return Token.builder().token(jwt).build();
    }

}

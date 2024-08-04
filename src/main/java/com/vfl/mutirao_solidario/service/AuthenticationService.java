package com.vfl.mutirao_solidario.service;

import com.vfl.mutirao_solidario.controller.dto.Signin;
import com.vfl.mutirao_solidario.controller.dto.Signup;
import com.vfl.mutirao_solidario.controller.dto.AuthResponse;
import com.vfl.mutirao_solidario.model.User;
import com.vfl.mutirao_solidario.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse signup(Signup request) {
        var user = User.builder().name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .phoneNumber(request.phoneNumber())
                .build();

        if(userRepository.findByEmail(request.email()).isPresent())
            throw new IllegalArgumentException("Email already exists");

        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return AuthResponse.builder().name(userRepository.findByEmail(request.email()).get().getName())
                .token(jwt).build();
    }

    public AuthResponse signin(Signin request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return AuthResponse.builder().name(userRepository.findByEmail(request.email()).get().getName())
                .token(jwt).build();
    }

    public User getUserAuthenticated(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public void validateUser(Long id) throws BadCredentialsException {
        if(!getUserAuthenticated().getId().equals(id))
            throw new BadCredentialsException("User does not have the permission");
    }

}

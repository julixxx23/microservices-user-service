package com.microservices.userservice.user.infrastructure.adapter.in.rest;


import com.microservices.userservice.shared.infrastructure.security.jwt.JwtService;
import com.microservices.userservice.user.domain.model.Users;
import com.microservices.userservice.user.domain.port.in.SaveUserPort;
import com.microservices.userservice.user.infrastructure.adapter.in.rest.dto.UserCreateRequest;
import com.microservices.userservice.user.infrastructure.adapter.in.rest.dto.UserResponse;
import com.microservices.userservice.user.infrastructure.adapter.in.rest.mapper.rest.UserMapperRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final SaveUserPort saveUserPort;
    private final UserMapperRest mapper;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserCreateRequest request){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(
                userDetails.getUsername(),
                userDetails.getAuthorities().iterator().next().getAuthority()
        );
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserCreateRequest request){
        Users users = saveUserPort.save(mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(users));
    }
}

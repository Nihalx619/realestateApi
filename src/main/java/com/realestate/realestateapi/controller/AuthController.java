package com.realestate.realestateapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.realestateapi.dto.LoginDto;
import com.realestate.realestateapi.dto.RegisterDto;
import com.realestate.realestateapi.entity.Agent;
import com.realestate.realestateapi.repository.AgentRepository;
import com.realestate.realestateapi.security.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AgentRepository agentRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AgentRepository agentRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.agentRepository = agentRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto dto) {
        Agent agent = new Agent();
        agent.setName(dto.getName());
        agent.setEmail(dto.getEmail());
        agent.setPassword(passwordEncoder.encode(dto.getPassword()));
        agent.setRole(dto.getRole());
        agentRepository.save(agent);
        return ResponseEntity.status(HttpStatus.CREATED).body("Agent registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        String token = jwtUtil.generateToken(dto.getEmail());
        return ResponseEntity.ok(token);
    }
}
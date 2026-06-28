package com.realestate.realestateapi.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.realestate.realestateapi.repository.AgentRepository;

@Service
public class AgentDetailsService implements UserDetailsService {

    private final AgentRepository agentRepository;

    public AgentDetailsService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) 
            throws UsernameNotFoundException {
        return agentRepository.findByEmail(email)
                .orElseThrow(() -> 
                    new UsernameNotFoundException("Agent not found: " + email));
    }
}
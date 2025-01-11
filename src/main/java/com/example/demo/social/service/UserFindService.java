package com.example.demo.social.service;

import com.example.demo.social.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFindService {

    private final UserRepository userRepository;

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
}

package com.example.demo.favorite.service;

import com.example.demo.emergency.entity.Emergency;
import com.example.demo.emergency.repository.EmergencyRepository;
import com.example.demo.favorite.dto.FavoriteDto;
import com.example.demo.favorite.entity.Favorite;
import com.example.demo.favorite.repository.FavoriteRepository;
import com.example.demo.social.entity.User;
import com.example.demo.social.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final EmergencyRepository emergencyRepository;

    public void addFavorite(Long userId, Long emergencyId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Emergency emergency = emergencyRepository.findById(emergencyId).orElseThrow(() -> new IllegalArgumentException("Emergency not found"));

        Favorite favorite = Favorite.builder()
                .user(user)
                .emergency(emergency)
                .build();

        favoriteRepository.save(favorite);
    }

    public void deleteFavorite(Long userId, Long emergencyId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Emergency emergency = emergencyRepository.findById(emergencyId)
                .orElseThrow(() -> new IllegalArgumentException("Emergency not found"));

        Favorite favorite = favoriteRepository.findByUserAndEmergency(user, emergency)
                .orElseThrow(() -> new IllegalArgumentException("즐겨찾기 항목이 존재하지 않습니다."));

        favoriteRepository.delete(favorite);
    }




}
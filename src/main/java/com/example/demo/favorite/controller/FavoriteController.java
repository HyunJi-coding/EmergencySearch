package com.example.demo.favorite.controller;

import com.example.demo.favorite.service.FavoriteService;
import com.example.demo.social.entity.User;
import com.example.demo.social.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final UserRepository userRepository;

    @PostMapping("/addFavorite")
    @ResponseBody
    public String addFavorite(@RequestParam Long emergencyId, Authentication authentication) {
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();
        String email = (String) principal.getAttributes().get("email");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        favoriteService.addFavorite(user.getId(), emergencyId);
        return "즐겨찾기 추가 완료";
    }

    @PostMapping("/deleteFavorite")
    @ResponseBody
    public String deleteFavorite(@RequestParam Long emergencyId, Authentication authentication) {
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();
        String email = (String) principal.getAttributes().get("email");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        favoriteService.deleteFavorite(user.getId(), emergencyId);
        return "즐겨찾기 삭제 완료";
    }
}
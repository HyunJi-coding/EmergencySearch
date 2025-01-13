package com.example.demo.mypage.controller;

import com.example.demo.review.dto.ReviewResponseDto;
import com.example.demo.review.entity.Review;
import com.example.demo.review.servie.ReviewService;
import com.example.demo.social.entity.User;
import com.example.demo.social.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final ReviewService reviewService;
    private final UserRepository userRepository;

    @GetMapping
    public String myPage(Authentication authentication, Model model) {
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();
        String email = (String) principal.getAttributes().get("email");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<ReviewResponseDto> reviews = reviewService.getReviewsByUserId(user.getId())
                .stream()
                .map(review -> new ReviewResponseDto(
                        review.getUser().getEmail(),
                        review.getContent(),
                        review.getCreatedDate()
                ))
                .collect(Collectors.toList());

        model.addAttribute("user", user);
        model.addAttribute("reviews", reviews);

        return "mypage";
    }
}
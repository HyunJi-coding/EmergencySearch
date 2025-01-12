package com.example.demo.review.controller;

import com.example.demo.review.dto.ReviewResponseDto;
import com.example.demo.review.entity.Review;
import com.example.demo.review.servie.ReviewService;
import com.example.demo.social.entity.User;
import com.example.demo.social.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final UserRepository userRepository;

    @PostMapping("/addReview")
    @ResponseBody
    public String addReview(@RequestParam Long emergencyId,
                            @RequestParam String content,
                            Authentication authentication) {
        OAuth2User principal = (OAuth2User) authentication.getPrincipal();
        String email = (String) principal.getAttributes().get("email");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        reviewService.addReview(user.getId(), emergencyId, content);
        return "리뷰 추가 완료";
    }

}

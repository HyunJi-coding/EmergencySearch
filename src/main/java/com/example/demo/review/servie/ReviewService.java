package com.example.demo.review.servie;

import com.example.demo.emergency.entity.Emergency;
import com.example.demo.emergency.repository.EmergencyRepository;
import com.example.demo.review.entity.Review;
import com.example.demo.review.repository.ReviewRepository;
import com.example.demo.social.entity.User;
import com.example.demo.social.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final EmergencyRepository emergencyRepository;

    public void addReview(Long userId, Long emergencyId, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Emergency emergency = emergencyRepository.findById(emergencyId)
                .orElseThrow(() -> new IllegalArgumentException("Emergency not found"));

        Review review = Review.builder()
                .user(user)
                .emergency(emergency)
                .content(content)
                .build();

        reviewRepository.save(review);
    }

    public List<Review> getReviewsByEmergencyId(Long emergencyId) {
        return reviewRepository.findByEmergencyIdOrderByCreatedDateDesc(emergencyId);
    }

}


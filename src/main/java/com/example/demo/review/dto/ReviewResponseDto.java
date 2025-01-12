package com.example.demo.review.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponseDto {
    private String userEmail;
    private String content;
    private LocalDateTime createdDate;
}

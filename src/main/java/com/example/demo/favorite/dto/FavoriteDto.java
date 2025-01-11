package com.example.demo.favorite.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDto {

    private Long favoriteId;
    private String emergencyName;
    private String emergencyAddress;
    private String hospitalType;
}
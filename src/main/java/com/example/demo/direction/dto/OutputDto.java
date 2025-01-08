package com.example.demo.direction.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OutputDto {

    private String emergencyName;
    private String emergencyAddress;
    private String directionUrl;
    private String roadViewUrl;
    private String distance;
}
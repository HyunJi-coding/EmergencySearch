package com.example.demo.emergency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmergencyDto {

    private Long id;
    private String emergencyName;
    private String emergencyAddress;
    private double latitude;
    private double longitude;

}
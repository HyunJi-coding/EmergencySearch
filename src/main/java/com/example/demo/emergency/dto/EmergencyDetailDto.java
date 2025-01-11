package com.example.demo.emergency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmergencyDetailDto {

    private Long id;
    private String emergencyName;
    private String emergencyAddress;
    private String hospitalType;
    private String remarks;
    private String mainPhone1;
    private String emergencyPhone;
    private String consultationTimeStart;
    private String consultationTimeEnd;
    private String directionUrl;
    private double latitude;
    private double longitude;

}
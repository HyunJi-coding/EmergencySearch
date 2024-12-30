package com.example.demo.emergency.dto;

import com.example.demo.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
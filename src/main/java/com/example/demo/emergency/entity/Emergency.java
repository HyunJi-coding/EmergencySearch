package com.example.demo.emergency.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "emergency")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Emergency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emergencyName;
    private String emergencyAddress;
    private double latitude;
    private double longitude;

    public void changeEmergencyAddress(String address){
        this.emergencyAddress = address;
    }

}

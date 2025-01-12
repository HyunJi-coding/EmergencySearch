package com.example.demo.emergency.entity;

import com.example.demo.BaseTimeEntity;
import com.example.demo.favorite.entity.Favorite;
import com.example.demo.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "emergency")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Emergency extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emergencyName;
    private String emergencyAddress;
    private String hospitalType;
    private String remarks;
    private String mainPhone1;
    private String emergencyPhone;
    private String consultationTimeStart;
    private String consultationTimeEnd;
    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "emergency", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "emergency", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public void changeEmergencyAddress(String address){
        this.emergencyAddress = address;
    }

}

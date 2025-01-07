package com.example.demo.direction.controller;


import com.example.demo.emergency.service.EmergencyRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class FormController {

    private final EmergencyRecommendationService emergencyRecommendationService;

    @GetMapping("/")
    public String main() {
        return "main";
    }


}
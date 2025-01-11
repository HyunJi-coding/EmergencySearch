package com.example.demo.direction.controller;


import com.example.demo.direction.dto.InputDto;
import com.example.demo.emergency.dto.EmergencyDetailDto;
import com.example.demo.emergency.service.EmergencyDetailService;
import com.example.demo.emergency.service.EmergencyRecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequiredArgsConstructor
public class FormController {

    private final EmergencyRecommendationService emergencyRecommendationService;
    private final EmergencyDetailService emergencyDetailService;

    @Value("${kakao.javascript.key}")
    private String kakaoJavaScriptKey;

    @GetMapping("/")
    public String main(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser");

        model.addAttribute("isAuthenticated", isAuthenticated);
        return "main";
    }

    @PostMapping("/search")
    public ModelAndView postDirection(@ModelAttribute InputDto inputDto, Model model)  {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser");
        model.addAttribute("isAuthenticated", isAuthenticated);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("output");
        modelAndView.addObject("outputFormList",
                emergencyRecommendationService.recommendEmergencyList(inputDto.getAddress()));

        return modelAndView;
    }

    @GetMapping("/emergency/{emergencyName}")
    public String emergencyDetail(@PathVariable String emergencyName, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser");
        model.addAttribute("isAuthenticated", isAuthenticated);

        EmergencyDetailDto emergencyDetail = emergencyDetailService.getEmergencyDetail(emergencyName);
        model.addAttribute("emergencyDetail", emergencyDetail);
        model.addAttribute("kakaoJavaScriptKey", kakaoJavaScriptKey);

        return "detail";
    }


}
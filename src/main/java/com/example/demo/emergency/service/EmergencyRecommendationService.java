package com.example.demo.emergency.service;

import com.example.demo.api.dto.DocumentDto;
import com.example.demo.api.dto.KakaoApiResponseDto;
import com.example.demo.api.service.KakaoAddressSearchService;
import com.example.demo.direction.entity.Direction;
import com.example.demo.direction.service.DirectionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmergencyRecommendationService {

    private final KakaoAddressSearchService kakaoAddressSearchService;
    private final DirectionService directionService;

    public void recommendEmergencyList(String address) {

        KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

        if (Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentDtoList())) {
            log.error("[EmergencyRecommendationService.recommendEmergencyList fail] Input address: {}", address);
            return;
        }

        DocumentDto documentDto = kakaoApiResponseDto.getDocumentDtoList().get(0);

        List<Direction> directionList = directionService.buildDirectionList(documentDto);

        directionService.saveAll(directionList);
    }
}
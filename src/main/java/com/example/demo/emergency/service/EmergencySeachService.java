package com.example.demo.emergency.service;

import com.example.demo.emergency.dto.EmergencyDto;
import com.example.demo.emergency.entity.Emergency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmergencySeachService {

    private final EmergencyRepositoryService emergencyRepositoryService;

    public List<EmergencyDto> searchEmergencyDtoList() {

        return emergencyRepositoryService.findAll()
                .stream()
                .map(this::convertToEmergencyDto)
                .collect(Collectors.toList());
    }

    private EmergencyDto convertToEmergencyDto(Emergency emergency) {

        return EmergencyDto.builder()
                .id(emergency.getId())
                .emergencyName(emergency.getEmergencyName())
                .emergencyAddress(emergency.getEmergencyAddress())
                .latitude(emergency.getLatitude())
                .longitude(emergency.getLongitude())
                .build();
    }
}

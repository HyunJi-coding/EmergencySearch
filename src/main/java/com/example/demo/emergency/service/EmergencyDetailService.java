package com.example.demo.emergency.service;

import com.example.demo.emergency.dto.EmergencyDetailDto;
import com.example.demo.emergency.entity.Emergency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmergencyDetailService {

    private final EmergencyRepositoryService emergencyRepositoryService;

    public EmergencyDetailDto getEmergencyDetail(String emergencyName) {
        Emergency emergency = emergencyRepositoryService.findByEmergencyName(emergencyName);

        return convertToEmergencyDetailDto(emergency);
    }

    private EmergencyDetailDto convertToEmergencyDetailDto(Emergency emergency) {
        return EmergencyDetailDto.builder()
                .emergencyName(emergency.getEmergencyName())
                .emergencyAddress(emergency.getEmergencyAddress())
                .hospitalType(emergency.getHospitalType())
                .remarks(emergency.getRemarks())
                .mainPhone1(emergency.getMainPhone1())
                .emergencyPhone(emergency.getEmergencyPhone())
                .consultationTimeStart(emergency.getConsultationTimeStart())
                .consultationTimeEnd(emergency.getConsultationTimeEnd())
                .latitude(emergency.getLatitude())
                .longitude(emergency.getLongitude())
                .build();
    }
}
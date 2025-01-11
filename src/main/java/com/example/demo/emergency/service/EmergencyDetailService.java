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

    private static final String DIRECTION_BASE_URL = "https://map.kakao.com/link/map/";

    public EmergencyDetailDto getEmergencyDetail(String emergencyName) {
        Emergency emergency = emergencyRepositoryService.findByEmergencyName(emergencyName);

        return convertToEmergencyDetailDto(emergency);
    }

    private EmergencyDetailDto convertToEmergencyDetailDto(Emergency emergency) {
        return EmergencyDetailDto.builder()
                .id(emergency.getId())
                .emergencyName(emergency.getEmergencyName())
                .emergencyAddress(emergency.getEmergencyAddress())
                .hospitalType(emergency.getHospitalType())
                .remarks(emergency.getRemarks())
                .mainPhone1(emergency.getMainPhone1())
                .emergencyPhone(emergency.getEmergencyPhone())
                .consultationTimeStart(formatTime(emergency.getConsultationTimeStart()))
                .consultationTimeEnd(formatTime(emergency.getConsultationTimeEnd()))
                .directionUrl(buildDirectionUrl(emergency))
                .latitude(emergency.getLatitude())
                .longitude(emergency.getLongitude())

                .build();
    }

    private String formatTime(String time) {
        if (time == null || time.isEmpty()) {
            return "00:00";
        }

        String timeString = String.format("%04d", Integer.parseInt(time));

        String hours = timeString.substring(0, 2);
        String minutes = timeString.substring(2, 4);

        return hours + ":" + minutes;
    }

    private String buildDirectionUrl(Emergency emergency) {
        return DIRECTION_BASE_URL + String.join(",",
                emergency.getEmergencyName(),
                String.valueOf(emergency.getLatitude()),
                String.valueOf(emergency.getLongitude()));
    }
}
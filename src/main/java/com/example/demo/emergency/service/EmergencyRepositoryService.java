package com.example.demo.emergency.service;

import com.example.demo.emergency.entity.Emergency;
import com.example.demo.emergency.repository.EmergencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmergencyRepositoryService {

    private final EmergencyRepository emergencyRepository;

    @Transactional
    public void updateAddress(Long id, String address){

        Emergency entity = emergencyRepository.findById(id).orElse(null);

        if(Objects.isNull(entity)){
            log.error("[EmergencyRepositoryService updateAddress] not found id: {}", id);
            return;
        }

        entity.changeEmergencyAddress(address);

    }

    @Transactional(readOnly = true)
    public List<Emergency> findAll(){
        return emergencyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Emergency findByEmergencyName(String emergencyName) {
        Optional<Emergency> emergency = emergencyRepository.findByEmergencyName(emergencyName);
        return emergency.orElseThrow(() -> new RuntimeException("응급실 정보를 찾을 수 없습니다."));
    }
}

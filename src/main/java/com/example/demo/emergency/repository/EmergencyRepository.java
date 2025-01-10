package com.example.demo.emergency.repository;

import com.example.demo.emergency.entity.Emergency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmergencyRepository extends JpaRepository<Emergency, Long> {
    Optional<Emergency> findByEmergencyName(String emergencyName);
}

package com.example.demo.emergency.repository

import com.example.demo.AbstractIntegrationContainerBaseTest
import com.example.demo.emergency.entity.Emergency
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

import java.time.LocalDateTime

class EmergencyRepositoryTest extends AbstractIntegrationContainerBaseTest{

    @Autowired
    private EmergencyRepository emergencyRepository

    def setup() {
        emergencyRepository.deleteAll()
    }

    def "EmergencyRepository save"() {

        given:
        String address = "서울 특별시 강남구 일원로 81"
        String name = "삼성서울병원 응급실"
        double latitude = 36.11
        double longitude = 128.11

        def emergency = Emergency.builder()
                .emergencyAddress(address)
                .emergencyName(name)
                .latitude(latitude)
                .longitude(longitude)
                .build()
        when:
        def result = emergencyRepository.save(emergency)

        then:
        result.getEmergencyAddress() == address
        result.getEmergencyName() == name
        result.getLatitude() == latitude
        result.getLongitude() == longitude
    }

    def "EmergencyRepository saveAll"() {

        given:
        String address = "서울 특별시 강남구 일원로 81"
        String name = "삼성서울병원 응급실"
        double latitude = 36.11
        double longitude = 128.11

        def emergency = Emergency.builder()
                .emergencyAddress(address)
                .emergencyName(name)
                .latitude(latitude)
                .longitude(longitude)
                .build()
        when:
        emergencyRepository.saveAll(Arrays.asList(emergency))
        def result = emergencyRepository.findAll()

        then:
        result.get(0).getEmergencyAddress() == address
        result.get(0).getEmergencyName() == name
        result.get(0).getLatitude() == latitude
        result.get(0).getLongitude() == longitude
    }

    def "BaseTimeEntity_등록"() {

        given:
        def now = LocalDateTime.now()
        String address = "서울 특별시 성북구 종암동"
        String name = "삼성서울병원 응급실"

        def emergency = Emergency.builder()
                .emergencyAddress(address)
                .emergencyName(name)
                .build()
        when:
        emergencyRepository.save(emergency)
        def result = emergencyRepository.findAll()
        then:
        result.get(0).getCreatedDate().isAfter(now)
        result.get(0).getModifiedDate().isAfter(now)
    }

}


package com.example.demo.emergency.service

import com.example.demo.AbstractIntegrationContainerBaseTest
import com.example.demo.emergency.entity.Emergency
import com.example.demo.emergency.repository.EmergencyRepository
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

class EmergencyRepositoryServiceTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private EmergencyRepositoryService emergencyRepositoryService

    @Autowired
    private EmergencyRepository emergencyRepository

    void setup() {
        emergencyRepository.deleteAll()
    }

    def "EmergencyRepository update - dirty checking success"() {

        given:
        String inputAddress = "서울 특별시 성북구 종암동"
        String modifiedAddress = "서울 광진구 구의동"
        String name = "서울대학교 응급실"

        def emergency = Emergency.builder()
                .emergencyAddress(inputAddress)
                .emergencyName(name)
                .build()
        when:
        def entity = emergencyRepository.save(emergency)
        emergencyRepositoryService.updateAddress(entity.getId(), modifiedAddress)

        def result = emergencyRepository.findAll()

        then:
        result.get(0).getEmergencyAddress() == modifiedAddress
    }
}

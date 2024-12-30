package com.example.demo.direction.service

import com.example.demo.api.dto.DocumentDto
import com.example.demo.emergency.dto.EmergencyDto
import com.example.demo.emergency.service.EmergencySeachService
import spock.lang.Specification


class DirectionServiceTest extends Specification {

    private EmergencySeachService emergencySearchService = Mock()
    private DirectionService directionService = new DirectionService(emergencySearchService)

    private List<EmergencyDto> emergencyList

    def setup() {

        emergencyList = new ArrayList<>()
        emergencyList.addAll(
                EmergencyDto.builder()
                        .id(1L)
                        .emergencyName("서울대응급병원")
                        .emergencyAddress("주소1")
                        .latitude(37.61040424)
                        .longitude(127.0569046)
                        .build(),
                EmergencyDto.builder()
                        .id(2L)
                        .emergencyName("호수병원")
                        .emergencyAddress("주소2")
                        .latitude(37.60894036)
                        .longitude(127.029052)
                        .build()
        )
    }

    def "calculateDistance"() {
        given:
        def latitude1 = 37.5505
        def longitude1 = 127.0817

        def latitude2 = 37.541
        def longitude2 = 127.0766
        def result = "1.1"

        when:
        def distance = directionService.calculateDistance(latitude1, longitude1, latitude2, longitude2)

        then:
        String.format("%.1f", distance) == result
    }

    def "buildDirectionList - 결과 값이 거리순 정렬이 되는지 확인"() {
        given:
        def addressName = "서울 성북구 종암로10길"
        double inputLatitude = 37.5960650456809
        double inputLongitude = 127.037033003036

        def documentDto = DocumentDto.builder()
                .addressName(addressName)
                .latitude(inputLatitude)
                .longitude(inputLongitude)
                .build()

        when:
        emergencySearchService.searchEmergencyDtoList() >> emergencyList

        def results = directionService.buildDirectionList(documentDto)
        then:

        results.size() == 2
        results.get(0).targetEmergencyName == "호수병원"
        results.get(1).targetEmergencyName == "서울대응급병원"
        String.format("%.1f", results.get(0).distance) == "1.6"
        String.format("%.1f", results.get(1).distance) == "2.4"
    }

    def "buildDirectionList -  정해진 반경 10km 내에 검색이 되는지 확인"() {
        given:
        emergencyList.add(
                EmergencyDto.builder()
                        .id(3L)
                        .emergencyName("경기병원")
                        .emergencyAddress("주소3")
                        .latitude(37.3825107393401)
                        .longitude(127.236707811313)
                        .build())

        def addressName = "서울 성북구 종암로10길"
        double inputLatitude = 37.5960650456809
        double inputLongitude = 127.037033003036

        def documentDto = DocumentDto.builder()
                .addressName(addressName)
                .latitude(inputLatitude)
                .longitude(inputLongitude)
                .build()

        when:
        emergencySearchService.searchEmergencyDtoList() >> emergencyList

        def results = directionService.buildDirectionList(documentDto)
        then:

        results.size() == 2
        results.get(0).targetEmergencyName == "호수병원"
        results.get(1).targetEmergencyName == "서울대응급병원"
    }
}

package com.example.demo.api.service

import spock.lang.Specification
import java.nio.charset.StandardCharsets

class KakaoUriBuilderServiceTest extends Specification {

    private KakaoUriBuilderService kakaoUriBuilderService

    def setup() {
        kakaoUriBuilderService = new KakaoUriBuilderService()
    }

    def "buildUriByAddressSearch - 한글 파라미터의 경우 정상적으로 인코딩"() {
        given:
        def address = "서울 성북구"

        when:
        def uri = kakaoUriBuilderService.buildUriByAddressSearch(address)

        then:
        println uri
    }
}
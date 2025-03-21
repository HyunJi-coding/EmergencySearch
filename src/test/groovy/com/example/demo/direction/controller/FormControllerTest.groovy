package com.example.demo.direction.controller

import com.example.demo.emergency.service.EmergencyRecommendationService
import spock.lang.Specification

import com.example.demo.direction.dto.OutputDto
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class FormControllerTest extends Specification {

    private MockMvc mockMvc
    private EmergencyRecommendationService emergencyRecommendationService = Mock()
    private List<OutputDto> outputDtoList

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new FormController(emergencyRecommendationService))
                .build()

        outputDtoList = new ArrayList<>()
        outputDtoList.addAll(
                OutputDto.builder()
                        .emergencyName("emergency1")
                        .build(),
                OutputDto.builder()
                        .emergencyName("emergency2")
                        .build()
        )
    }

    def "GET /"() {

        expect:
        mockMvc.perform(get("/"))
                .andExpect(handler().handlerType(FormController.class))
                .andExpect(handler().methodName("main"))
                .andExpect(status().isOk())
                .andExpect(view().name("main"))
                .andDo(log())
    }

    def "POST /search"() {

        given:
        String inputAddress = "서울 성북구 종암동"

        when:
        def resultActions = mockMvc.perform(post("/search")
                .param("address", inputAddress))

        then:
        1 * emergencyRecommendationService.recommendEmergencyList(argument -> {
            assert argument == inputAddress
        }) >> outputDtoList

        resultActions
                .andExpect(status().isOk())
                .andExpect(view().name("output"))
                .andExpect(model().attributeExists("outputFormList"))
                .andExpect(model().attribute("outputFormList", outputDtoList))
                .andDo(print())
    }
}
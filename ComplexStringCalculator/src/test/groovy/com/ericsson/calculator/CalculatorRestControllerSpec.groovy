package com.ericsson.calculator

import static org.mockito.BDDMockito.given
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import com.ericsson.business.CalculatorService
import com.ericsson.web.CalculatorRestController

import spock.lang.Specification

@WebMvcTest(CalculatorRestController.class)
class CalculatorRestControllerSpec extends Specification {

    @MockBean
    private CalculatorService calculatorService

    @Autowired
    private MockMvc mockMvc

    def "Controller should return status OK when calculation call is successful"() {
        given: 'Calculator service will return valid response'
        given(calculatorService.calculate("1+1")).willReturn("2")

        when: 'Controller recieves valid calculation call'
        def response = mockMvc.perform(get("/calculator").contentType(MediaType.TEXT_HTML).param("infixExpression", "1+1"))

        then: 'response code is OK'
        response.andExpect(status().isOk())
    }
    
//    def "Controller should return status BAD REQUEST when invalid input is passed into calculator"() {
//        given: 'Calculator service will return valid response'
//        given(calculatorService.calculate("1=1")).willThrow(IllegalArgumentException)
//
//        when: 'Controller recieves invalid calculation call'
//        def response = mockMvc.perform(get("/calculator").contentType(MediaType.TEXT_HTML).param("infixExpression", "1=1"))
//
//        then: 'response code is BAD REQUEST'
//        response.andExpect(content().string("Invalid input detected. Argument at position: [1] is not valid operator or in illegal position.")).andExpect(status().isBadRequest())
//    }
}

package com.ericsson.calculator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import com.ericsson.validator.Validator
import com.ericsson.web.CalculatorService
import com.ericsson.exceptions.InvalidArgumentException
import com.ericsson.operations.PostfixCalculator
import spock.lang.Specification

@SpringBootTest
class CalculatorServiceSpec extends Specification {

    @Autowired
    CalculatorService calculatorService;

    def input, expectedResult

    def "Calculator Service throws exception when unsupported operators and letters are inputted into calculator"() {
        when: "Invalid argument is inputted into calculator"
        def result = calculatorService.convert(input)

        then: "Exception is thrown"
        thrown(InvalidArgumentException)

        where:
        input << [
            "A+B",
            "1+2-A",
            "1^2",
            "1+2%3",
            "1=2",
            "1+(2?3)",
            "1*£2"
        ]
    }
    
    def "Calculator Service throws exception when invalid infix notation is inputted into calculator"() {
        when: "Invalid argument is inputted into calculator"
        def result = calculatorService.convert(input)

        then: "Exception is thrown"
        thrown(InvalidArgumentException)

        where:
        input << [
            "1+2(",
            ")1+2",
            "1+(2**3)",
            "1+(2*3)+4)",
            "1++2",
            "1+(2**3)",
            "1+((2-+3)*4)",
            "1//2",
            "+1*2",
            "1*2+",
            "1(*2+2)",
            "1(2+)"
        ]
    }
    
    def "Calculator Service correctly evaluates valid infix notation"() {
        when: "Valid infix notation is inputted"
        input = calculatorService.convert(input)
        
        then: "input is converted to postfix notation"
        input == expectedResult

        where:
        input                   |       expectedResult
        "2+(2*4)"               |       "10"
        "21+3*6/3+2*2/4-3*1+6"  |       "31"
        "21+18/3+4/4-3+6-3"     |       "28"
    }
}

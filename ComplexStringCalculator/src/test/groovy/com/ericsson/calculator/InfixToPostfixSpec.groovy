package com.ericsson.calculator

import converter.InfixToPostfix
import spock.lang.Specification

class InfixToPostfixSpec extends Specification {

    InfixToPostfix convert = new InfixToPostfix();

    def input, expectedOutput

    def "Should convert infix notation to postfix notation" () {

        when: "Valid infix notation is inputted"
        input = convert.convertToPostfix(input)

        then: "input is converted to postfix notation"
        input == expectedOutput

        where:
        input                   |       expectedOutput
        "1+2"                   | 	"12+"
        "1+(2*3)"               |       "123*+"
        "1+(2*3-(4/5-6)*7)*8"   | 	"123*45/6-7*-8*+"
    }
}

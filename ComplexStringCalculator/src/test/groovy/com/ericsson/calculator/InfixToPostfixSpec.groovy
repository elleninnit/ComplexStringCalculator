package com.ericsson.calculator

import spock.lang.Specification

class InfixToPostfixSpec extends Specification {

	InfixToPostfix convert = new InfixToPostfix();
	
	def expectedOutput, input

	def "Should convert infix notation to postfix notation" () {

		when: "Infix notation is inputted"
		input = convert.convertToPostfix(input)

		then: "input is converted to postfix notation"
		input == expectedOutput

		where:
		input					|	expectedOutput
		"1+2" 					| 	"12+"
		"1-2"					|	"12-"
		"1+(2*3-(4/5-6)*7)*8"	| 	"123*45/6-7*-8*+"
	}
}

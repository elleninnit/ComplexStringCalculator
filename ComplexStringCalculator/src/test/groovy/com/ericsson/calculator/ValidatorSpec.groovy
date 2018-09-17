package com.ericsson.calculator

import spock.lang.Specification
import validator.Validator

class ValidatorSpec extends Specification {

    Validator validator = new Validator()

    def input

    def "Should return false if two operators occur twice in a row" () {

        when: "Invalid string is passed to validator"
        def output = validator.validate(input)

        then: "should return false"
        output == false

        where:
        input << ["1++2", "1+(2**3)", "1+((2-+3) * 4)", "1//2"]
    }

    def "Should return false if equation starts or ends with an operator" () {

        when: "Invalid string is passed to validator"
        def output = validator.validate(input)

        then: "should return false"
        output == false

        where:
        input << ["+1+2", "1+2-", "1+2-3/", "1/2*"]
    }

    def "Should return false if equation has letters" () {

        when: "Invalid string is passed to validator"
        def output = validator.validate(input)

        then: "should return false"
        output == false

        where:
        input << ["A+1", "A+(B-C)", "1+2-A"]
    }

    def "Should return false if unsupported/invalid operators are inputted" () {

        when: "Invalid string is passed to validator"
        def output = validator.validate(input)

        then: "should return false"
        output == false

        where:
        input << ["1^2", "1+2%3", "1=2", "1+(2?3)", "1*£2"]
    }

    def "Should return false unmatched bracket is found" () {

        when: "Invalid string is passed to validator"
        def output = validator.validate(input)

        then: "should return false"
        output == false

        where:
        input << ["1+2(", "1+(2-3", "1+(2*3)+4)", ")1+2", "1+2(+3)", "1+2+)"]
    }

    def "Should return true if valid equation is inputted" () {

        when: "Valid string is inputted"
        def output = validator.validate(input)

        then: "should return true"
        output == true

        where:
        input << ["1+2", "1+(2-3)", "1/2", "1+((2*3)+4)", "1*2"]
    }
}

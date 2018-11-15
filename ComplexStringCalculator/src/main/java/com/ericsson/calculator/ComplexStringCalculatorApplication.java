package com.ericsson.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.ericsson" })
public class ComplexStringCalculatorApplication {
    public static void main(final String[] args) {
        SpringApplication.run(ComplexStringCalculatorApplication.class, args);
    }
}

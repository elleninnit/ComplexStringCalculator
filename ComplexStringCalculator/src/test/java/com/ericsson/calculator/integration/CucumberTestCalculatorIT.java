package com.ericsson.calculator.integration;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(glue = "Steps", features = "src/test/resources/features/")
public class CucumberTestCalculatorIT {

}

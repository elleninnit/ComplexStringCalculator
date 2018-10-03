package com.ericsson.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCalculatorIT {
    
    private WebDriver driver;

    private static final String INPUT = "input";
    private static final String CALCULATE_BUTTON = "calculateButton";
    private static final String RESULT = "result";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\eleohlt\\Documents\\Git\\ComplexStringCalculator\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080");
    }

    @Test
    public void testValidInput() {
        String input = "21+18/3+4/4-3+6-3";
        driver.findElement(By.id(INPUT)).sendKeys(input);
        driver.findElement(By.id(CALCULATE_BUTTON)).click();
        assertEquals("28", driver.findElement(By.id(RESULT)).getText());
    }
    
    @Test
    public void testInvalidInput() {
        String input = "12/2+(5#=)";
        driver.findElement(By.id(INPUT)).sendKeys(input);
        driver.findElement(By.id(CALCULATE_BUTTON)).click();
        assertEquals("", driver.findElement(By.id(RESULT)).getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
    
}
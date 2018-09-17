package calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import converter.InfixToPostfix;
import validator.Validator;

@SpringBootApplication
public class ComplexStringCalculatorApplication {

    @Autowired
    private static Validator validator = new Validator();
    
    @Autowired
    private static InfixToPostfix converter = new InfixToPostfix();
    
    public static void main(final String[] args) {

        String input = "21+3*6/3+2";
        input = input.replaceAll("\\s+", "");

        if (validator.validate(input)) {
            System.out.println(converter.convertToPostfix(input));
        } else {
            System.out.println("Incorrect input!");
        }

        
        SpringApplication.run(ComplexStringCalculatorApplication.class, args);
    }
}

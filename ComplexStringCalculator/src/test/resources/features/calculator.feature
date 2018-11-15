Feature: to perform integer calculations on arbitary string expressions

  Scenario: A user inputs a string expression to be calculated
    When A user sends a call to calculate a string expression "21+3"
    Then The user receives back a correct string expression "24"

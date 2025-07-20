package com.example;

public class CalculatorTest {
    
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        
        // Test addition
        assert calc.add(2, 3) == 5 : "Addition test failed";
        
        // Test subtraction
        assert calc.subtract(5, 3) == 2 : "Subtraction test failed";
        
        // Test multiplication
        assert calc.multiply(4, 3) == 12 : "Multiplication test failed";
        
        // Test division
        assert calc.divide(10, 2) == 5.0 : "Division test failed";
        
        System.out.println("All tests passed!");
    }
}
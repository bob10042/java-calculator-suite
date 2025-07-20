package com.example;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Command-line Scientific Calculator
 * Works in terminal without GUI
 */
public class ConsoleCalculator {
    private Scanner scanner;
    private Calculator calculator;
    private Map<String, Double> variables;
    private List<String> commandHistory;

    public ConsoleCalculator() {
        scanner = new Scanner(System.in);
        calculator = new Calculator();
        variables = new HashMap<>();
        commandHistory = new ArrayList<>();
        setupPhysicsConstants();
    }

    private void setupPhysicsConstants() {
        variables.put("c", PhysicsConstants.c);
        variables.put("h", PhysicsConstants.h);
        variables.put("e", PhysicsConstants.e);
        variables.put("m_e", PhysicsConstants.m_e);
        variables.put("m_p", PhysicsConstants.m_p);
        variables.put("k_B", PhysicsConstants.k_B);
        variables.put("G", PhysicsConstants.G);
        variables.put("g", PhysicsConstants.g);
        variables.put("alpha", PhysicsConstants.alpha);
        variables.put("epsilon_0", PhysicsConstants.epsilon_0);
        variables.put("mu_0", PhysicsConstants.mu_0);
        variables.put("eV", PhysicsConstants.eV);
        variables.put("AU", PhysicsConstants.AU);
        variables.put("ly", PhysicsConstants.ly);
        variables.put("pi", Math.PI);
        variables.put("E", Math.E);
    }

    public void run() {
        printWelcome();
        
        while (true) {
            System.out.print(">>> ");
            String input = scanner.nextLine().trim();
            
            if (input.isEmpty()) continue;
            
            commandHistory.add(input);
            
            if (input.equals("exit") || input.equals("quit")) {
                System.out.println("Goodbye!");
                break;
            }
            
            try {
                String result = processCommand(input);
                if (!result.isEmpty()) {
                    System.out.println(result);
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
            
            System.out.println();
        }
    }

    private void printWelcome() {
        System.out.println("=== SCIENTIFIC COMMAND-LINE CALCULATOR ===");
        System.out.println("Version 1.0 - Physics & Engineering Calculator");
        System.out.println();
        System.out.println("Quick Examples:");
        System.out.println(">>> 2 + 3 * 4");
        System.out.println(">>> sqrt(16) + sin(pi/2)");
        System.out.println(">>> c                     (speed of light)");
        System.out.println(">>> m_e * c^2 / eV        (electron rest energy in eV)");
        System.out.println(">>> photon_energy(500e-9) (green photon energy)");
        System.out.println();
        System.out.println("Commands: help, constants, variables, examples, exit");
        System.out.println();
    }

    private String processCommand(String command) throws Exception {
        // Handle special commands
        switch (command) {
            case "help":
                return getHelpText();
            case "constants":
                return PhysicsConstants.getAllConstants();
            case "variables":
            case "vars":
                return getVariables();
            case "examples":
                return getExamples();
            case "demo":
                runDemo();
                return "";
        }

        // Handle variable assignments
        if (command.contains("=") && !command.contains("==")) {
            return handleAssignment(command);
        }

        // Evaluate expression
        double result = evaluateExpression(command);
        return formatResult(result);
    }

    private String handleAssignment(String expression) {
        String[] parts = expression.split("=", 2);
        if (parts.length != 2) {
            throw new RuntimeException("Invalid assignment syntax");
        }

        String varName = parts[0].trim();
        String valueExpr = parts[1].trim();

        double value = evaluateExpression(valueExpr);
        variables.put(varName, value);

        return varName + " = " + formatResult(value);
    }

    private double evaluateExpression(String expression) {
        // Replace variables
        String processedExpression = replaceVariables(expression);
        
        // Replace mathematical functions
        processedExpression = replaceMathFunctions(processedExpression);
        
        // Replace physics functions
        processedExpression = replacePhysicsFunctions(processedExpression);

        // Handle power operator
        processedExpression = processedExpression.replaceAll("\\^", "**");
        
        // Evaluate using JavaScript engine
        try {
            javax.script.ScriptEngineManager manager = new javax.script.ScriptEngineManager();
            javax.script.ScriptEngine engine = manager.getEngineByName("JavaScript");
            
            // Convert ** back to Math.pow for JavaScript
            processedExpression = convertPowerOperator(processedExpression);
            
            Object result = engine.eval(processedExpression);
            double value = ((Number) result).doubleValue();
            
            variables.put("ans", value);
            return value;
            
        } catch (Exception e) {
            throw new RuntimeException("Cannot evaluate expression: " + expression);
        }
    }

    private String convertPowerOperator(String expression) {
        // Convert a**b to Math.pow(a,b)
        Pattern pattern = Pattern.compile("([\\d\\.\\w\\(\\)]+)\\*\\*([\\d\\.\\w\\(\\)]+)");
        Matcher matcher = pattern.matcher(expression);
        
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String base = matcher.group(1);
            String exponent = matcher.group(2);
            matcher.appendReplacement(sb, "Math.pow(" + base + "," + exponent + ")");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private String replaceVariables(String expression) {
        for (Map.Entry<String, Double> entry : variables.entrySet()) {
            String varName = entry.getKey();
            String value = entry.getValue().toString();
            expression = expression.replaceAll("\\b" + Pattern.quote(varName) + "\\b", value);
        }
        return expression;
    }

    private String replaceMathFunctions(String expression) {
        expression = expression.replaceAll("\\bsin\\(", "Math.sin(");
        expression = expression.replaceAll("\\bcos\\(", "Math.cos(");
        expression = expression.replaceAll("\\btan\\(", "Math.tan(");
        expression = expression.replaceAll("\\bsqrt\\(", "Math.sqrt(");
        expression = expression.replaceAll("\\blog\\(", "Math.log10(");
        expression = expression.replaceAll("\\bln\\(", "Math.log(");
        expression = expression.replaceAll("\\bexp\\(", "Math.exp(");
        expression = expression.replaceAll("\\babs\\(", "Math.abs(");
        return expression;
    }

    private String replacePhysicsFunctions(String expression) {
        // Handle photon_energy function
        if (expression.contains("photon_energy(")) {
            Pattern pattern = Pattern.compile("photon_energy\\(([^)]+)\\)");
            Matcher matcher = pattern.matcher(expression);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                double wavelength = Double.parseDouble(matcher.group(1));
                double energy = PhysicsConstants.photonEnergy(wavelength);
                matcher.appendReplacement(sb, String.valueOf(energy));
            }
            matcher.appendTail(sb);
            expression = sb.toString();
        }

        // Handle de_broglie function
        if (expression.contains("de_broglie(")) {
            Pattern pattern = Pattern.compile("de_broglie\\(([^,]+),([^)]+)\\)");
            Matcher matcher = pattern.matcher(expression);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                double mass = Double.parseDouble(matcher.group(1));
                double velocity = Double.parseDouble(matcher.group(2));
                double wavelength = PhysicsConstants.deBroglieWavelength(mass, velocity);
                matcher.appendReplacement(sb, String.valueOf(wavelength));
            }
            matcher.appendTail(sb);
            expression = sb.toString();
        }

        return expression;
    }

    private String formatResult(double result) {
        if (Math.abs(result) > 1e6 || (Math.abs(result) < 1e-3 && result != 0)) {
            return String.format("%.6e", result);
        } else {
            return String.format("%.6f", result);
        }
    }

    private String getVariables() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current Variables:\n");
        
        // Show important physics constants first
        String[] important = {"c", "h", "e", "m_e", "k_B", "G", "eV", "pi", "ans"};
        for (String var : important) {
            if (variables.containsKey(var)) {
                sb.append(String.format("  %-8s = %s\n", var, formatResult(variables.get(var))));
            }
        }
        
        sb.append("\nAll variables: " + variables.keySet().size() + " total");
        return sb.toString();
    }

    private String getHelpText() {
        return "COMMANDS:\n" +
               "  help          - Show this help\n" +
               "  constants     - Show physics constants\n" +
               "  variables     - Show current variables\n" +
               "  examples      - Show example calculations\n" +
               "  demo          - Run demonstration\n" +
               "  exit/quit     - Exit calculator\n\n" +
               "EXPRESSIONS:\n" +
               "  2 + 3 * 4     - Basic arithmetic\n" +
               "  x = 5 * c     - Variable assignment\n" +
               "  sin(pi/2)     - Trigonometric functions\n" +
               "  sqrt(16)      - Mathematical functions\n" +
               "  2^8           - Power operation\n" +
               "  photon_energy(500e-9) - Physics functions\n\n" +
               "CONSTANTS:\n" +
               "  c, h, e, m_e, k_B, G, pi, E, eV, etc.\n";
    }

    private String getExamples() {
        return "EXAMPLE CALCULATIONS:\n\n" +
               "Basic Math:\n" +
               ">>> 2 + 3 * 4\n" +
               ">>> sqrt(144)\n" +
               ">>> 2^8\n" +
               ">>> sin(pi/2)\n\n" +
               "Physics:\n" +
               ">>> c                          (speed of light)\n" +
               ">>> m_e * c^2 / eV             (electron rest energy in eV)\n" +
               ">>> photon_energy(500e-9) / eV (green photon in eV)\n" +
               ">>> de_broglie(m_e, 1e6)       (electron wavelength)\n\n" +
               "Variables:\n" +
               ">>> E = m_e * c^2\n" +
               ">>> E_eV = E / eV\n" +
               ">>> ans                        (last result)\n";
    }

    private void runDemo() {
        System.out.println("=== RUNNING DEMONSTRATION ===\n");
        
        String[] calculations = {
            "2 + 3 * 4",
            "sqrt(16) + sin(pi/2)",
            "c",
            "h",
            "E = m_e * c^2",
            "E / eV",
            "photon_energy(500e-9)",
            "photon_energy(500e-9) / eV",
            "de_broglie(m_e, 1e6)"
        };
        
        String[] descriptions = {
            "Basic arithmetic",
            "Math functions",
            "Speed of light",
            "Planck constant", 
            "Electron rest energy (store in E)",
            "Convert to electron volts",
            "Green photon energy",
            "Green photon in eV",
            "Electron de Broglie wavelength"
        };
        
        for (int i = 0; i < calculations.length; i++) {
            System.out.println(">>> " + calculations[i] + "    // " + descriptions[i]);
            try {
                double result = evaluateExpression(calculations[i]);
                System.out.println(formatResult(result));
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
            System.out.println();
            
            // Pause briefly
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }
        
        System.out.println("=== DEMONSTRATION COMPLETE ===");
    }

    public static void main(String[] args) {
        System.out.println("Starting console calculator...\n");
        
        ConsoleCalculator calc = new ConsoleCalculator();
        calc.run();
    }
}
package com.example;

import java.util.*;

/**
 * Simple Console Calculator that works without JavaScript engine
 */
public class SimpleConsoleCalc {
    private Scanner scanner;
    private Map<String, Double> variables;

    public SimpleConsoleCalc() {
        scanner = new Scanner(System.in);
        variables = new HashMap<>();
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
        System.out.println("=== PHYSICS CONSOLE CALCULATOR ===");
        System.out.println("Version 1.0 - Physics Constants & Basic Calculations");
        System.out.println();
        System.out.println("Available Commands:");
        System.out.println("constants  - Show physics constants");
        System.out.println("variables  - Show current variables");
        System.out.println("demo       - Run demonstration");
        System.out.println("help       - Show help");
        System.out.println("exit       - Exit calculator");
        System.out.println();
        System.out.println("Try: constants, demo, or variable names like 'c', 'h', 'm_e'");
        System.out.println();
    }

    private String processCommand(String command) throws Exception {
        switch (command) {
            case "help":
                return getHelpText();
            case "constants":
                return PhysicsConstants.getAllConstants();
            case "variables":
            case "vars":
                return getVariables();
            case "demo":
                runDemo();
                return "";
            default:
                // Check if it's a variable name
                if (variables.containsKey(command)) {
                    double value = variables.get(command);
                    variables.put("ans", value);
                    return command + " = " + formatResult(value);
                }
                
                // Try some simple calculations
                return handleSimpleCalculation(command);
        }
    }

    private String handleSimpleCalculation(String command) {
        // Handle some simple patterns
        if (command.equals("electron_energy") || command.equals("m_e * c^2")) {
            double energy = PhysicsConstants.m_e * PhysicsConstants.c * PhysicsConstants.c;
            variables.put("ans", energy);
            return "Electron rest energy = " + formatResult(energy) + " J = " + 
                   formatResult(energy / PhysicsConstants.eV) + " eV";
        }
        
        if (command.equals("proton_energy") || command.equals("m_p * c^2")) {
            double energy = PhysicsConstants.m_p * PhysicsConstants.c * PhysicsConstants.c;
            variables.put("ans", energy);
            return "Proton rest energy = " + formatResult(energy) + " J = " + 
                   formatResult(energy / PhysicsConstants.eV) + " eV";
        }
        
        if (command.startsWith("photon_energy_")) {
            // Extract wavelength from command like "photon_energy_500nm"
            String wavelengthStr = command.substring(14);
            if (wavelengthStr.endsWith("nm")) {
                double wavelength = Double.parseDouble(wavelengthStr.substring(0, wavelengthStr.length()-2)) * 1e-9;
                double energy = PhysicsConstants.photonEnergy(wavelength);
                variables.put("ans", energy);
                return "Photon energy (" + wavelengthStr + ") = " + formatResult(energy) + " J = " +
                       formatResult(energy / PhysicsConstants.eV) + " eV";
            }
        }
        
        if (command.equals("fine_structure")) {
            return "Fine structure constant = " + formatResult(PhysicsConstants.alpha);
        }
        
        if (command.equals("bohr_radius")) {
            return "Bohr radius = " + formatResult(PhysicsConstants.a_0) + " m";
        }
        
        if (command.equals("rydberg")) {
            return "Rydberg constant = " + formatResult(PhysicsConstants.R_infinity) + " m⁻¹";
        }
        
        throw new RuntimeException("Unknown command or calculation: " + command);
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
        sb.append("Physics Constants Available:\n");
        
        String[] constants = {"c", "h", "e", "m_e", "m_p", "k_B", "G", "g", "alpha", "eV", "AU", "ly", "pi"};
        String[] descriptions = {
            "Speed of light", "Planck constant", "Elementary charge", 
            "Electron mass", "Proton mass", "Boltzmann constant",
            "Gravitational constant", "Standard gravity", "Fine structure constant",
            "Electron volt", "Astronomical unit", "Light year", "Pi"
        };
        
        for (int i = 0; i < constants.length; i++) {
            if (variables.containsKey(constants[i])) {
                sb.append(String.format("  %-12s = %-12s (%s)\n", 
                    constants[i], formatResult(variables.get(constants[i])), descriptions[i]));
            }
        }
        
        return sb.toString();
    }

    private String getHelpText() {
        return "AVAILABLE COMMANDS:\n" +
               "  constants         - Show all physics constants\n" +
               "  variables         - Show available constants\n" +
               "  demo              - Run demonstration\n" +
               "  help              - Show this help\n" +
               "  exit/quit         - Exit calculator\n\n" +
               "PHYSICS CONSTANTS (type name to see value):\n" +
               "  c                 - Speed of light\n" +
               "  h                 - Planck constant\n" +
               "  e                 - Elementary charge\n" +
               "  m_e               - Electron mass\n" +
               "  m_p               - Proton mass\n" +
               "  k_B               - Boltzmann constant\n" +
               "  G                 - Gravitational constant\n" +
               "  eV                - Electron volt\n" +
               "  alpha             - Fine structure constant\n\n" +
               "SPECIAL CALCULATIONS:\n" +
               "  electron_energy   - Electron rest energy\n" +
               "  proton_energy     - Proton rest energy\n" +
               "  photon_energy_500nm - Green light photon energy\n" +
               "  fine_structure    - Fine structure constant\n" +
               "  bohr_radius       - Bohr radius\n";
    }

    private void runDemo() {
        System.out.println("=== PHYSICS CONSTANTS DEMONSTRATION ===\n");
        
        String[] examples = {
            "c", "h", "e", "m_e", "electron_energy", 
            "photon_energy_500nm", "fine_structure", "bohr_radius"
        };
        
        for (String example : examples) {
            System.out.println(">>> " + example);
            try {
                String result = processCommand(example);
                System.out.println(result);
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
            System.out.println();
            
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
        }
        
        System.out.println("=== DEMONSTRATION COMPLETE ===");
        System.out.println("Try typing any of the commands shown above!");
    }

    public static void main(String[] args) {
        System.out.println("Starting Physics Console Calculator...\n");
        
        SimpleConsoleCalc calc = new SimpleConsoleCalc();
        calc.run();
    }
}
package com.example;

public class Calculator {
    
    public int add(int a, int b) {
        return a + b;
    }
    
    public int subtract(int a, int b) {
        return a - b;
    }
    
    public int multiply(int a, int b) {
        return a * b;
    }
    
    public double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return (double) a / b;
    }
    
    // Power calculations
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
    
    public double square(double value) {
        return Math.pow(value, 2);
    }
    
    public double cube(double value) {
        return Math.pow(value, 3);
    }
    
    // Electrical power calculations
    public double calculatePower(double voltage, double current) {
        return voltage * current; // P = V * I
    }
    
    public double calculatePowerFromResistance(double voltage, double resistance) {
        if (resistance == 0) {
            throw new IllegalArgumentException("Resistance cannot be zero");
        }
        return Math.pow(voltage, 2) / resistance; // P = V² / R
    }
    
    public double calculateApparentPower(double voltage, double current) {
        return voltage * current; // S = V * I (VA)
    }
    
    public double calculateReactivePower(double apparentPower, double realPower) {
        return Math.sqrt(Math.pow(apparentPower, 2) - Math.pow(realPower, 2)); // Q = √(S² - P²)
    }
    
    public double calculatePowerFactor(double realPower, double apparentPower) {
        if (apparentPower == 0) {
            throw new IllegalArgumentException("Apparent power cannot be zero");
        }
        return realPower / apparentPower; // PF = P / S
    }
    
    // 3-phase power calculations
    public double calculateThreePhaseRealPower(double voltage, double current, double powerFactor) {
        return Math.sqrt(3) * voltage * current * powerFactor; // P = √3 * V * I * cos(φ)
    }
    
    public double calculateThreePhaseApparentPower(double voltage, double current) {
        return Math.sqrt(3) * voltage * current; // S = √3 * V * I
    }
    
    public double calculateThreePhaseReactivePower(double voltage, double current, double powerFactor) {
        double sinPhi = Math.sqrt(1 - Math.pow(powerFactor, 2));
        return Math.sqrt(3) * voltage * current * sinPhi; // Q = √3 * V * I * sin(φ)
    }
    
    // Line to line voltage calculation
    public double calculateLineToLineVoltage(double lineToNeutralVoltage) {
        return lineToNeutralVoltage * Math.sqrt(3); // VLL = √3 * VLN
    }
    
    // Phase current calculation (balanced load)
    public double calculatePhaseCurrentBalanced(double totalPower, double lineVoltage, double powerFactor) {
        if (lineVoltage == 0 || powerFactor == 0) {
            throw new IllegalArgumentException("Voltage and power factor cannot be zero");
        }
        return totalPower / (Math.sqrt(3) * lineVoltage * powerFactor); // I = P / (√3 * V * cos(φ))
    }
    
    // Electrical impedance calculations
    public double calculateImpedanceMagnitude(double resistance, double reactance) {
        return Math.sqrt(Math.pow(resistance, 2) + Math.pow(reactance, 2)); // Z = √(R² + X²)
    }
    
    public double calculateImpedanceAngle(double resistance, double reactance) {
        return Math.toDegrees(Math.atan(reactance / resistance)); // φ = arctan(X/R)
    }
    
    // Physics calculations using constants
    public double calculateElectronRestEnergy() {
        return PhysicsConstants.m_e * PhysicsConstants.c * PhysicsConstants.c; // E = mc²
    }
    
    public double calculatePhotonEnergy(double wavelength) {
        return PhysicsConstants.photonEnergy(wavelength); // E = hc/λ
    }
    
    public double calculatePhotonWavelength(double energy) {
        return PhysicsConstants.photonWavelength(energy); // λ = hc/E
    }
    
    public double calculateDeBroglieWavelength(double mass, double velocity) {
        return PhysicsConstants.deBroglieWavelength(mass, velocity); // λ = h/(mv)
    }
    
    public double calculateKineticEnergy(double mass, double velocity) {
        return 0.5 * mass * velocity * velocity; // KE = ½mv²
    }
    
    public double calculatePotentialEnergy(double mass, double height) {
        return mass * PhysicsConstants.g * height; // PE = mgh
    }
    
    public double calculateCoulombForce(double q1, double q2, double distance) {
        return PhysicsConstants.k_e * q1 * q2 / (distance * distance); // F = kq₁q₂/r²
    }
    
    public double calculateGravitationalForce(double m1, double m2, double distance) {
        return PhysicsConstants.G * m1 * m2 / (distance * distance); // F = Gm₁m₂/r²
    }
    
    public double calculateEscapeVelocity(double mass, double radius) {
        return PhysicsConstants.escapeVelocity(mass, radius); // v = √(2GM/r)
    }
    
    public double calculateOrbitalVelocity(double centralMass, double radius) {
        return Math.sqrt(PhysicsConstants.G * centralMass / radius); // v = √(GM/r)
    }
    
    public double calculateBlackbodyPower(double temperature, double area) {
        return PhysicsConstants.sigma * area * Math.pow(temperature, 4); // P = σAT⁴
    }
    
    public double calculateWienDisplacement(double temperature) {
        return PhysicsConstants.b / temperature; // λ_max = b/T
    }
    
    public double calculateRelativisticMass(double restMass, double velocity) {
        double gamma = 1.0 / Math.sqrt(1 - (velocity * velocity) / (PhysicsConstants.c * PhysicsConstants.c));
        return gamma * restMass; // m = γm₀
    }
    
    public double calculateRelativisticEnergy(double restMass, double velocity) {
        double gamma = 1.0 / Math.sqrt(1 - (velocity * velocity) / (PhysicsConstants.c * PhysicsConstants.c));
        return gamma * restMass * PhysicsConstants.c * PhysicsConstants.c; // E = γmc²
    }
    
    public double calculateTimeDialation(double velocity) {
        return 1.0 / Math.sqrt(1 - (velocity * velocity) / (PhysicsConstants.c * PhysicsConstants.c)); // γ
    }
    
    public double calculateLengthContraction(double velocity) {
        return Math.sqrt(1 - (velocity * velocity) / (PhysicsConstants.c * PhysicsConstants.c)); // 1/γ
    }
}
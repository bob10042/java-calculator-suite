package com.example;

/**
 * Scientific Computing Examples
 * Demonstrates physics calculations and real-world applications
 */
public class ScientificExamples {
    private Calculator calc;
    
    public ScientificExamples() {
        calc = new Calculator();
    }
    
    /**
     * Example 1: Hydrogen Atom Calculations
     */
    public void hydrogenAtomExample() {
        System.out.println("=== HYDROGEN ATOM CALCULATIONS ===");
        
        // Ground state energy (n=1)
        double rydberg = PhysicsConstants.R_infinity;
        double groundStateEnergy = -13.6 * PhysicsConstants.eV; // -13.6 eV
        System.out.printf("Ground state energy: %.3f eV\n", groundStateEnergy / PhysicsConstants.eV);
        
        // Bohr radius
        double bohrRadius = PhysicsConstants.a_0;
        System.out.printf("Bohr radius: %.3e m (%.3f Angstroms)\n", bohrRadius, bohrRadius * 1e10);
        
        // Electron orbital velocity in ground state
        double velocity = PhysicsConstants.alpha * PhysicsConstants.c;
        System.out.printf("Electron velocity (ground state): %.3e m/s (%.1f%% of c)\n", 
                         velocity, (velocity / PhysicsConstants.c) * 100);
        
        // Binding energy
        double bindingEnergy = Math.abs(groundStateEnergy);
        System.out.printf("Binding energy: %.3f eV\n", bindingEnergy / PhysicsConstants.eV);
        
        System.out.println();
    }
    
    /**
     * Example 2: Photon and Light Calculations
     */
    public void photonExample() {
        System.out.println("=== PHOTON AND LIGHT CALCULATIONS ===");
        
        // Visible light spectrum
        double[] wavelengths = {400e-9, 500e-9, 600e-9, 700e-9}; // meters
        String[] colors = {"Violet", "Green", "Orange", "Red"};
        
        for (int i = 0; i < wavelengths.length; i++) {
            double energy = calc.calculatePhotonEnergy(wavelengths[i]);
            double frequency = PhysicsConstants.c / wavelengths[i];
            
            System.out.printf("%s light (%.0f nm):\n", colors[i], wavelengths[i] * 1e9);
            System.out.printf("  Energy: %.3f eV\n", energy / PhysicsConstants.eV);
            System.out.printf("  Frequency: %.3e Hz\n", frequency);
        }
        
        // X-ray photon
        double xrayEnergy = 10000 * PhysicsConstants.eV; // 10 keV
        double xrayWavelength = calc.calculatePhotonWavelength(xrayEnergy);
        System.out.printf("\n10 keV X-ray wavelength: %.3e m (%.3f Angstroms)\n", 
                         xrayWavelength, xrayWavelength * 1e10);
        
        System.out.println();
    }
    
    /**
     * Example 3: Relativistic Calculations
     */
    public void relativisticExample() {
        System.out.println("=== RELATIVISTIC CALCULATIONS ===");
        
        // Electron at different velocities
        double[] velocitiesFraction = {0.1, 0.5, 0.9, 0.99, 0.999};
        
        System.out.println("Electron relativistic effects:");
        System.out.println("v/c\tγ\tMass(MeV/c²)\tKE(MeV)");
        
        for (double vFraction : velocitiesFraction) {
            double velocity = vFraction * PhysicsConstants.c;
            double gamma = calc.calculateTimeDialation(velocity);
            double relativeMass = calc.calculateRelativisticMass(PhysicsConstants.m_e, velocity);
            double kineticEnergy = (gamma - 1) * PhysicsConstants.m_e * PhysicsConstants.c * PhysicsConstants.c;
            
            System.out.printf("%.3f\t%.3f\t%.3f\t\t%.3f\n", 
                             vFraction, gamma, 
                             relativeMass * PhysicsConstants.c * PhysicsConstants.c / (1e6 * PhysicsConstants.eV),
                             kineticEnergy / (1e6 * PhysicsConstants.eV));
        }
        
        System.out.println();
    }
    
    /**
     * Example 4: Quantum Mechanics - Particle in a Box
     */
    public void particleInBoxExample() {
        System.out.println("=== PARTICLE IN A BOX ===");
        
        double boxLength = 1e-9; // 1 nm box
        double mass = PhysicsConstants.m_e;
        
        System.out.printf("Electron in %.0f nm box:\n", boxLength * 1e9);
        
        // Energy levels for n = 1, 2, 3
        for (int n = 1; n <= 3; n++) {
            double energy = (n * n * PhysicsConstants.h * PhysicsConstants.h) / 
                           (8 * mass * boxLength * boxLength);
            
            System.out.printf("  E%d = %.3f eV\n", n, energy / PhysicsConstants.eV);
        }
        
        // Ground state de Broglie wavelength
        double momentum = Math.PI * PhysicsConstants.h / boxLength;
        double velocity = momentum / mass;
        double deBroglie = calc.calculateDeBroglieWavelength(mass, velocity);
        
        System.out.printf("Ground state de Broglie wavelength: %.3e m (%.1f nm)\n", 
                         deBroglie, deBroglie * 1e9);
        
        System.out.println();
    }
    
    /**
     * Example 5: Astronomical Calculations
     */
    public void astronomyExample() {
        System.out.println("=== ASTRONOMICAL CALCULATIONS ===");
        
        // Earth escape velocity
        double earthEscape = calc.calculateEscapeVelocity(PhysicsConstants.M_earth, PhysicsConstants.R_earth);
        System.out.printf("Earth escape velocity: %.1f km/s\n", earthEscape / 1000);
        
        // Sun escape velocity
        double sunEscape = calc.calculateEscapeVelocity(PhysicsConstants.M_sun, PhysicsConstants.R_sun);
        System.out.printf("Sun escape velocity: %.1f km/s\n", sunEscape / 1000);
        
        // Earth orbital velocity around Sun
        double earthOrbital = calc.calculateOrbitalVelocity(PhysicsConstants.M_sun, PhysicsConstants.AU);
        System.out.printf("Earth orbital velocity: %.1f km/s\n", earthOrbital / 1000);
        
        // Schwarzschild radius of Sun
        double sunBlackHole = PhysicsConstants.schwarzschildRadius(PhysicsConstants.M_sun);
        System.out.printf("Sun's Schwarzschild radius: %.1f km\n", sunBlackHole / 1000);
        
        // Time for light to travel from Sun to Earth
        double lightTime = PhysicsConstants.AU / PhysicsConstants.c;
        System.out.printf("Light travel time Sun→Earth: %.1f minutes\n", lightTime / 60);
        
        System.out.println();
    }
    
    /**
     * Example 6: Blackbody Radiation
     */
    public void blackbodyExample() {
        System.out.println("=== BLACKBODY RADIATION ===");
        
        // Different temperature objects
        double[] temperatures = {300, 5778, 10000}; // K
        String[] objects = {"Room temperature", "Sun surface", "Hot star"};
        
        for (int i = 0; i < temperatures.length; i++) {
            double temp = temperatures[i];
            double peakWavelength = calc.calculateWienDisplacement(temp);
            double totalPower = calc.calculateBlackbodyPower(temp, 1.0); // per m²
            
            System.out.printf("%s (%.0f K):\n", objects[i], temp);
            System.out.printf("  Peak wavelength: %.0f nm\n", peakWavelength * 1e9);
            System.out.printf("  Power density: %.1f W/m²\n", totalPower);
            
            // Determine color region
            if (peakWavelength < 400e-9) {
                System.out.println("  Color region: Ultraviolet");
            } else if (peakWavelength < 700e-9) {
                System.out.println("  Color region: Visible");
            } else {
                System.out.println("  Color region: Infrared");
            }
        }
        
        System.out.println();
    }
    
    /**
     * Example 7: Electromagnetic Field Calculations
     */
    public void electromagneticExample() {
        System.out.println("=== ELECTROMAGNETIC CALCULATIONS ===");
        
        // Coulomb force between proton and electron in hydrogen
        double protonElectronForce = calc.calculateCoulombForce(
            PhysicsConstants.e, -PhysicsConstants.e, PhysicsConstants.a_0);
        
        System.out.printf("Coulomb force in hydrogen atom: %.3e N\n", Math.abs(protonElectronForce));
        
        // Electric field of a proton at Bohr radius
        double electricField = PhysicsConstants.k_e * PhysicsConstants.e / 
                              (PhysicsConstants.a_0 * PhysicsConstants.a_0);
        
        System.out.printf("Electric field at Bohr radius: %.3e V/m\n", electricField);
        
        // Energy stored in electric field
        double fieldEnergy = 0.5 * PhysicsConstants.epsilon_0 * electricField * electricField;
        System.out.printf("Energy density in field: %.3e J/m³\n", fieldEnergy);
        
        System.out.println();
    }
    
    /**
     * Run all examples
     */
    public void runAllExamples() {
        System.out.println("SCIENTIFIC COMPUTING EXAMPLES");
        System.out.println("==============================\n");
        
        hydrogenAtomExample();
        photonExample();
        relativisticExample();
        particleInBoxExample();
        astronomyExample();
        blackbodyExample();
        electromagneticExample();
        
        System.out.println("=== PHYSICS CONSTANTS SUMMARY ===");
        System.out.println(PhysicsConstants.getAllConstants());
    }
    
    public static void main(String[] args) {
        ScientificExamples examples = new ScientificExamples();
        examples.runAllExamples();
    }
}
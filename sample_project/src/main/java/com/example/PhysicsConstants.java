package com.example;

/**
 * Comprehensive Physics Constants Library
 * Contains fundamental constants, derived constants, and conversion factors
 * All values are in SI units unless otherwise specified
 */
public class PhysicsConstants {
    
    // ========== FUNDAMENTAL CONSTANTS ==========
    
    // Speed of light in vacuum (m/s)
    public static final double SPEED_OF_LIGHT = 299792458.0;
    public static final double c = SPEED_OF_LIGHT;
    
    // Planck constant (J⋅s)
    public static final double PLANCK_CONSTANT = 6.62607015e-34;
    public static final double h = PLANCK_CONSTANT;
    
    // Reduced Planck constant (J⋅s)
    public static final double REDUCED_PLANCK = PLANCK_CONSTANT / (2 * Math.PI);
    public static final double h_bar = REDUCED_PLANCK;
    
    // Elementary charge (C)
    public static final double ELEMENTARY_CHARGE = 1.602176634e-19;
    public static final double e = ELEMENTARY_CHARGE;
    
    // Electron rest mass (kg)
    public static final double ELECTRON_MASS = 9.1093837015e-31;
    public static final double m_e = ELECTRON_MASS;
    
    // Proton rest mass (kg)
    public static final double PROTON_MASS = 1.67262192369e-27;
    public static final double m_p = PROTON_MASS;
    
    // Neutron rest mass (kg)
    public static final double NEUTRON_MASS = 1.67492749804e-27;
    public static final double m_n = NEUTRON_MASS;
    
    // Atomic mass unit (kg)
    public static final double ATOMIC_MASS_UNIT = 1.66053906660e-27;
    public static final double u = ATOMIC_MASS_UNIT;
    
    // Avogadro constant (mol⁻¹)
    public static final double AVOGADRO_NUMBER = 6.02214076e23;
    public static final double N_A = AVOGADRO_NUMBER;
    
    // Boltzmann constant (J/K)
    public static final double BOLTZMANN_CONSTANT = 1.380649e-23;
    public static final double k_B = BOLTZMANN_CONSTANT;
    
    // Gas constant (J/(mol⋅K))
    public static final double GAS_CONSTANT = 8.314462618;
    public static final double R = GAS_CONSTANT;
    
    // Vacuum permeability (H/m)
    public static final double VACUUM_PERMEABILITY = 4 * Math.PI * 1e-7;
    public static final double mu_0 = VACUUM_PERMEABILITY;
    
    // Vacuum permittivity (F/m)
    public static final double VACUUM_PERMITTIVITY = 1.0 / (mu_0 * c * c);
    public static final double epsilon_0 = VACUUM_PERMITTIVITY;
    
    // Gravitational constant (m³/(kg⋅s²))
    public static final double GRAVITATIONAL_CONSTANT = 6.67430e-11;
    public static final double G = GRAVITATIONAL_CONSTANT;
    
    // Standard acceleration of gravity (m/s²)
    public static final double STANDARD_GRAVITY = 9.80665;
    public static final double g = STANDARD_GRAVITY;
    
    // ========== DERIVED CONSTANTS ==========
    
    // Fine structure constant (dimensionless)
    public static final double FINE_STRUCTURE = e * e / (4 * Math.PI * epsilon_0 * h_bar * c);
    public static final double alpha = FINE_STRUCTURE;
    
    // Classical electron radius (m)
    public static final double CLASSICAL_ELECTRON_RADIUS = e * e / (4 * Math.PI * epsilon_0 * m_e * c * c);
    public static final double r_e = CLASSICAL_ELECTRON_RADIUS;
    
    // Bohr radius (m)
    public static final double BOHR_RADIUS = h_bar / (m_e * c * alpha);
    public static final double a_0 = BOHR_RADIUS;
    
    // Rydberg constant (m⁻¹)
    public static final double RYDBERG_CONSTANT = m_e * c * alpha * alpha / (2 * h);
    public static final double R_infinity = RYDBERG_CONSTANT;
    
    // Stefan-Boltzmann constant (W/(m²⋅K⁴))
    public static final double STEFAN_BOLTZMANN = 2 * Math.pow(Math.PI, 5) * Math.pow(k_B, 4) / 
                                                  (15 * Math.pow(h, 3) * c * c);
    public static final double sigma = STEFAN_BOLTZMANN;
    
    // Wien displacement constant (m⋅K)
    public static final double WIEN_DISPLACEMENT = h * c / (4.965114231 * k_B);
    public static final double b = WIEN_DISPLACEMENT;
    
    // Coulomb constant (N⋅m²/C²)
    public static final double COULOMB_CONSTANT = 1.0 / (4 * Math.PI * epsilon_0);
    public static final double k_e = COULOMB_CONSTANT;
    
    // ========== ATOMIC AND NUCLEAR CONSTANTS ==========
    
    // Electron volt (J)
    public static final double ELECTRON_VOLT = e;
    public static final double eV = ELECTRON_VOLT;
    
    // Hartree energy (J)
    public static final double HARTREE_ENERGY = 2 * RYDBERG_CONSTANT * h * c;
    public static final double E_h = HARTREE_ENERGY;
    
    // Nuclear magneton (J/T)
    public static final double NUCLEAR_MAGNETON = e * h_bar / (2 * m_p);
    public static final double mu_N = NUCLEAR_MAGNETON;
    
    // Bohr magneton (J/T)
    public static final double BOHR_MAGNETON = e * h_bar / (2 * m_e);
    public static final double mu_B = BOHR_MAGNETON;
    
    // ========== ASTRONOMICAL CONSTANTS ==========
    
    // Astronomical unit (m)
    public static final double ASTRONOMICAL_UNIT = 1.495978707e11;
    public static final double AU = ASTRONOMICAL_UNIT;
    
    // Light year (m)
    public static final double LIGHT_YEAR = c * 365.25 * 24 * 3600;
    public static final double ly = LIGHT_YEAR;
    
    // Parsec (m)
    public static final double PARSEC = AU / Math.tan(Math.toRadians(1.0 / 3600.0));
    public static final double pc = PARSEC;
    
    // Solar mass (kg)
    public static final double SOLAR_MASS = 1.98847e30;
    public static final double M_sun = SOLAR_MASS;
    
    // Earth mass (kg)
    public static final double EARTH_MASS = 5.9722e24;
    public static final double M_earth = EARTH_MASS;
    
    // Earth radius (m)
    public static final double EARTH_RADIUS = 6.371e6;
    public static final double R_earth = EARTH_RADIUS;
    
    // Solar radius (m)
    public static final double SOLAR_RADIUS = 6.96e8;
    public static final double R_sun = SOLAR_RADIUS;
    
    // Solar luminosity (W)
    public static final double SOLAR_LUMINOSITY = 3.828e26;
    public static final double L_sun = SOLAR_LUMINOSITY;
    
    // ========== PARTICLE PHYSICS CONSTANTS ==========
    
    // Muon mass (kg)
    public static final double MUON_MASS = 1.883531627e-28;
    public static final double m_mu = MUON_MASS;
    
    // Tau mass (kg)
    public static final double TAU_MASS = 3.16754e-27;
    public static final double m_tau = TAU_MASS;
    
    // W boson mass (kg)
    public static final double W_BOSON_MASS = 1.43332e-25;
    public static final double m_W = W_BOSON_MASS;
    
    // Z boson mass (kg)
    public static final double Z_BOSON_MASS = 1.62518e-25;
    public static final double m_Z = Z_BOSON_MASS;
    
    // Higgs boson mass (kg) - approximate
    public static final double HIGGS_MASS = 2.23e-25;
    public static final double m_H = HIGGS_MASS;
    
    // ========== CONVERSION FACTORS ==========
    
    // Energy conversions
    public static final double eV_TO_JOULE = eV;
    public static final double JOULE_TO_eV = 1.0 / eV;
    public static final double keV_TO_JOULE = 1000 * eV;
    public static final double MeV_TO_JOULE = 1e6 * eV;
    public static final double GeV_TO_JOULE = 1e9 * eV;
    
    // Mass-energy conversions
    public static final double AMU_TO_kg = u;
    public static final double AMU_TO_MeV = u * c * c / MeV_TO_JOULE;
    
    // Length conversions
    public static final double ANGSTROM = 1e-10;
    public static final double FERMI = 1e-15;
    public static final double INCH = 0.0254;
    public static final double FOOT = 0.3048;
    public static final double MILE = 1609.344;
    
    // Time conversions
    public static final double MINUTE = 60.0;
    public static final double HOUR = 3600.0;
    public static final double DAY = 86400.0;
    public static final double YEAR = 365.25 * DAY;
    
    // Temperature conversions (offset functions needed)
    public static final double CELSIUS_TO_KELVIN_OFFSET = 273.15;
    public static final double FAHRENHEIT_TO_CELSIUS_FACTOR = 5.0 / 9.0;
    public static final double FAHRENHEIT_TO_CELSIUS_OFFSET = 32.0;
    
    // Pressure conversions
    public static final double ATMOSPHERE = 101325.0; // Pa
    public static final double TORR = ATMOSPHERE / 760.0;
    public static final double BAR = 1e5; // Pa
    public static final double PSI = 6894.757; // Pa
    
    // ========== UTILITY METHODS ==========
    
    /**
     * Convert Celsius to Kelvin
     */
    public static double celsiusToKelvin(double celsius) {
        return celsius + CELSIUS_TO_KELVIN_OFFSET;
    }
    
    /**
     * Convert Kelvin to Celsius
     */
    public static double kelvinToCelsius(double kelvin) {
        return kelvin - CELSIUS_TO_KELVIN_OFFSET;
    }
    
    /**
     * Convert Fahrenheit to Celsius
     */
    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - FAHRENHEIT_TO_CELSIUS_OFFSET) * FAHRENHEIT_TO_CELSIUS_FACTOR;
    }
    
    /**
     * Convert Celsius to Fahrenheit
     */
    public static double celsiusToFahrenheit(double celsius) {
        return celsius / FAHRENHEIT_TO_CELSIUS_FACTOR + FAHRENHEIT_TO_CELSIUS_OFFSET;
    }
    
    /**
     * Convert energy from eV to Joules
     */
    public static double eVToJoules(double eV) {
        return eV * eV_TO_JOULE;
    }
    
    /**
     * Convert energy from Joules to eV
     */
    public static double joulesToeV(double joules) {
        return joules * JOULE_TO_eV;
    }
    
    /**
     * Calculate photon energy from wavelength
     * @param wavelength in meters
     * @return energy in Joules
     */
    public static double photonEnergy(double wavelength) {
        return h * c / wavelength;
    }
    
    /**
     * Calculate photon wavelength from energy
     * @param energy in Joules
     * @return wavelength in meters
     */
    public static double photonWavelength(double energy) {
        return h * c / energy;
    }
    
    /**
     * Calculate de Broglie wavelength
     * @param mass in kg
     * @param velocity in m/s
     * @return wavelength in meters
     */
    public static double deBroglieWavelength(double mass, double velocity) {
        return h / (mass * velocity);
    }
    
    /**
     * Calculate thermal velocity
     * @param temperature in Kelvin
     * @param mass in kg
     * @return velocity in m/s
     */
    public static double thermalVelocity(double temperature, double mass) {
        return Math.sqrt(3 * k_B * temperature / mass);
    }
    
    /**
     * Calculate escape velocity
     * @param mass in kg (planetary mass)
     * @param radius in meters
     * @return velocity in m/s
     */
    public static double escapeVelocity(double mass, double radius) {
        return Math.sqrt(2 * G * mass / radius);
    }
    
    /**
     * Calculate schwarzschild radius (black hole event horizon)
     * @param mass in kg
     * @return radius in meters
     */
    public static double schwarzschildRadius(double mass) {
        return 2 * G * mass / (c * c);
    }
    
    /**
     * Get all constants as a formatted string
     */
    public static String getAllConstants() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== FUNDAMENTAL PHYSICS CONSTANTS ===\n\n");
        
        sb.append("FUNDAMENTAL CONSTANTS:\n");
        sb.append(String.format("Speed of light (c): %.0f m/s\n", c));
        sb.append(String.format("Planck constant (h): %.3e J⋅s\n", h));
        sb.append(String.format("Elementary charge (e): %.3e C\n", e));
        sb.append(String.format("Electron mass (m_e): %.3e kg\n", m_e));
        sb.append(String.format("Proton mass (m_p): %.3e kg\n", m_p));
        sb.append(String.format("Boltzmann constant (k_B): %.3e J/K\n", k_B));
        sb.append(String.format("Gravitational constant (G): %.3e m³/(kg⋅s²)\n", G));
        sb.append(String.format("Standard gravity (g): %.3f m/s²\n", g));
        
        sb.append("\nDERIVED CONSTANTS:\n");
        sb.append(String.format("Fine structure constant (α): %.6f\n", alpha));
        sb.append(String.format("Bohr radius (a₀): %.3e m\n", a_0));
        sb.append(String.format("Stefan-Boltzmann (σ): %.3e W/(m²⋅K⁴)\n", sigma));
        sb.append(String.format("Coulomb constant (k_e): %.3e N⋅m²/C²\n", k_e));
        
        sb.append("\nASTRONOMICAL CONSTANTS:\n");
        sb.append(String.format("Astronomical Unit (AU): %.3e m\n", AU));
        sb.append(String.format("Light year (ly): %.3e m\n", ly));
        sb.append(String.format("Parsec (pc): %.3e m\n", pc));
        sb.append(String.format("Solar mass (M☉): %.3e kg\n", M_sun));
        sb.append(String.format("Earth mass (M⊕): %.3e kg\n", M_earth));
        
        return sb.toString();
    }
}
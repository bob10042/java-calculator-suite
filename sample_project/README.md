# Java Sample Project

A simple Java project with calculator functionality.

## Project Structure

```
sample_project/
├── src/
│   ├── main/java/com/example/
│   │   ├── App.java          # Main application
│   │   └── Calculator.java   # Calculator class
│   └── test/java/com/example/
│       └── CalculatorTest.java # Simple tests
├── build.sh                  # Build script
└── README.md                # This file
```

## Building and Running

### Build the project:
```bash
./build.sh
```

### Run the console application:
```bash
java -cp build/classes com.example.App
```

### Run the GUI application:
```bash
./run_gui.sh
```
Or manually:
```bash
java -cp build/classes com.example.AdvancedCalculatorGUI
```

### Run the Power Calculator GUI:
```bash
./run_power_gui.sh
```
Or manually:
```bash
java -cp build/classes com.example.PowerCalculatorGUI
```

### Run the Programmable Console:
```bash
./run_console.sh
```
Or manually:
```bash
java -cp build/classes com.example.ProgrammableConsole
```

### Run Scientific Examples:
```bash
./run_examples.sh
```
Or manually:
```bash
java -cp build/classes com.example.ScientificExamples
```

### Run Simple Console Calculator:
```bash
./run_simple_calc.sh
```
Or manually:
```bash
java -cp build/classes com.example.SimpleConsoleCalc
```

### Run tests:
```bash
java -cp build/classes -ea com.example.CalculatorTest
```

## Development

This project uses standard Maven directory structure but with a simple shell script for building. You can later convert it to use Maven or Gradle for dependency management.

## Features

### Console Application
- Basic calculator operations (add, subtract, multiply, divide)
- Simple unit tests
- Clean project structure

### GUI Application (Advanced Calculator Pro)
- **Professional Interface**: Full Swing GUI with toolbar and menus
- **Basic Operations**: Add, subtract, multiply, divide
- **Scientific Functions**: sin, cos, tan, square root
- **Memory Operations**: Store, recall, clear, add to memory
- **History Tracking**: View and save calculation history
- **File Operations**: Save/load history to files
- **Multiple Modes**: Standard and scientific calculator modes
- **Toolbar**: Quick access to common functions
- **Status Bar**: Real-time status updates
- **Color-coded Buttons**: Easy to distinguish number, operator, and function buttons

### Power Calculator GUI (Electrical Engineering)
- **DC Power Calculations**: P = V×I, P = V²/R
- **3-Phase Power Analysis**: Real, Apparent, and Reactive Power
- **Impedance Calculations**: Magnitude and Phase Angle
- **Mathematical Functions**: Power (x^y), Square, Cube
- **Professional Formulas**: All standard electrical engineering equations
- **Tabbed Interface**: Organized by calculation type
- **Input Validation**: Error handling for electrical constraints

### Programmable Console (Scientific Computing)
- **Physics Constants Library**: Complete fundamental and derived constants
- **Python Script Execution**: Run Python code with physics constants pre-loaded
- **Java Expression Evaluation**: Mathematical expressions with variables
- **Variable Storage**: Save and reuse calculation results
- **Command History**: Navigate previous commands with arrow keys
- **Multiple Modes**: Java expressions, Python scripts, physics calculator
- **Scientific Functions**: Comprehensive mathematical and physics functions
- **Terminal Interface**: Professional console with syntax highlighting

### Scientific Examples & Physics Library
- **Comprehensive Constants**: 50+ physics constants (c, h, e, etc.)
- **Quantum Mechanics**: Hydrogen atom, particle in box, de Broglie wavelength
- **Relativity**: Time dilation, length contraction, relativistic energy
- **Astronomy**: Escape velocities, orbital mechanics, blackbody radiation
- **Electromagnetism**: Coulomb forces, electric fields, energy calculations
- **Conversion Functions**: Temperature, energy units, wavelength/frequency
- **Real-world Examples**: Calculations for atoms, stars, and particles

### Simple Console Calculator (Terminal-based)
- **Command-line Interface**: Works in any terminal without GUI
- **Physics Constants Access**: Quick lookup of fundamental constants
- **Pre-programmed Calculations**: Electron energy, photon energy, etc.
- **Interactive Commands**: demo, constants, variables, help
- **No Dependencies**: Pure Java, works on any system
- **Educational Focus**: Perfect for learning physics constants

### Build System
- Simple shell script build automation
- Cross-platform compatibility
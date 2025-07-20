# Java Sample Project - Development Guide

## Project Overview

This is a comprehensive Java calculator project featuring multiple calculator interfaces, physics calculations, and scientific computing capabilities. The project includes GUI applications, console interfaces, and a programmable computing environment with physics constants.

## Project Structure

```
sample_project/
├── src/                           # Source code root
│   ├── main/java/com/example/     # Main application source
│   │   ├── App.java               # Basic console application entry point
│   │   ├── Calculator.java        # Core calculator with math/physics operations
│   │   ├── PhysicsConstants.java  # Comprehensive physics constants library
│   │   ├── AdvancedCalculatorGUI.java    # Professional Swing calculator GUI
│   │   ├── PowerCalculatorGUI.java       # Electrical engineering calculator GUI
│   │   ├── ProgrammableConsole.java      # Scientific programmable console
│   │   ├── ConsoleCalculator.java        # Basic console calculator
│   │   ├── SimpleConsoleCalc.java        # Terminal-based physics calculator
│   │   └── ScientificExamples.java       # Physics calculation examples
│   ├── test/java/com/example/     # Test source code
│   │   └── CalculatorTest.java    # Basic unit tests using assertions
│   └── resources/                 # Resource files (currently empty)
├── build/                         # Compiled class files
│   └── classes/com/example/       # Compiled .class files
├── build.sh                       # Build script (compile all sources)
├── test_calc.sh                   # Test script
├── run_gui.sh                     # Launch Advanced Calculator GUI
├── run_power_gui.sh              # Launch Power Calculator GUI  
├── run_console.sh                # Launch Programmable Console
├── run_console_calc.sh           # Launch Console Calculator
├── run_simple_calc.sh            # Launch Simple Console Calculator
├── run_examples.sh               # Run Scientific Examples
└── README.md                     # Existing project documentation
```

## Build System

### Primary Build Script
- **File**: `/home/bob43/java_projects/sample_project/build.sh`
- **Purpose**: Compiles all Java source files and tests
- **Build Process**:
  1. Creates `build/classes` directory
  2. Compiles main source files: `javac -d build/classes src/main/java/com/example/*.java`
  3. Compiles test files: `javac -cp build/classes -d build/classes src/test/java/com/example/*.java`

### Build Commands
```bash
# Build the entire project
./build.sh

# Manual compilation (if needed)
mkdir -p build/classes
javac -d build/classes src/main/java/com/example/*.java
javac -cp build/classes -d build/classes src/test/java/com/example/*.java
```

## Applications and Entry Points

### 1. Basic Console Application
- **Main Class**: `com.example.App`
- **Launch Script**: None (use java command directly)
- **Command**: `java -cp build/classes com.example.App`
- **Purpose**: Simple "Hello World" with basic calculator demo

### 2. Advanced Calculator GUI
- **Main Class**: `com.example.AdvancedCalculatorGUI`
- **Launch Script**: `./run_gui.sh`
- **Manual Command**: `java -cp build/classes com.example.AdvancedCalculatorGUI`
- **Features**: 
  - Professional Swing GUI with toolbar and menus
  - Scientific functions (sin, cos, tan, sqrt)
  - Memory operations and calculation history
  - File operations for saving/loading history

### 3. Electrical Power Calculator GUI
- **Main Class**: `com.example.PowerCalculatorGUI`
- **Launch Script**: `./run_power_gui.sh`
- **Manual Command**: `java -cp build/classes com.example.PowerCalculatorGUI`
- **Features**:
  - DC power calculations (P = V×I, P = V²/R)
  - 3-phase power analysis
  - Impedance calculations
  - Tabbed interface for different calculation types

### 4. Scientific Programmable Console
- **Main Class**: `com.example.ProgrammableConsole`
- **Launch Script**: `./run_console.sh`
- **Manual Command**: `java -cp build/classes com.example.ProgrammableConsole`
- **Features**:
  - Physics constants library with 50+ constants
  - Java expression evaluation with variables
  - Python script execution capabilities
  - Command history and terminal interface

### 5. Console Calculator
- **Main Class**: `com.example.ConsoleCalculator`
- **Launch Script**: `./run_console_calc.sh`
- **Manual Command**: `java -cp build/classes com.example.ConsoleCalculator`
- **Purpose**: Basic console-based calculator interface

### 6. Simple Console Calculator
- **Main Class**: `com.example.SimpleConsoleCalc`
- **Launch Script**: `./run_simple_calc.sh`
- **Manual Command**: `java -cp build/classes com.example.SimpleConsoleCalc`
- **Features**:
  - Terminal-based interface (no GUI dependencies)
  - Physics constants access
  - Educational physics calculations

### 7. Scientific Examples
- **Main Class**: `com.example.ScientificExamples`
- **Launch Script**: `./run_examples.sh`
- **Manual Command**: `java -cp build/classes com.example.ScientificExamples`
- **Purpose**: Demonstrates physics calculations and real-world examples

## Testing

### Test Framework
- **Framework**: Java assertions (not JUnit)
- **Test File**: `/home/bob43/java_projects/sample_project/src/test/java/com/example/CalculatorTest.java`
- **Test Script**: `/home/bob43/java_projects/sample_project/test_calc.sh`

### Running Tests
```bash
# Run all tests with assertions enabled
java -cp build/classes -ea com.example.CalculatorTest

# Run quick test script
./test_calc.sh
```

### Test Coverage
Current tests cover basic calculator operations:
- Addition, subtraction, multiplication, division
- Uses Java assertions for validation

## Key Configuration Files

### Shell Scripts
All launch scripts follow the same pattern:
1. Build the project using `./build.sh`
2. Launch the specific main class
3. Include descriptive echo statements

### No External Dependencies
- **Build System**: Custom shell script (no Maven/Gradle)
- **Dependencies**: Pure Java with Swing for GUI
- **Testing**: Built-in Java assertions
- **Runtime**: Standard JDK (no external libraries)

## Architecture Overview

### Core Components

1. **Calculator Class** (`Calculator.java`)
   - Central calculation engine
   - Basic arithmetic operations
   - Advanced electrical power calculations
   - Physics calculations using PhysicsConstants
   - Relativistic calculations
   - Astronomical calculations

2. **PhysicsConstants Class** (`PhysicsConstants.java`)
   - Comprehensive physics constants library
   - Fundamental constants (c, h, e, etc.)
   - Derived constants (fine structure, Bohr radius)
   - Astronomical constants (solar mass, Earth mass)
   - Particle physics constants
   - Utility methods for conversions
   - 50+ physics constants with SI units

3. **GUI Applications**
   - Built with Java Swing
   - Professional interfaces with menus and toolbars
   - Event-driven architecture
   - Separate specialized interfaces for different use cases

4. **Console Applications**
   - Command-line interfaces
   - Interactive input/output
   - Programmable features with variables and scripting

### Component Interactions

```
App.java (Entry) 
    ↓
Calculator.java (Core Engine)
    ↓
PhysicsConstants.java (Constants & Utilities)
    ↑
GUI Applications (AdvancedCalculatorGUI, PowerCalculatorGUI)
Console Applications (ProgrammableConsole, SimpleConsoleCalc)
Scientific Examples (ScientificExamples)
```

## Development Workflow

### Common Development Tasks

1. **Adding New Features**
   ```bash
   # Edit source files in src/main/java/com/example/
   # Build project
   ./build.sh
   # Test manually with appropriate run script
   ```

2. **Adding New GUI Components**
   - Extend existing GUI classes or create new ones
   - Follow Swing patterns used in AdvancedCalculatorGUI
   - Create corresponding run script

3. **Adding Physics Calculations**
   - Add constants to PhysicsConstants.java
   - Add calculation methods to Calculator.java
   - Create examples in ScientificExamples.java

4. **Testing Changes**
   ```bash
   # Run existing tests
   ./build.sh && java -cp build/classes -ea com.example.CalculatorTest
   
   # Test specific applications
   ./run_gui.sh
   ./run_console.sh
   # etc.
   ```

### Code Organization Principles

- **Single Package**: All classes in `com.example` package
- **Separation of Concerns**: 
  - Calculator.java for business logic
  - PhysicsConstants.java for data and utilities
  - Separate classes for different interfaces
- **No External Dependencies**: Pure Java approach
- **Cross-platform Compatibility**: Uses standard Java features

## Quick Start Guide

1. **Build the project**:
   ```bash
   ./build.sh
   ```

2. **Run the main GUI calculator**:
   ```bash
   ./run_gui.sh
   ```

3. **Try the programmable console**:
   ```bash
   ./run_console.sh
   ```

4. **Run physics examples**:
   ```bash
   ./run_examples.sh
   ```

5. **Run tests**:
   ```bash
   java -cp build/classes -ea com.example.CalculatorTest
   ```

This project demonstrates a well-structured Java application with multiple interfaces, comprehensive physics calculations, and a simple but effective build system suitable for educational and scientific computing purposes.
#!/bin/bash

echo "Building and running Advanced Calculator GUI..."

# Build the project
./build.sh

# Run the GUI application
echo "Starting GUI application..."
java -cp build/classes com.example.AdvancedCalculatorGUI
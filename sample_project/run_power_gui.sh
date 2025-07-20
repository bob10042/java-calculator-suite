#!/bin/bash

echo "Building and running Electrical Power Calculator GUI..."

# Build the project
./build.sh

# Run the Power Calculator GUI application
echo "Starting Power Calculator GUI..."
java -cp build/classes com.example.PowerCalculatorGUI
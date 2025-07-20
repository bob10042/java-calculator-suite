#!/bin/bash

echo "Building and running Simple Console Calculator..."

# Build the project
./build.sh

# Run the Simple Console Calculator
echo "Starting Simple Console Calculator..."
java -cp build/classes com.example.SimpleConsoleCalc
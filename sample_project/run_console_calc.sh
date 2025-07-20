#!/bin/bash

echo "Building and running Console Calculator..."

# Build the project
./build.sh

# Run the Console Calculator
echo "Starting Console Calculator..."
java -cp build/classes com.example.ConsoleCalculator
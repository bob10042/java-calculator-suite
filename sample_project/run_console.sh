#!/bin/bash

echo "Building and running Scientific Programmable Console..."

# Build the project
./build.sh

# Run the Console application
echo "Starting Programmable Console..."
java -cp build/classes com.example.ProgrammableConsole
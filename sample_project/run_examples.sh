#!/bin/bash

echo "Building and running Scientific Examples..."

# Build the project
./build.sh

# Run the Scientific Examples
echo "Running physics calculations examples..."
java -cp build/classes com.example.ScientificExamples
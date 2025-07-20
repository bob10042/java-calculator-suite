#!/bin/bash

echo "Testing simple calculations..."

./build.sh

# Test with a single calculation
echo "c" | java -cp build/classes com.example.ConsoleCalculator
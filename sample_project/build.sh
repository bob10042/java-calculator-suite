#!/bin/bash

# Simple Java build script
echo "Building Java project..."

# Create build directory
mkdir -p build/classes

# Compile source files
javac -d build/classes src/main/java/com/example/*.java

# Compile test files
javac -cp build/classes -d build/classes src/test/java/com/example/*.java

echo "Build complete!"
echo ""
echo "To run the application:"
echo "java -cp build/classes com.example.App"
echo ""
echo "To run tests:"
echo "java -cp build/classes -ea com.example.CalculatorTest"
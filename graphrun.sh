#!/bin/bash

# Clean previous build
rm -rf bin/
mkdir -p bin

# Compile all Java files
javac -d bin -cp "lib/gson-2.13.2.jar" $(find src/main/java -name "*.java")

# If compilation was successful, run the program
if [ $? -eq 0 ]; then
    java -cp "bin:lib/gson-2.13.2.jar" com.graphproject.main.Main
else
    echo "Build failed."
fi

#!/bin/bash
rm -r ./*.class && rm -r ./characters/*.class && rm -r ./graph/*.class && rm -r ./projectexceptions/*.class && rm -r ./geom/*.class && rm -r ./graphviewer/*.class && rm -r ./math/*.class
javac Main.java
java Main
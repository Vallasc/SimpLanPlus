#!/bin/sh

DIR="./compiled/"
JAR="simp-lan-plus.jar"

rm -rf "$DIR"
mkdir "$DIR"
# ; windows, in *nix it is :
javac -target 1.8 -source 1.8 -d "$DIR" -cp lib/antlr-4.9.2-complete.jar:src src/com/unibo/ci/Main.java
unzip lib/antlr-4.9.2-complete.jar -d "$DIR"
cd "$DIR"
jar cfe "$JAR" com.unibo.ci.Main ./
mv *.jar ../
cd ..
echo "MAKE JAR: done\n"
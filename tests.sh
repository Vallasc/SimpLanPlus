#!/bin/sh

rm -rf compiled 
mkdir compiled
# ; windows, in *nix it is :
javac -target 1.8 -source 1.8 -d compiled -cp lib/antlr-4.9.2-complete.jar:src src/com/unibo/ci/test/Test.java
echo "Running"
java -cp lib/antlr-4.9.2-complete.jar:compiled com.unibo.ci.test.Test "$@"

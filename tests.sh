#!/bin/sh

rm -rf compiled 
mkdir compiled
# ; windows, in *nix it is :
javac -d compiled -cp lib/antlr-4.9.2-complete.jar:src src/com/unibo/ci/test/Test.java
java -cp lib/antlr-4.9.2-complete.jar:compiled com.unibo.ci.test.Test

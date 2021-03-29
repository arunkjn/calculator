# Calculator
This project implements an RPN calculator (Reverse polish notation). https://en.wikipedia.org/wiki/Reverse_Polish_notation

There is currently a CLI implementation and in future it will to ported to a web application environment.

## requirement
The project requires a minimum of JDK 11 to compile.

## building
If you are on a unix environment then execute the `./build.sh` script in the root of this project. It will install the required version of maven and build the project.

The build generates a bundled jar with all dependencies and you can run it with -
```shell
java -jar target/calculator-1.0-SNAPSHOT-jar-with-dependencies.jar
```
It also generates a code coverage report with the jacoco plugin which can be viewed in `target/site/jacoco/index.html`.
The build is currently missing relese and version management using `maven-release-plugin`

## assumptions
1. When an operator fails to execute due to arithmetic error such as divide by zero, the operands are put back on stack and a error message is shown.
2. If the user enters an invalid token in the string it is treated as an unsupported operator. A warning message is shown, and the operator is ignored. The rest of input string continues to process normally.
3. A numeric input is expected from user in the form of a decimal formatted string with an optional (-) sign. Any other formats including scientific notation will not be recognized.
4. There is no limit implemented to the stack size and undo stack size. Althought there must ideally be some sane limit in production web app.


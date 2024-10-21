package com.cliframe;

public class Main {
    public static void main( String[] args ) { // mvn clean validate compile site assembly:assembly -DdescriptorId=jar-with-dependencies exec:exec
        System.out.println("\nIf you are seeing this run ansi.cmd and rerun the program (⚠Windows)");
        System.out.print("\033c\033[2J");
        new App();
    }
}

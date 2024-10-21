package com.cliframe;

import java.util.Scanner;

public class App {
    public App() {
        //new Demo();

        Scanner scanner = new Scanner(System.in);
        Frame frame = new Frame(12, 100, Frame.borderBuilder(1));

        frame.setWindowString(0, true, "[ CLI FRAME ]"); 
        frame.setWindowString(2, true, "This is a framework to create frames in a cli program");
        frame.divider(4);
        frame.setWindowString(6, 3, "- Single and double frame ('─', '═')");
        frame.setWindowString(7, 3, "- Add strings (supports x and y position or auto center)");
        frame.setWindowString(8, 3, "- Different modes to clear the frame");
        frame.setWindowString(9, 3, "- Horizontal divisors");

        frame.print();
        scanner.nextLine();
        scanner.close();
    }
}

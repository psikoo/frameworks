package com.cliframe;

import java.util.Scanner;

public class Demo {
    public Demo() {
        Scanner scanner = new Scanner(System.in);

        Frame frameMode0 = new Frame(10, 20, Frame.borderBuilder(0));
        frameMode0.print();
        System.out.println("mode 0 frame");
        scanner.nextLine();

        Frame frameMode1 = new Frame(15, 100, Frame.borderBuilder(1));
        frameMode1.print();
        System.out.println("mode 1 frame");
        scanner.nextLine();

        frameMode1.setWindowString(1, 1, "Text starting at 1,1");
        frameMode1.setWindowString(3, 10, "Text starting at 3,10").print();
        scanner.nextLine();

        frameMode1.setWindowEmptyString(1, 1, 5).print();
        System.out.println("Removed 5 chars starting at 1,1");
        scanner.nextLine();
        
        frameMode1.setWindowString(0, true, "[ Auto centered text in the border! ]").print(); 
        scanner.nextLine();

        frameMode1.divider(2);
        frameMode1.divider(10).print();
        System.out.println("Automatic divisors at y2 and y10");
        scanner.nextLine();

        frameMode1.empty("keepBorderChar").print();
        System.out.println("Delete everything that is not a border");
        scanner.nextLine();

        frameMode1.empty("keepBorderIntact").print();
        System.out.println("Delete everything that is not in the sides but keep border as is");
        scanner.nextLine();

        frameMode1.empty("keepBorderString").print();
        System.out.println("Delete everything that is not in the sides and reset every border piece that is not part of a string");
        scanner.nextLine();
        scanner.close();
    }
}

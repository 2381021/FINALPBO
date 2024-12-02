package view;

import java.util.Scanner;

public class InputHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static String input(String prompt) {
        System.out.print(prompt + " : ");
        return scanner.nextLine();
    }
}

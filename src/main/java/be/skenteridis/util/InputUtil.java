package be.skenteridis.util;

import java.util.Scanner;

public class InputUtil {
    private final Scanner scanner;

    public InputUtil() {
        scanner = new Scanner(System.in);
    }
    public String getValidString(String prompt) {
        String s;
        while (true) {
            System.out.print(prompt + ": ");
            s = scanner.nextLine();
            if(s.isEmpty()) System.out.println("Valid value required!");
            else break;
        }
        return s;
    }
    public int getInt(String prompt) {
        int n;
        while (true) {
            try {
                n = Integer.parseInt(getValidString(prompt));
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number!");
            }
        }
        return n;
    }
    public void close() {
        scanner.close();
    }
}
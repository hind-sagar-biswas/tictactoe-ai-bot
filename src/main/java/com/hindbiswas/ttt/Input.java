package com.hindbiswas.ttt;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

/**
 * Input
 */
public class Input {
    public final Scanner sc = new Scanner(System.in);

    public String string(String prompt) {
        System.out.print(prompt + ": ");
        return sc.nextLine();
    }

    public char character(String prompt) {
        return string(prompt).charAt(0);
    }


    public char character(String prompt, char def) {
        try {
            return string(prompt).charAt(0);
        } catch (Exception e) {
            sc.next();
            return def;
        }
    }

    public char character(String prompt, Predicate<Character> condition) {
        while (true) {
            char input = character(prompt);
            if (condition.test(input)) {
                return input;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }


    public char character(String prompt, Predicate<Character> condition, char def) {
        while (true) {
            char input = character(prompt, def);
            if (condition.test(input)) {
                return input;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public int integer(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                return sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public int integer(String prompt, Predicate<Integer> condition) {
        while (true) {
            int input = integer(prompt);
            if (condition.test(input)) {
                return input;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    public int integer(String prompt, Predicate<Integer> condition, String errorMessage,
            String successMessage) {
        int input = integer(prompt);
        if (condition.test(input)) {
            System.out.println(successMessage);
            return input;
        } else {
            System.out.println(errorMessage);
            return -1;
        }
    }
}

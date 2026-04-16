package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Library Management System
 * Allows borrowing books, calculates fines for late returns.
 * Assumptions:
 * - Allowed borrow days per book: 7
 * - Fine: $1.0 per day late per book
 */
public class App {
    private static final int ALLOWED_DAYS = 7;
    private static final double FINE_PER_DAY = 1.0;

    static class Book {
        private final String title;

        Book(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return title;
        }
    }

    static class User {
        private final String name;
        private final String id;
        private final List<Book> issuedBooks;

        User(String name, String id, List<Book> issuedBooks) {
            this.name = name;
            this.id = id;
            this.issuedBooks = new ArrayList<>(issuedBooks);
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public List<Book> getIssuedBooks() {
            return issuedBooks;
        }
    }

    public static double calculateFine(int numBooks, int daysBorrowed) {
        if (daysBorrowed <= ALLOWED_DAYS) {
            return 0.0;
        }
        int lateDays = daysBorrowed - ALLOWED_DAYS;
        return numBooks * lateDays * FINE_PER_DAY;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Library Management System ===");
        System.out.print("Enter user name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            name = "Unknown User";
        }

        System.out.print("Enter user ID: ");
        String id = scanner.nextLine().trim();
        if (id.isEmpty()) {
            id = "UNKNOWN";
        }

        System.out.print("Enter number of books issued: ");
        int numBooks = 0;
        while (numBooks <= 0) {
            try {
                numBooks = Integer.parseInt(scanner.nextLine().trim());
                if (numBooks <= 0) {
                    System.out.print("Please enter a positive number: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter positive integer: ");
            }
        }

        System.out.print("Enter days borrowed: ");
        int daysBorrowed = 0;
        while (daysBorrowed <= 0) {
            try {
                daysBorrowed = Integer.parseInt(scanner.nextLine().trim());
                if (daysBorrowed <= 0) {
                    System.out.print("Please enter a positive number: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter positive integer: ");
            }
        }

        // Generate dummy books
        List<Book> books = new ArrayList<>();
        for (int i = 1; i <= numBooks; i++) {
            books.add(new Book("Book " + i));
        }

        User user = new User(name, id, books);
        double totalFine = calculateFine(numBooks, daysBorrowed);

        // Display results
        System.out.println("\n=== Borrowing Status ===");
        System.out.println("User Name: " + user.getName());
        System.out.println("User ID: " + user.getId());
        System.out.println("Books Issued (" + numBooks + "):");
        for (Book book : books) {
            System.out.println("  - " + book);
        }
        System.out.printf("Days Borrowed: %d%n", daysBorrowed);
        if (totalFine > 0) {
            System.out.printf("Total Fine: $%.2f (%.0f days late)%n", totalFine, Math.max(0, daysBorrowed - ALLOWED_DAYS));
        } else {
            System.out.println("Total Fine: $0.00 (No late fees)");
        }

        scanner.close();
    }
}

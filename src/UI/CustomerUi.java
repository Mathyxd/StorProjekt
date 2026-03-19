package UI;


import main.*;
import Model.*;
import util.*;
import File.*;

import java.util.List;
import java.util.Scanner;


public class CustomerUi {

    private final Scanner scanner;

    public CustomerUi(Scanner scanner) {
        this.scanner = scanner;
    }

    private Customer createCustomer(String customerName, String email) {
        System.out.println("Vælg kundetype:");
        System.out.println("1. Normal");
        System.out.println("2. VIP");
        System.out.println("3. Employee");

        int choice = readInt("Vælg kundetype: ");

        switch (choice) {
            case 1:
                return new NormalCustomer(customerName, email);
            case 2:
                return new VIPCustomer(customerName, email);
            case 3:
                return new EmployeeCustomer(customerName, email);
            default:
                System.out.println("Ugyldigt valg. Normal kunde vælges automatisk.");
                return new NormalCustomer(customerName, email);
        }
    }

    private String readEmail() {
        while (true) {
            String email = readText("Email: ");
            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return email;
            }
            System.out.println("Ugyldig email. Prøv igen.");
        }
    }

    public void showCustomerOverview() {
        List<String> customers = FileHandler.loadCustomer();

        if (customers.isEmpty()) {
            System.out.println("Ingen kunder er blevet registreret endnu. ");
            pressEnterToContinue();
            return;
        }
        System.out.println("\n=== Kundeoversigt ===");
        for (String entry : customers) {
            String[] parts = entry.split(",");
            System.out.println("Navn: " + parts[0] + " | Email: " + parts[1] + " | Type: " + parts[2]);
        }
        System.out.println("Antal kunder: " + customers.size());
        pressEnterToContinue();
    }
    private void exitProgram() {
        running = false;
        System.out.println("Programmet lukker.");
    }

    private int readInt(String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            }

            System.out.println("Skriv et gyldigt tal.");
            scanner.nextLine();
        }
    }

    private String readText(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

}

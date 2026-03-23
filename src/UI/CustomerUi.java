package UI;


import main.*;
import Model.*;
import util.*;
import File.*;

import java.util.List;
import java.util.Scanner;


public class CustomerUi extends BaseUi {

    public CustomerUi(Scanner scanner) {
        super(scanner);
    }

    public Customer createCustomer(String customerName, String email) {
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
                System.out.println(YELLOW + "Ugyldigt valg. Normal kunde vælges automatisk." + RESET);
                return new NormalCustomer(customerName, email);
        }
    }

    public String readEmail() {
        while (true) {
            String email = readText("Email: ");
            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return email;
            }
            System.out.println(YELLOW + "Ugyldig email. Prøv igen." + RESET);
        }
    }

    public void showCustomerOverview() {
        List<String> customers = FileHandler.loadCustomer();

        if (customers.isEmpty()) {
            System.out.println(YELLOW + "Ingen kunder er blevet registreret endnu. " + RESET);
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
}

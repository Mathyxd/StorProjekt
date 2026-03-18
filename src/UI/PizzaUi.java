package UI;
import Service.OrderManager;
import main.*;
import Model.*;
import util.*;
import File.*;

import java.util.ArrayList;
import java.util.Scanner;

public class PizzaUi {

    private final Scanner scanner;
    private final OrderManager orderManager;
    private boolean running;

    public PizzaUi() {
        scanner = new Scanner(System.in);
        orderManager = new OrderManager();
        running = true;
    }

    public void start() {
        while (running) {
            showMainMenu();
            int choice = readInt("Vælg et menupunkt: ");
            handleChoice(choice);
        }
    }

    private void showMainMenu() {
        System.out.println();
        System.out.println("=== Mario's Pizzabar ===");
        System.out.println("1. Vis menukort");
        System.out.println("2. Opret ordre");
        System.out.println("3. Vis aktive ordrer");
        System.out.println("4. Marker ordre som klar");
        System.out.println("5. Afslut");
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                showMenuCard();
                break;
            case 2:
                createOrder();
                break;
            case 3:
                showActiveOrders();
                break;
            case 4:
                markOrderReady();
                break;
            case 5:
                exitProgram();
                break;
            default:
                System.out.println("Ugyldigt valg. Prøv igen.");
        }
    }

    private void showMenuCard() {
        System.out.println("Vis menukort her.");
        System.out.println("Denne metode skal senere hente pizzaer fra model/service.");
    }

    private void createOrder() {
        String customerName = readText("Kundenavn: ");
        int pizzaNumber = readInt("Pizzanummer: ");
        int quantity = readInt("Antal: ");

        System.out.println("Opret ordre her for " + customerName + ".");
        System.out.println("Valgt pizza: " + pizzaNumber + ", antal: " + quantity);
        System.out.println("OrderManager skal senere bruges til at gemme ordren.");
    }

    private void showActiveOrders() {
        System.out.println("Vis aktive ordrer her.");
        System.out.println("OrderManager skal senere levere listen.");
    }

    private void markOrderReady() {
        int orderNumber = readInt("Ordrenummer: ");
        System.out.println("Marker ordre " + orderNumber + " som klar her.");
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

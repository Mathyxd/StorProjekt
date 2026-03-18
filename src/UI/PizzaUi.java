package UI;
import Service.OrderManager;
import main.*;
import Model.*;
import util.*;
import File.*;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class PizzaUi {

    private final Scanner scanner;
    private final OrderManager orderManager;
    private final Menu menu;
    private boolean running;


    public PizzaUi() {
        scanner = new Scanner(System.in);
        orderManager = new OrderManager();
        running = true;
        menu = new Menu();
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
        System.out.println("5. Vis ordreHistoirk");
        System.out.println("6. Afslut");
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
            case 5: showOrderHistory();
            break;
            case 6:
                exitProgram();
                break;
            default:
                System.out.println("Ugyldigt valg. Prøv igen.");
        }
    }

    private void showMenuCard() {
        System.out.println("=== Menukort ===");
        for (Pizza pizza : menu.getPizzas()) {
            System.out.println(
                    pizza.getNumber() + ". " +
                            pizza.getName() + " - " +
                            pizza.getIngredients() + " - " +
                            pizza.getPrice() + " kr"
            );
        }
        pressEnterToContinue();
    }

    private void createOrder() {
        String customerName = readText("Kundenavn: ");
        int pizzaNumber = readInt("Pizzanummer: ");
        int quantity = readInt("Antal: ");

        Pizza pizza = menu.findPizzaByNumber(pizzaNumber);

        if (pizza == null) {
            System.out.println("Pizza findes ikke.");
            return;
        }

        Customer customer = new NormalCustomer(customerName);
        LocalDateTime pickupTime = LocalDateTime.now().plusMinutes(20);

        Order order = orderManager.createOrder(customer, pizza, quantity, pickupTime);

        System.out.println("Ordre oprettet: #" + order.getOrderNumber());
        pressEnterToContinue();
    }

    private void showActiveOrders() {
        var orders = orderManager.getActiveOrdersSorted();

        if (orders.isEmpty()) {
            System.out.println("Ingen aktive ordrer.");
            return;
        }

        for (Order order : orders) {
            System.out.println(
                    "Ordre #" + order.getOrderNumber() +
                            " | Pizza: " + order.getPizza().getName() +
                            " | Antal: " + order.getQuantity() +
                            " | Status: " + order.getStatus() +
                            " | Klar: " + order.getPickupTime()
            );
        }
        pressEnterToContinue();
    }

    private void markOrderReady() {
        int orderNumber = readInt("Ordrenummer: ");
        boolean success = orderManager.markOrderAsReady(orderNumber);

        if (success) {
            System.out.println("Ordren er markeret som klar.");
            FileHandler.saveOrder(orderManager.findOrder(orderNumber));
        } else {
            System.out.println("Ordren blev ikke fundet.");
        }
        pressEnterToContinue();
    }
    public void showOrderHistory() {
        List<String> orders = FileHandler.loadOrders();

        if (orders.isEmpty()) {
            System.out.println("Ingen ordrer i historikken endnu.");
            pressEnterToContinue();
            return;
        }
        System.out.println("\n===OrdreHisotirk===");
        double totalRevenue = 0;
        for (String order : orders) {
            String[] parts = order.split(",");
            double price = Double.parseDouble(parts[4]);
            totalRevenue += price;
                    System.out.println(
                            "Kunde: " + parts[0] +
                                    " | Type: " + parts[1] +
                                    " | Pizza: " + parts[2] +
                                    " | Tidspunkt: " + parts[3] +
                                    " | Pris: " + parts[4] + " kr"
                    );
        }
        System.out.println("---------");
        System.out.println("Samlet omsætning: " + String.format("%.2f", totalRevenue) + "kr");
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

    private void pressEnterToContinue() {
        System.out.println("\nTryk Enter for at fortsætte...");
        scanner.nextLine();
    }
}

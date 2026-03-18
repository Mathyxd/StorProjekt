package UI;
import Service.OrderManager;
import main.*;
import Model.*;
import util.*;
import File.*;
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
        Size size = readSize();

        Order order = orderManager.createOrder(customer, pizza, size, quantity, pickupTime);

        System.out.println("Ordre oprettet: #" + order.getOrderNumber());
        pressEnterToContinue();
    }
    private Size readSize() {
        System.out.println("Vælg størrelse:");
        System.out.println("1. Small");
        System.out.println("2. Medium");
        System.out.println("3. Large");

        int choice = readInt("Vælg størrelse: ");

        switch (choice) {
            case 1:
                return Size.SMALL;
            case 2:
                return Size.MEDIUM;
            case 3:
                return Size.LARGE;
            default:
                System.out.println("Ugyldigt valg. Medium vælges automatisk.");
                return Size.MEDIUM;
        }
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
        } else {
            System.out.println("Ordren blev ikke fundet.");
        }
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

package UI;
import Service.OrderManager;
import Service.PaymentService;
import main.*;
import Model.*;
import util.*;
import File.*;

import java.util.Scanner;

public class PizzaUi {
    private final Scanner scanner;
    private final OrderUi orderUi;
    private final CustomerUi customerUi;
    private final MenuUi menuUi;
    private boolean running;


    public PizzaUi() {
        scanner = new Scanner(System.in);
        OrderManager orderManager = new OrderManager();
        PaymentService paymentService = new PaymentService();
        Menu menu = new Menu();

        orderUi = new OrderUi(scanner, orderManager, paymentService, menu);
        customerUi = new CustomerUi(scanner);
        menuUi = new MenuUi(scanner, menu);
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
        System.out.println("5. Marker ordre som leveret");
        System.out.println("6. Vis ordre Historik");
        System.out.println("7. Vis kundeoversigt");
        System.out.println("8. Afslut");
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
                markOrderComplete();
                break;
            case 6:
                showOrderHistory();
                break;
            case 7:
                showCustomerOverview();
                break;
            case 8:
                exitProgram();
                break;
            default:
                System.out.println("Ugyldigt valg. Prøv igen.");
        }
    }




    private double readDouble(String message) {
        while (true) {
            System.out.print(message);
            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            }

            System.out.println("Skriv et gyldigt beløb.");
            scanner.nextLine();
        }
    }






    private void pressEnterToContinue() {
        System.out.println("\nTryk Enter for at fortsætte...");
        scanner.nextLine();
    }

}









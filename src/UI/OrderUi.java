package UI;

import Service.*;
import main.*;
import Model.*;
import util.*;
import File.*;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;


public class
OrderUi extends BaseUi {

    private final OrderManager orderManager;
    private final PaymentService paymentService;
    private final Menu menu;
    private final CustomerUi customerUi;
    private final MenuUi menuUi;

    public OrderUi(Scanner scanner, OrderManager orderManager, PaymentService paymentService, Menu menu, CustomerUi customerUi, MenuUi menuUi) {
        super(scanner);
        this.orderManager = orderManager;
        this.paymentService = paymentService;
        this.menu = menu;
        this.customerUi = customerUi;
        this.menuUi = menuUi;
    }

    public void createOrder() {
        String customerName = readText("Kundenavn: ");
        String email = customerUi.readEmail();
        int pizzaNumber = readInt("Pizzanummer: ");
        int quantity = readInt("Antal: ");

        Pizza pizza = menu.findPizzaByNumber(pizzaNumber);

        if (pizza == null) {
            System.out.println(YELLOW + "Pizza findes ikke." + RESET);
            return;
        }

        Customer customer = customerUi.createCustomer(customerName, email);
        LocalDateTime pickupTime = LocalDateTime.now().plusMinutes(20);
        Size size = menuUi.readSize();

        Order order = orderManager.createOrder(customer, pizza, size, quantity, pickupTime);
        double total = paymentService.calculateTotal(order);
        System.out.println(GREEN + "Ordre oprettet: #" + order.getOrderNumber() + RESET);
        System.out.println("Samlet pris: " + String.format("%.2f", total) + " kr");


        double amountPaid = readDouble("Betalt beløb: ");

        if (paymentService.processPayment(order, amountPaid)) {
            double change = paymentService.calculateChange(order, amountPaid);
            System.out.println(GREEN + "Betaling godkendt." + RESET);
            System.out.println("Byttepenge: " + String.format("%.2f", change) + " kr");
        } else {
            System.out.println(RED + "Betaling afvist. Kunden har ikke betalt nok." + RESET);
        }

        FileHandler.saveCustomer(customer);

        System.out.println("Ordren er færdig kl. " +
                order.getPickupTime().toLocalTime().withSecond(0).withNano(0));
        pressEnterToContinue();
    }


    public void showActiveOrders() {
        var orders = orderManager.getActiveOrdersSorted();

        if (orders.isEmpty()) {
            System.out.println(YELLOW + "Ingen aktive ordrer." + RESET);
            return;
        }

        for (Order order : orders) {
            System.out.println(
                    "Ordre #" + order.getOrderNumber() +
                            " | Kunde: " + order.getCustomer().getName() +
                            " | Type: " + order.getCustomer().getCustomerType() +
                            " | Pizza: " + order.getPizza().getName() +
                            " | Antal: " + order.getQuantity() +
                            " | Status: " + order.getStatus() +
                            " | Klar: " + order.getPickupTime()
            );
        }
        pressEnterToContinue();
    }

    public void markOrderReady() {
        int orderNumber = readInt("Ordrenummer: ");
        boolean success = orderManager.markOrderAsReady(orderNumber);

        if (success) {
            System.out.println(GREEN + "Ordren er markeret som klar." + RESET);
        } else {
            System.out.println(YELLOW + "Ordren blev ikke fundet." + RESET);
        }
        pressEnterToContinue();
    }
    public void markOrderComplete() {
        int orderNumber = readInt("Ordrenummer: ");
        Order completedOrder = orderManager.completeOrder(orderNumber);

        if (completedOrder != null) {
            FileHandler.saveOrder(completedOrder);
            System.out.println("Ordren er markeret som leveret og gemt i historikken.");
        } else {
            System.out.println("Ordren blev ikke fundet.");
        }
        pressEnterToContinue();
    }

    public void showOrderHistory() {
        List<String> orders = FileHandler.loadOrders();

        if (orders.isEmpty()) {
            System.out.println(YELLOW + "Ingen ordrer i historikken endnu." + RESET);
            pressEnterToContinue();
            return;
        }
        System.out.println("\n===OrdreHistorik===");
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
                            " | Pris: " + String.format("%.2f", price) + " kr"
            );
        }
        System.out.println("---------");
        System.out.println("Samlet omsætning: " + String.format("%.2f", totalRevenue) + "kr");
        pressEnterToContinue();
    }
}

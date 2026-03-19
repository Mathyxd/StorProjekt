package UI;

import Service.*;
import main.*;
import Model.*;
import util.*;
import File.*;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;


public class OrderUi {
    private final Scanner scanner;
    private final OrderManager orderManager;
    private final PaymentService paymentService;
    private final Menu menu;

    public OrderUi(Scanner scanner, OrderManager orderManager, PaymentService paymentService, Menu menu) {
        this.scanner = scanner;
        this.orderManager = orderManager;
        this.paymentService = paymentService;
        this.menu = menu;
    }

    private void createOrder() {
        String customerName = readText("Kundenavn: ");
        String email = readEmail();
        int pizzaNumber = readInt("Pizzanummer: ");
        int quantity = readInt("Antal: ");

        Pizza pizza = menu.findPizzaByNumber(pizzaNumber);

        if (pizza == null) {
            System.out.println("Pizza findes ikke.");
            return;
        }

        Customer customer = createCustomer(customerName, email);
        LocalDateTime pickupTime = LocalDateTime.now().plusMinutes(20);
        Size size = readSize();

        Order order = orderManager.createOrder(customer, pizza, size, quantity, pickupTime);
        double total = paymentService.calculateTotal(order);
        System.out.println("Ordre oprettet: #" + order.getOrderNumber());
        System.out.println("Samlet pris: " + total + " kr");

        double amountPaid = readDouble("Betalt beløb: ");

        if (paymentService.processPayment(order, amountPaid)) {
            double change = paymentService.calculateChange(order, amountPaid);
            System.out.println("Betaling godkendt.");
            System.out.println("Byttepenge: " + change + " kr");
        } else {
            System.out.println("Betaling afvist. Kunden har ikke betalt nok.");
        }

        FileHandler.saveCustomer(customer);
        FileHandler.saveOrder(order);

        System.out.println("Ordren er færdig kl. " +
                order.getPickupTime().toLocalTime().withSecond(0).withNano(0));
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
    private void markOrderComplete() {
        int orderNumber = readInt("Ordrenummer: ");
        boolean success = orderManager.markOrderAsComplete(orderNumber);

        if(success) {
            System.out.println("Ordren er market som leveret.");
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
}

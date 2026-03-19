package Service;

import java.util.ArrayList; // Vi bruger ArrayList til at gemme ordrer
import java.util.Comparator; // Bruges til at sortere ordrer
import java.util.List;// Interface for lister
import java.time.LocalDateTime;

import Model.*;

public class OrderManager { // Klasse der styrer alle ordrer


    private final List<Order> activeOrders = new ArrayList<>();
    private final List<Order> completedOrders = new ArrayList<>();
    private int nextOrderNumber = 1;

    // Opret ny ordre
    public Order createOrder(Customer customer, Pizza pizza, Size size, int quantity, LocalDateTime pickupTime) {
        Order order = new Order(nextOrderNumber++, customer, pizza, size, quantity, pickupTime);
        activeOrders.add(order);
        return order;
    }

    // Hent alle aktive ordrer sorteret efter afhentingstid
    public List<Order> getActiveOrdersSorted() {
        List<Order> sorted = new ArrayList<>(activeOrders);
        sorted.sort(Comparator.comparing(Order::getPickupTime));
        return sorted;
    }

    // Hent alle afsluttede ordrer
    public List<Order> getCompletedOrders() {
        return new ArrayList<>(completedOrders);
    }

    // Marker ordre som klar (Mario har lavet pizzaen)
    public boolean markOrderAsReady(int orderNumber) {
        Order order = findActiveOrder(orderNumber);
        if (order == null) {
            return false;
        }
        order.setStatus(OrderStatus.READY);
        return true;
    }

    // Fjern ordre fra aktiv liste når den er afhentet og betalt
    public boolean markOrderAsComplete(int orderNumber) {
        Order order = findActiveOrder(orderNumber);
        if (order == null) {
            return false;
        }
        order.setStatus(OrderStatus.COMPLETED);
        activeOrders.remove(order);
        completedOrders.add(order);
        return true;
    }

    // Beregn samlet omsætning
    public double getTotalRevenue() {
        double total = 0;
        for (Order order : completedOrders) {
            total += order.getTotalPrice();
        }
        return total;

    }

    // Find aktiv ordre ud fra ordrenummer
    private Order findActiveOrder(int orderNumber) {
        for (Order order : activeOrders) {
            if (order.getOrderNumber() == orderNumber) {
                return order;
            }
        }
        return null;
    }
    public Order findOrder(int orderNumber) {
        return findActiveOrder(orderNumber);
    }

}
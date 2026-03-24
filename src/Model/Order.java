package Model;

import java.time.LocalDateTime;

public class   Order {
    private final int orderNumber;
    private final Customer customer;
    private final Pizza pizza;
    private final int quantity;
    private final LocalDateTime pickupTime;
    private final Size size;
    private OrderStatus status;

    public Order(int orderNumber, Customer customer, Pizza pizza, Size size, int quantity, LocalDateTime pickupTime) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer må ikke være null");
        }
        if (pizza == null) {
            throw new IllegalArgumentException("Pizza må ikke være null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity skal være større end 0");
        }
        if (pickupTime == null) {
            throw new IllegalArgumentException("Pickup time må ikke være null");
        }

        this.orderNumber = orderNumber;
        this.customer = customer;
        this.pizza = pizza;
        this.size = size;
        this.quantity = quantity;
        this.pickupTime = pickupTime;
        this.status = OrderStatus.RECEIVED;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getPickupTime() {
        return pickupTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status må ikke være null");
        }
        this.status = status;
    }

    public double getTotalPrice() {
        return customer.calculatePrice(pizza.getPrice() * size.getPriceMultiplier()) * quantity;
    }

    @Override
    public String toString() {
        return "Ordre #" + orderNumber +
                " | " + customer.getName() +
                " | " + pizza.getName() +
                " x" + quantity +
                " | Afhentes: " + pickupTime +
                " | Status: " + status +
                " | Pris: " + String.format("%.2f", getTotalPrice()) + " kr.";
    }
}

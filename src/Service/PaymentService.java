package Service;
import Model.Order;

public class PaymentService {
    public double calculateTotal(Order order) {
        return order.getTotalPrice();
    }

    public boolean processPayment(Order order, double amountPaid) {
        return amountPaid >= order.getTotalPrice();
    }

    public double calculateChange(Order order, double amountPaid) {
        if (!processPayment(order, amountPaid)) {
            return 0;
        }
        return amountPaid - order.getTotalPrice();
    }
}


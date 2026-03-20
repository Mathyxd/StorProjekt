package util;
import Model.Order;
import java.util.Comparator;

public class OrderComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {

        int result = o1.getPickupTime().compareTo(o2.getPickupTime());

        if (result != 0) {
            return result;
        }
        return Integer.compare(o1.getOrderNumber(), o2.getOrderNumber());
    }
}


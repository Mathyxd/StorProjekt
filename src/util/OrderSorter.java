package util;
import Model.Order;
import java.util.ArrayList;
import java.util.Collections;


public class OrderSorter {
    private static final OrderComparator comparator = new OrderComparator();

    public static void sortPizza(ArrayList<Order> order) {

        Collections.sort(order, comparator);
    }
}

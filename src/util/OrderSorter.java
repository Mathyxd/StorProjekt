package util;
import Model.Order;
import java.util.ArrayList;
import java.util.Collections;


public class OrderSorter {
    public static void sortPizza(ArrayList<Order> order) {

        OrderComparator comparator = new OrderComparator();

        Collections.sort(order, comparator);
    }
}

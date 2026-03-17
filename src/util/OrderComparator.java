package util;
import Model.Order;
import java.util.Comparator;

public class OrderComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2){

        int result = o1.getOrderNumber().getPizza()
                .compareTo(o2.getOrderNumber().getPizza());

        if(result != 0) {
            return result;
        }

        return o1.getOrderNumber().getSize()
                .compareTo(o2.getOrderNumber().getSize());

    }
}

package File;

import Model.Order;
import Model.Pizza;
import util.ExceptionHandler;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileHandler {
    private static final String ORDERS_FILE = "Orders.csv";
    private static final String MENU_FILE = "menu.csv";

    public static void saveOrder(Order order) {
        try (PrintWriter writer = new PrintWriter (new FileWriter(FileHandler.ORDERS_FILE, true)) {
             writer.println(
                     order.getCustomer().getName() + "," +
                             order.getCustomer().getCustomerType() + "," +
                             order.getPizza().getName() + "," +
                             order.getPickupTime() + "," +
                             order.getTotalPrice()
                    );
        } catch (IOException e) {
            ExceptionHandler.handle(e);
        }
    }
public static List<Pizza> loadMenu() {
    List<Pizza> pizzas = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(FileHandler.MENU_FILE))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String name = parts[0];
            double price = Double.parseDouble(parts[1]);
            pizzas.add(new Pizza(name, price));
        }
    } catch (IOException e) {
        ExceptionHandler.handle(e);
    }
    return pizzas;
}





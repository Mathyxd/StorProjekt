package File;

import Model.Order;
import Model.Pizza;
import Model.Customer;
import util.ExceptionHandler;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileHandler {
    private static final String ORDERS_FILE = "Orders.csv";
    private static final String MENU_FILE = "menu.csv";
    private static final String CUSTOMER_FILE = "customer.csv";

    public static void saveOrder(Order order) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FileHandler.ORDERS_FILE, true))) {
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

    public static List<String> loadOrders() {
        List<String> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FileHandler.ORDERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                orders.add(line);
            }
        } catch (IOException e) {
            ExceptionHandler.handle(e);
        }
        return orders;
    }

    public static List<Pizza> loadMenu() {
        List<Pizza> pizzas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FileHandler.MENU_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int number = Integer.parseInt(parts[0].trim());
                int prepTimeMinutes = 15;
                String name = parts[1].trim();
                String ingredients = parts[2].trim();
                double price = Double.parseDouble(parts[3].trim());
                pizzas.add(new Pizza(number, prepTimeMinutes, name, ingredients, price));
            }
        } catch (IOException e) {
            ExceptionHandler.handle(e);
        }
        return pizzas;
    }

    public static void saveCustomer(Customer customer) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FileHandler.CUSTOMER_FILE, true))) {
            writer.println(
                    customer.getName() + "," +
                            customer.getEmail() + "," +
                            customer.getCustomerType()
            );
        } catch (IOException e) {
            ExceptionHandler.handle(e);
        }
    }

    public static List<String> loadCustomer() {
        List<String> customer = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FileHandler.CUSTOMER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                customer.add(line);
            }
        } catch (IOException e) {
            ExceptionHandler.handle(e);
        }
        return customer;
    }
}





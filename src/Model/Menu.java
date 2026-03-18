package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Menu {
    private final List<Pizza> pizzas;

    public Menu() {
        pizzas = createDefaultMenu();
    }

    public List<Pizza> getPizzas() {
        return Collections.unmodifiableList(pizzas);
    }

    public Pizza findPizzaByNumber(int number) {
        for (Pizza pizza : pizzas) {
            if (pizza.getNumber() == number) {
                return pizza;
            }
        }
        return null;
    }

    private List<Pizza> createDefaultMenu() {
        List<Pizza> defaultMenu = new ArrayList<>();
        defaultMenu.add(new Pizza(1,11, "Margherita", "Tomat, ost, oregano", 65));
        defaultMenu.add(new Pizza(2,12, "Vesuvio", "Tomat, ost, skinke", 72));
        defaultMenu.add(new Pizza(3,14, "Capricciosa", "Tomat, ost, skinke, champignon", 78));
        defaultMenu.add(new Pizza(4,13, "Pepperoni", "Tomat, ost, pepperoni", 80));
        defaultMenu.add(new Pizza(5,13, "Hawaii", "Tomat, ost, skinke, ananas", 79));
        defaultMenu.add(new Pizza(6,15, "Napoli", "Tomat, ost, ansjoser, oliven", 81));
        return defaultMenu;
    }
}

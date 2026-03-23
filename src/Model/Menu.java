package Model;

import File.FileHandler;
import java.util.Collections;
import java.util.List;

public class Menu {
    private final List<Pizza> pizzas;

    public Menu() {
        pizzas = FileHandler.loadMenu();

        if (pizzas.isEmpty()) {
            throw new RuntimeException("Menu kunne ikke indlæses. Tjek menu.csv!");
        }
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
}
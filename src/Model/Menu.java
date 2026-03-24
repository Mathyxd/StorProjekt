package Model;

import File.FileHandler;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;

public class Menu {
    private Pizza[] pizzas;

    public Menu() {
        List<Pizza> loaded = FileHandler.loadMenu();

        if (loaded.isEmpty()) {
            throw new RuntimeException("Menu kunne ikke indlæses. Tjek menu.csv!");
        }
        pizzas = loaded.toArray(new Pizza[0]);
    }

    public List<Pizza> getPizzas() {
        return Collections.unmodifiableList(Arrays.asList(pizzas));
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
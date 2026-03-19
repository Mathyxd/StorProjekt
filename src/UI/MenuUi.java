package UI;


import main.*;
import Model.*;
import util.*;
import File.*;
import java.util.Scanner;
import UI.*;


public class MenuUi {
    private final Scanner scanner;
    private final Menu menu;

    public MenuUi(Scanner scanner, Menu menu) {
        this.scanner = scanner;
        this.menu = menu;
    }

    private void showMenuCard() {
        System.out.println("=== Menukort ===");
        for (Pizza pizza : menu.getPizzas()) {
            System.out.println(
                    pizza.getNumber() + ". " +
                            pizza.getName() + " - " +
                            pizza.getIngredients() + " - " +
                            pizza.getPrice() + " kr"

            );
        }
        pressEnterToContinue();
    }

    private Size readSize() {
        System.out.println("Vælg størrelse:");
        System.out.println("1. Small");
        System.out.println("2. Medium");
        System.out.println("3. Large");

        int choice = readInt("Vælg størrelse: ");

        switch (choice) {
            case 1:
                return Size.SMALL;
            case 2:
                return Size.MEDIUM;
            case 3:
                return Size.LARGE;
            default:
                System.out.println("Ugyldigt valg. Medium vælges automatisk.");
                return Size.MEDIUM;
        }
    }
}

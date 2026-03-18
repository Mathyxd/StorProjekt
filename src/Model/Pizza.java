package Model;

public class Pizza {
    private final int number;
    private final int prepTimeMinutes;
    private final String name;
    private final String ingredients;
    private final double price;

    public Pizza(int number,int prepTimeMinutes, String name, String ingredients, double price) {
        if (number <= 0) {
            throw new IllegalArgumentException("Pizzanummer skal være større end 0");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Navn må ikke være tomt");
        }
        if (ingredients == null || ingredients.isBlank()) {
            throw new IllegalArgumentException("Ingredienser må ikke være tomme");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Pris må ikke være negativ");
        }

        this.number = number;
        this.prepTimeMinutes = prepTimeMinutes;
        this.name = name.trim();
        this.ingredients = ingredients.trim();
        this.price = price;
    }

    public int getNumber() {
        return number;
    }
    public int getPrepTimeMinutes() {
        return prepTimeMinutes;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return number + ". " + name + " - " + ingredients + " - " + String.format("%.2f", price) + " kr.";
    }
}

package Model;

public abstract class Customer {
    private final String name;
    private final String email;


    protected Customer(String name, String email) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Navn må ikke være tomt");
        }
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Ugyldig email");
        }

        this.name = name.trim();
        this.email = email.trim().toLowerCase();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
    public abstract double getDiscountRate();

    public double calculatePrice(double originalPrice) {
        return originalPrice * (1 - getDiscountRate());
    }

    public abstract String getCustomerType();

    @Override
    public String toString() {
        return getCustomerType() + ": " + name;
    }
}

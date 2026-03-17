package Model;

public abstract class Customer {
    private final String name;

    protected Customer(String name) {
        this.name = name == null || name.isBlank() ? "Ukendt kunde" : name.trim();
    }

    public String getName() {
        return name;
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

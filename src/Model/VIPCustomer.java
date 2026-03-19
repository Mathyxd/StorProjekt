package Model;

public class VIPCustomer extends Customer {
    private static final double DISCOUNT_RATE = 0.10;

    public VIPCustomer(String name, String email) {
        super(name, email);
    }

    @Override
    public double getDiscountRate() {
        return DISCOUNT_RATE;
    }

    @Override
    public String getCustomerType() {
        return "VIP";
    }
}

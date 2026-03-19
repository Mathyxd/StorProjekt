package Model;

public class EmployeeCustomer extends Customer {
    private static final double DISCOUNT_RATE = 0.20;

    public EmployeeCustomer(String name, String email) {
        super(name, email);
    }

    @Override
    public double getDiscountRate() {
        return DISCOUNT_RATE;
    }

    @Override
    public String getCustomerType() {
        return "Employee";
    }
}
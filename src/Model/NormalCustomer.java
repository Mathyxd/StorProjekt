package Model;

public class NormalCustomer extends Customer {

    public NormalCustomer(String name, String email) {
        super(name, email);
    }

    @Override
    public double getDiscountRate() {
        return 0.0;
    }

    @Override
    public String getCustomerType() {
        return "Normal";
    }
}
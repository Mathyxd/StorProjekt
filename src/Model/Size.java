package Model;

public enum Size {
    SMALL(0.85),
    MEDIUM(1.0),
    LARGE(1.2);

    private final double priceMultiplier;

    Size(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }
}

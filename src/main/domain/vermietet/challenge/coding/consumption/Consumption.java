package vermietet.challenge.coding.consumption;

public class Consumption {
    private final double value;

    public Consumption(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}

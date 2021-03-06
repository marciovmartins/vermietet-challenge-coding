package vermietet.challenge.coding.domain;

public abstract class ValueObject {
    private final Object value;

    protected ValueObject(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ValueObject
                && ((ValueObject) obj).value.equals(value);
    }
}

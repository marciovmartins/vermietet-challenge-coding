package vermietet.challenge.coding.domain.village;

import vermietet.challenge.coding.domain.ValueObject;

public class Village {
    private final Id id;
    private final Name name;

    public Village(Id id, Name name) {
        this.id = id;
        this.name = name;
    }

    public Id id() {
        return id;
    }

    public Name name() {
        return name;
    }

    public static class Id extends ValueObject {
        public Id(int value) {
            super(value);
        }
    }

    public static class Name extends ValueObject {
        public Name(String value) {
            super(value);
        }
    }
}

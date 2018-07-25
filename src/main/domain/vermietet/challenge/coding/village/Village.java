package vermietet.challenge.coding.village;

public class Village {
    private final Id id;
    private final Name name;

    public Village(Id id, Name name) {
        this.id = id;
        this.name = name;
    }

    public Id getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public static class Id {
        private final int value;

        public Id(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(this.value);
        }
    }

    static class Name {
        private final String value;

        Name(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}

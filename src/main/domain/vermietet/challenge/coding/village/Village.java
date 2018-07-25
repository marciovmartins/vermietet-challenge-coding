package vermietet.challenge.coding.village;

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

    public static class Name {
        private final String value;

        public Name(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}

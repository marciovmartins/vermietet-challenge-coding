package vermietet.challenge.coding;

public class Village {
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
}

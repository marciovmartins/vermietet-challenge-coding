package vermietet.challenge.coding.consumption;

import vermietet.challenge.coding.ValueObject;

class LastHours extends ValueObject {
    LastHours(String value) {
        super(value);
    }

    int toInteger() {
        String durationSanitized = this.toString().replace("h", "");
        return Integer.parseInt(durationSanitized);
    }
}

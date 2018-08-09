package vermietet.challenge.coding.domain.consumption;

import vermietet.challenge.coding.domain.ValueObject;

public class LastHours extends ValueObject {
    public LastHours(String value) {
        super(value);
    }

    public int toInteger() {
        String durationSanitized = this.toString().replace("h", "");
        return Integer.parseInt(durationSanitized);
    }
}

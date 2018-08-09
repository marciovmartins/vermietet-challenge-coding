package vermietet.challenge.coding.infrastructure;

import vermietet.challenge.coding.domain.Environment;

public class LocalEnvironment implements Environment {
    @Override
    public String get(String key) {
        return System.getenv(key);
    }
}

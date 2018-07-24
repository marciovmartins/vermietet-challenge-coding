package vermietet.challenge.coding;

import org.springframework.stereotype.Component;

@Component
public class LocalEnvironment implements Environment { // TODO: tests need to be implemented.
    @Override
    public String get(String key) {
        return System.getenv(key);
    }
}

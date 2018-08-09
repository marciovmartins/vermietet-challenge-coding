package vermietet.challenge.coding;

public class LocalEnvironment implements Environment {
    @Override
    public String get(String key) {
        return System.getenv(key);
    }
}

package vermietet.challenge.coding.infrastructure;

import vermietet.challenge.coding.domain.Environment;

import java.net.URI;
import java.util.HashMap;

public class HerokuEnvironment implements Environment {
    private static HashMap<String, String> vars = new HashMap<>();

    HerokuEnvironment() {
        try {
            URI dbUri = new URI(System.getenv("DATABASE_URL"));
            vars.put("JDBC_URL", "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require");
            vars.put("JDBC_USERNAME", dbUri.getUserInfo().split(":")[0]);
            vars.put("JDBC_PASSWORD", dbUri.getUserInfo().split(":")[1]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String get(String key) {
        if (vars.containsKey(key)) {
            return vars.get(key);
        }
        return System.getenv(key);
    }
}

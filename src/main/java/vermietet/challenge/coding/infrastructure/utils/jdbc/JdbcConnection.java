package vermietet.challenge.coding.infrastructure.utils.jdbc;

import vermietet.challenge.coding.domain.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    private final Environment environment;

    public JdbcConnection(Environment environment) {
        this.environment = environment;
    }

    public Connection instance() {
        try {
            return DriverManager.getConnection(
                    environment.get("JDBC_URL"),
                    environment.get("JDBC_USERNAME"),
                    environment.get("JDBC_PASSWORD")
            );
        } catch (SQLException e) {
            throw new DatabaseConnectionErrorException(e);
        }
    }

    class DatabaseConnectionErrorException extends RuntimeException {
        DatabaseConnectionErrorException(SQLException e) {
            super(e);
        }
    }
}

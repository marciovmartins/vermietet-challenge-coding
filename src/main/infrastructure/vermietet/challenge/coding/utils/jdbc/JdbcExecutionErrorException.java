package vermietet.challenge.coding.utils.jdbc;

import java.sql.SQLException;

public class JdbcExecutionErrorException extends RuntimeException {
    public JdbcExecutionErrorException(SQLException e) {
        super(e);
    }
}

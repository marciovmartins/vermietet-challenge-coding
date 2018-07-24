package vermietet.challenge.coding.consumption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vermietet.challenge.coding.Environment;
import vermietet.challenge.coding.village.Village;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class JdbcConsumptionRepository implements ConsumptionRepository {
    private final Connection connection;

    @Autowired
    JdbcConsumptionRepository(Environment environment) {
        try {
            connection = DriverManager.getConnection(
                    environment.get("JDBC_URL"),
                    environment.get("JDBC_USERNAME"),
                    environment.get("JDBC_PASSWORD")
            );
        } catch (SQLException e) {
            throw new DatabaseConnectionErrorException(e);
        }
    }

    @Override
    public void insert(Consumption consumption, Village.Id villageId) {
        String sql = "INSERT INTO consumptions (village_id, consumption) VALUES (?, ?)";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(villageId.toString()));
            stmt.setDouble(2, Double.parseDouble(consumption.toString()));

            stmt.execute();
        } catch (SQLException e) {
            throw new DatabaseExecutionErrorException(e);
        }
    }

    class DatabaseConnectionErrorException extends RuntimeException {
        DatabaseConnectionErrorException(SQLException e) {
            super(e);
        }
    }

    class DatabaseExecutionErrorException extends RuntimeException {
        DatabaseExecutionErrorException(SQLException e) {
            super(e);
        }
    }
}

package vermietet.challenge.coding.consumption;

import vermietet.challenge.coding.utils.jdbc.JdbcConnection;
import vermietet.challenge.coding.utils.jdbc.JdbcExecutionErrorException;
import vermietet.challenge.coding.village.Village;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class JdbcConsumptionRepository implements ConsumptionRepository {
    private final Connection connection;

    public JdbcConsumptionRepository(JdbcConnection jdbcConnection) {
        connection = jdbcConnection.instance();
    }

    @Override
    public void insert(Consumption consumption, Village.Id villageId) {
        String sql = "INSERT INTO consumptions (village_id, consumption, datetime) VALUES (?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(villageId.toString()));
            stmt.setDouble(2, Double.parseDouble(consumption.toString()));
            stmt.setTimestamp(3, Timestamp.from(Instant.now()));

            stmt.execute();
        } catch (SQLException e) {
            throw new JdbcExecutionErrorException(e);
        }
    }
}

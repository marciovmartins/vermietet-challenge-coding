package vermietet.challenge.coding.village;

import vermietet.challenge.coding.utils.jdbc.JdbcConnection;
import vermietet.challenge.coding.utils.jdbc.JdbcExecutionErrorException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcVillageRepository implements VillageRepository {
    private final Connection connection;

    public JdbcVillageRepository(JdbcConnection jdbcConnection) {
        connection = jdbcConnection.instance();
    }

    @Override
    public Village findBy(Village.Id villageId) {
        String sql = "SELECT name FROM villages WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(villageId.toString()));

            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            return new Village(
                    villageId,
                    new Village.Name(resultSet.getString(1))
            );
        } catch (SQLException e) {
            throw new JdbcExecutionErrorException(e);
        }
    }

    @Override
    public List<Village> all() {
        String sql = "SELECT id, name FROM villages";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            List<Village> villages = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                villages.add(new Village(
                        new Village.Id(resultSet.getInt(1)),
                        new Village.Name(resultSet.getString(2))
                ));
            }
            return villages;
        } catch (SQLException e) {
            throw new JdbcExecutionErrorException(e);
        }
    }
}

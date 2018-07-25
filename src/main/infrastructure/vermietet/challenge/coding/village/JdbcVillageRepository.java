package vermietet.challenge.coding.village;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vermietet.challenge.coding.Environment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcVillageRepository implements VillageRepository {
    private final Connection connection;

    @Autowired
    JdbcVillageRepository(Environment environment) {
        try {
            connection = DriverManager.getConnection( // TODO: must be extracted to a JdbcConnection.
                    environment.get("JDBC_URL"),
                    environment.get("JDBC_USERNAME"),
                    environment.get("JDBC_PASSWORD")
            );
        } catch (SQLException e) {
            throw new DatabaseConnectionErrorException(e);
        }
    }

    @Override
    public Village findBy(Village.Id villageId) {
        String sql = "SELECT name FROM villages WHERE id = ?";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(villageId.toString()));

            ResultSet resultSet = stmt.executeQuery();
            resultSet.next();
            return new Village(
                    villageId,
                    new Village.Name(resultSet.getString(1))
            );
        } catch (SQLException e) {
            throw new DatabaseExecutionErrorException(e);
        }
    }

    @Override
    public List<Village> all() {
        String sql = "SELECT id, name FROM villages";

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);

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

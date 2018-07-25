package vermietet.challenge.coding.consumption;

import vermietet.challenge.coding.Environment;
import vermietet.challenge.coding.village.Village;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class JdbcReportConsumptionRepository implements ReportConsumptionRepository {
    private final Connection connection;

    JdbcReportConsumptionRepository(Environment environment) {
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
    public List<ReportConsumption> getReportConsumptionBy(List<Village> villages, LastHours lastHours) { // TODO: get village name from List<Village>?
        String sql = String.format(
                "SELECT c.village_id, SUM(c.consumption), v.name " +
                        "FROM consumptions c " +
                        "INNER JOIN villages v ON (v.id = c.village_id)" +
                        "WHERE c.village_id IN (%s) " +
                        "  AND datetime >= ?" +
                        "GROUP BY c.village_id",
                preparePlaceHolders(villages.size())
        );

        Object[] villageIds = this.getVillageIdsFrom(villages);
        Date lastHoursInDate = this.getDateInHoursFrom(lastHours);

        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            setValues(stmt, villageIds);
            stmt.setDate(villages.size() + 1, lastHoursInDate);

            List<ReportConsumption> reports = new ArrayList<>();
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                reports.add(
                        new ReportConsumption(
                                new Consumption(resultSet.getDouble(2)),
                                new Village.Name(resultSet.getString(3))
                        )
                );
            }
            return reports;
        } catch (SQLException e) {
            throw new DatabaseExecutionErrorException(e);
        }
    }

    private Object[] getVillageIdsFrom(List<Village> villages) {
        return villages.stream().map(v -> v.id().toString()).toArray();
    }

    private Date getDateInHoursFrom(LastHours lastHours) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -1 * Integer.parseInt(lastHours.toString()));
        return new Date(cal.getTimeInMillis());
    }

    private String preparePlaceHolders(int length) { // TODO: extract to another place
        return String.join(",", Collections.nCopies(length, "?"));
    }

    private void setValues(PreparedStatement preparedStatement, Object... values) throws SQLException { // TODO: extract to another place
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i + 1, values[i]);
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

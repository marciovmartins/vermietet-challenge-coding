package vermietet.challenge.coding.infrastructure.consumption;

import vermietet.challenge.coding.domain.consumption.Consumption;
import vermietet.challenge.coding.domain.consumption.LastHours;
import vermietet.challenge.coding.domain.consumption.ReportConsumption;
import vermietet.challenge.coding.domain.consumption.ReportConsumptionRepository;
import vermietet.challenge.coding.domain.village.Village;
import vermietet.challenge.coding.infrastructure.utils.jdbc.JdbcConnection;
import vermietet.challenge.coding.infrastructure.utils.jdbc.JdbcExecutionErrorException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class JdbcReportConsumptionRepository implements ReportConsumptionRepository {
    private final Connection connection;

    public JdbcReportConsumptionRepository(JdbcConnection jdbcConnection) {
        connection = jdbcConnection.instance();
    }

    @Override
    public List<ReportConsumption> getReportConsumptionBy(List<Village> villages, LastHours lastHours) { // TODO: get village name from List<Village>?
        String sql = String.format(
                "SELECT c.village_id, SUM(c.consumption), v.name " +
                        "FROM consumptions c " +
                        "INNER JOIN villages v ON (v.id = c.village_id)" + // TODO: should I include villages in query?
                        "WHERE c.village_id IN (%s) " +
                        "  AND datetime >= ?" +
                        "GROUP BY c.village_id",
                preparePlaceHolders(villages.size())
        );

        Object[] villageIds = getVillageIdsFrom(villages);
        Timestamp lastHoursInTimestamp = getDateInHoursFrom(lastHours);

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            setValues(stmt, villageIds);
            stmt.setTimestamp(villages.size() + 1, lastHoursInTimestamp);

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
            throw new JdbcExecutionErrorException(e);
        }
    }

    private Object[] getVillageIdsFrom(List<Village> villages) {
        return villages.stream().map(v -> v.id().toString()).toArray();
    }

    private Timestamp getDateInHoursFrom(LastHours lastHours) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, -1 * lastHours.toInteger());
        return new Timestamp(cal.getTimeInMillis());
    }

    private String preparePlaceHolders(int length) { // TODO: extract to another place
        return String.join(",", Collections.nCopies(length, "?"));
    }

    private void setValues(PreparedStatement preparedStatement, Object... values) throws SQLException { // TODO: extract to another place
        for (int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i + 1, values[i]);
        }
    }
}

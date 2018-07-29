package vermietet.challenge.coding.consumption;

import org.junit.Test;
import vermietet.challenge.coding.utils.JdbcTest;
import vermietet.challenge.coding.village.Village;

import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;

public class JdbcConsumptionRepositoryTest extends JdbcTest {
    @Test
    public void testInsertConsumption() throws Exception {
        // setup
        Consumption consumption = new Consumption(1.2);
        Village.Id villageId = new Village.Id(3);

        // execution
        JdbcConsumptionRepository repository = new JdbcConsumptionRepository(connection);
        repository.insert(consumption, villageId);

        // assertions
        ResultSet resultSet = connection.instance()
                .prepareStatement("SELECT village_id, consumption FROM consumptions")
                .executeQuery();
        resultSet.next();
        assertEquals(villageId.toString(), resultSet.getString(1));
        assertEquals(consumption.toString(), resultSet.getString(2));
    }

    @Override
    protected String[] getTables() {
        return new String[]{"consumptions"};
    }
}

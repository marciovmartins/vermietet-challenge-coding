package vermietet.challenge.coding.infrastructure.consumption;

import org.junit.Test;
import vermietet.challenge.coding.domain.consumption.Consumption;
import vermietet.challenge.coding.domain.village.Village;
import vermietet.challenge.coding.infrastructure.utils.JdbcTest;

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

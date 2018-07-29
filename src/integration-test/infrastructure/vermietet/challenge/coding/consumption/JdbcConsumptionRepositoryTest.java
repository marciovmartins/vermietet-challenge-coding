package vermietet.challenge.coding.consumption;

import org.junit.Test;
import vermietet.challenge.coding.Environment;
import vermietet.challenge.coding.JdbcConnection;
import vermietet.challenge.coding.village.Village;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcConsumptionRepositoryTest { // TODO: this test should be improved to be automated.
    @Test
    public void testInsertConsumption() {
        Environment environment = mock(Environment.class);
        when(environment.get("JDBC_URL")).thenReturn("jdbc:mysql://localhost:13000/vermietet");
        when(environment.get("JDBC_USERNAME")).thenReturn("root");
        when(environment.get("JDBC_PASSWORD")).thenReturn("");
        JdbcConnection jdbcConnection = new JdbcConnection(environment);

        Consumption consumption = new Consumption(1.2);
        Village.Id villageId = new Village.Id(3);

        JdbcConsumptionRepository repository = new JdbcConsumptionRepository(jdbcConnection);
        repository.insert(consumption, villageId);
    }
}
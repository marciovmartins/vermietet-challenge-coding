package vermietet.challenge.coding.consumption;

import org.junit.jupiter.api.Test;
import vermietet.challenge.coding.Environment;
import vermietet.challenge.coding.Village;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JdbcConsumptionRepositoryManualTest {
    @Test
    void testInsertConsumption() {
        Environment environment = mock(Environment.class);
        when(environment.get("JDBC_URL")).thenReturn("jdbc:mysql://localhost:13000/vermietet");
        when(environment.get("JDBC_USERNAME")).thenReturn("root");
        when(environment.get("JDBC_PASSWORD")).thenReturn("");

        Consumption consumption = new Consumption(1.2);
        Village.Id villageId = new Village.Id(3);

        JdbcConsumptionRepository repository = new JdbcConsumptionRepository(environment);
        repository.insert(consumption, villageId);
    }
}
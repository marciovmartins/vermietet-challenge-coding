package vermietet.challenge.coding.consumption;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import vermietet.challenge.coding.Environment;
import vermietet.challenge.coding.JdbcConnection;
import vermietet.challenge.coding.village.Village;

import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcConsumptionRepositoryTest {
    static private JdbcConnection connection;

    @BeforeClass
    static public void tearUp() {
        Environment environment = mock(Environment.class);
        when(environment.get("JDBC_URL")).thenReturn("jdbc:mysql://localhost:13001/vermietet");
        when(environment.get("JDBC_USERNAME")).thenReturn("root");
        when(environment.get("JDBC_PASSWORD")).thenReturn("");
        connection = new JdbcConnection(environment);
    }

    @AfterClass
    static public void tearDown() throws Exception {
        connection.instance().close();
    }

    @Before
    public void setUp() throws Exception {
        connection.instance().prepareStatement("TRUNCATE consumptions").execute();
    }

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
}
package vermietet.challenge.coding.infrastructure.utils;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import vermietet.challenge.coding.domain.Environment;
import vermietet.challenge.coding.infrastructure.utils.jdbc.JdbcConnection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class JdbcTest {
    protected static JdbcConnection connection;

    @BeforeClass
    static public void tearUp() {
        Environment environment = mock(Environment.class);
        when(environment.get("JDBC_URL")).thenReturn("jdbc:postgresql://db:5432/vermietet");
        when(environment.get("JDBC_USERNAME")).thenReturn("postgres");
        when(environment.get("JDBC_PASSWORD")).thenReturn("mysecretpassword");
        connection = new JdbcConnection(environment);
    }

    @AfterClass
    static public void tearDown() throws Exception {
        connection.instance().close();
    }

    @Before
    public void setUp() throws Exception {
        for (String table : getTables()) {
            connection.instance().prepareStatement("TRUNCATE " + table).execute();
        }
    }

    abstract protected String[] getTables();
}

package vermietet.challenge.coding.utils;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import vermietet.challenge.coding.Environment;
import vermietet.challenge.coding.utils.jdbc.JdbcConnection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class JdbcTest {
    protected static JdbcConnection connection;

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
        for (String table : getTables()) {
            connection.instance().prepareStatement("TRUNCATE " + table).execute();
        }
    }

    abstract protected String[] getTables();
}

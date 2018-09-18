package vermietet.challenge.coding.infrastructure.utils;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import vermietet.challenge.coding.domain.Environment;
import vermietet.challenge.coding.infrastructure.utils.jdbc.JdbcConnection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class JdbcTest { // TODO: duplicated from integration-test src. DRY?
    protected static JdbcConnection connection;

    @BeforeClass
    static public void tearUp() {
        String jdbcUrl = System.getenv("JDBC_URL");
        if (jdbcUrl == null) jdbcUrl = "jdbc:postgresql://db:5432/vermietet"; // TODO: extract to external class.

        String jdbcUsername = System.getenv("JDBC_USERNAME");
        if (jdbcUsername == null) jdbcUrl = "postgres"; // TODO: extract to external class.

        String jdbcPassword = System.getenv("JDBC_PASSWORD");
        if (jdbcPassword == null) jdbcPassword = "mysecretpassword"; // TODO: extract to external class.

        Environment environment = mock(Environment.class);
        when(environment.get("JDBC_URL")).thenReturn(jdbcUrl);
        when(environment.get("JDBC_USERNAME")).thenReturn(jdbcUsername);
        when(environment.get("JDBC_PASSWORD")).thenReturn(jdbcPassword);
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

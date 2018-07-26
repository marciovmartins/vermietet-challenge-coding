package vermietet.challenge.coding.village;

import org.junit.jupiter.api.Test;
import vermietet.challenge.coding.Environment;
import vermietet.challenge.coding.JdbcConnection;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JdbcVillageRepositoryManualTest { // TODO: this test should be improved to be automated.
    @Test
    void testGetVillageById() {
        Environment environment = mock(Environment.class);
        when(environment.get("JDBC_URL")).thenReturn("jdbc:mysql://localhost:13000/vermietet");
        when(environment.get("JDBC_USERNAME")).thenReturn("root");
        when(environment.get("JDBC_PASSWORD")).thenReturn("");
        JdbcConnection jdbcConnection = new JdbcConnection(environment);

        Village.Id villageId = new Village.Id(1);

        JdbcVillageRepository repository = new JdbcVillageRepository(jdbcConnection);
        Village village = repository.findBy(villageId);

        System.out.println(village.id());
        System.out.println(village.name());
    }

    @Test
    void testGetAll() {
        Environment environment = mock(Environment.class);
        when(environment.get("JDBC_URL")).thenReturn("jdbc:mysql://localhost:13000/vermietet");
        when(environment.get("JDBC_USERNAME")).thenReturn("root");
        when(environment.get("JDBC_PASSWORD")).thenReturn("");
        JdbcConnection jdbcConnection = new JdbcConnection(environment);

        JdbcVillageRepository repository = new JdbcVillageRepository(jdbcConnection);
        List<Village> villages = repository.all();

        villages.forEach(v -> System.out.println(String.format("VillageId: %s, VillageName: %s", v.id(), v.name())));
    }
}

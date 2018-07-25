package vermietet.challenge.coding.village;

import org.junit.jupiter.api.Test;
import vermietet.challenge.coding.Environment;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JdbcVillageRepositoryManualTest { // TODO: this test should be improved to be automated.
    @Test
    void testGetVillageById() {
        Environment environment = mock(Environment.class);
        when(environment.get("JDBC_URL")).thenReturn("jdbc:mysql://localhost:13000/vermietet");
        when(environment.get("JDBC_USERNAME")).thenReturn("root");
        when(environment.get("JDBC_PASSWORD")).thenReturn("");

        Village.Id villageId = new Village.Id(1);

        JdbcVillageRepository repository = new JdbcVillageRepository(environment);
        Village village = repository.findBy(villageId);

        System.out.println(village.id());
        System.out.println(village.name());
    }
}
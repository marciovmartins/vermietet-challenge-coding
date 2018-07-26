package vermietet.challenge.coding.consumption;

import org.junit.jupiter.api.Test;
import vermietet.challenge.coding.Environment;
import vermietet.challenge.coding.JdbcConnection;
import vermietet.challenge.coding.village.Village;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JdbcReportConsumptionRepositoryManualTest {
    @Test
    void testGetReportConsumptionInLastHour() {
        Environment environment = mock(Environment.class);
        when(environment.get("JDBC_URL")).thenReturn("jdbc:mysql://localhost:13000/vermietet");
        when(environment.get("JDBC_USERNAME")).thenReturn("root");
        when(environment.get("JDBC_PASSWORD")).thenReturn("");
        JdbcConnection jdbcConnection = new JdbcConnection(environment);

        Village villarriba = mock(Village.class);
        when(villarriba.id()).thenReturn(new Village.Id(1));
        when(villarriba.name()).thenReturn(new Village.Name("Villarriba"));
        Village villabajo = mock(Village.class);
        when(villabajo.id()).thenReturn(new Village.Id(2));
        when(villabajo.name()).thenReturn(new Village.Name("Villabajo"));

        List<Village> villages = new ArrayList<>();
        villages.add(villarriba);
        villages.add(villabajo);

        LastHours lastHours = new LastHours(1);

        ReportConsumptionRepository reportConsumptionRepository = new JdbcReportConsumptionRepository(jdbcConnection);
        List<ReportConsumption> reports = reportConsumptionRepository.getReportConsumptionBy(villages, lastHours);
        reports.forEach(r ->
                System.out.println(String.format("Village name: %s; Consumption: %s", r.villageName().toString(), r.consumption().toString()))
        );
    }
}
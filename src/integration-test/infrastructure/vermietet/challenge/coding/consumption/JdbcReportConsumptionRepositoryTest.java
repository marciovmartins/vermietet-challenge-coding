package vermietet.challenge.coding.consumption;

import org.junit.Test;
import vermietet.challenge.coding.utils.JdbcTest;
import vermietet.challenge.coding.village.Village;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcReportConsumptionRepositoryTest extends JdbcTest {
    @Test
    public void testGetReportConsumptionInLastHour() throws Exception {
        // setup
        PreparedStatement stmt1 = connection.instance().prepareStatement("INSERT INTO villages (id, name) VALUES (?, ?)");
        stmt1.setInt(1, 1);
        stmt1.setString(2, "Villarriba");
        stmt1.execute();

        PreparedStatement stmt2 = connection.instance().prepareStatement("INSERT INTO villages (id, name) VALUES (?, ?)");
        stmt2.setInt(1, 2);
        stmt2.setString(2, "Villabajo");
        stmt2.execute();

        PreparedStatement stmt3 = connection.instance().prepareStatement("INSERT INTO consumptions (village_id, consumption) VALUES (?, ?)");
        stmt3.setInt(1, 1);
        stmt3.setDouble(2, 1.1);
        stmt3.execute();

        PreparedStatement stmt4 = connection.instance().prepareStatement("INSERT INTO consumptions (village_id, consumption) VALUES (?, ?)");
        stmt4.setInt(1, 1);
        stmt4.setDouble(2, 2.2);
        stmt4.execute();

        PreparedStatement stmt5 = connection.instance().prepareStatement("INSERT INTO consumptions (village_id, consumption) VALUES (?, ?)");
        stmt5.setInt(1, 2);
        stmt5.setDouble(2, 3.3);
        stmt5.execute();

        Village villarriba = mock(Village.class);
        when(villarriba.id()).thenReturn(new Village.Id(1));
        when(villarriba.name()).thenReturn(new Village.Name("Villarriba"));
        Village villabajo = mock(Village.class);
        when(villabajo.id()).thenReturn(new Village.Id(2));
        when(villabajo.name()).thenReturn(new Village.Name("Villabajo"));

        List<Village> villages = new ArrayList<>();
        villages.add(villarriba);
        villages.add(villabajo);

        LastHours lastHours = new LastHours("1h");

        // executions
        ReportConsumptionRepository reportConsumptionRepository = new JdbcReportConsumptionRepository(connection);
        List<ReportConsumption> reports = reportConsumptionRepository.getReportConsumptionBy(villages, lastHours);

        // assertions
        assertEquals(2, reports.size());

        Map<Village.Name, Consumption> reportsByVillageName = reports.stream()
                .collect(Collectors.toMap(ReportConsumption::villageName, ReportConsumption::consumption));

        assertTrue(reportsByVillageName.containsKey(villarriba.name()));
        assertEquals(3.3, Double.parseDouble(reportsByVillageName.get(villarriba.name()).toString()), 0.1);

        assertTrue(reportsByVillageName.containsKey(villabajo.name()));
        assertEquals(3.3, Double.parseDouble(reportsByVillageName.get(villabajo.name()).toString()), 0.1);
    }

    @Override
    protected String[] getTables() {
        return new String[]{"villages", "consumptions"};
    }
}

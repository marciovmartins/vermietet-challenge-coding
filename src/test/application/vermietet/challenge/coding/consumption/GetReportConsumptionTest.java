package vermietet.challenge.coding.consumption;

import org.junit.Test;
import vermietet.challenge.coding.village.Village;
import vermietet.challenge.coding.village.VillageRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetReportConsumptionTest {
    @Test
    public void testGetReportConsumptionLastHour() {
        // setup
        Village villarriba = mock(Village.class);
        Village villabajo = mock(Village.class);

        List<Village> villages = new ArrayList<>();
        villages.add(villarriba);
        villages.add(villabajo);

        VillageRepository villageRepository = mock(VillageRepository.class);
        when(villageRepository.all()).thenReturn(villages);

        LastHours lastHours = new LastHours(1);
        ReportConsumption villarribaReport = mock(ReportConsumption.class);
        ReportConsumption villabajoReport = mock(ReportConsumption.class);

        ReportConsumptionRepository reportConsumptionRepository = mock(ReportConsumptionRepository.class);
        when(reportConsumptionRepository.getReportConsumptionBy(villages, lastHours))
                .thenReturn(Arrays.asList(villarribaReport, villabajoReport));

        // execution
        GetReportConsumption getReportConsumption = new GetReportConsumption(
                reportConsumptionRepository,
                villageRepository
        );
        List<ReportConsumption> reports = getReportConsumption.in(1);

        // assertions
        assertEquals(2, reports.size());
        assertTrue(reports.contains(villarribaReport));
        assertTrue(reports.contains(villabajoReport));
    }
}

package vermietet.challenge.coding.application.consumption;

import org.junit.Test;
import vermietet.challenge.coding.domain.consumption.LastHours;
import vermietet.challenge.coding.domain.consumption.ReportConsumption;
import vermietet.challenge.coding.domain.consumption.ReportConsumptionRepository;
import vermietet.challenge.coding.domain.village.Village;
import vermietet.challenge.coding.domain.village.VillageRepository;

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

        LastHours lastHours = new LastHours("1h");
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
        List<ReportConsumption> reports = getReportConsumption.in(lastHours.toString());

        // assertions
        assertEquals(2, reports.size());
        assertTrue(reports.contains(villarribaReport));
        assertTrue(reports.contains(villabajoReport));
    }
}

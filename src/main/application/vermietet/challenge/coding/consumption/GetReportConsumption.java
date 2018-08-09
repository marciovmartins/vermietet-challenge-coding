package vermietet.challenge.coding.consumption;

import vermietet.challenge.coding.village.Village;
import vermietet.challenge.coding.village.VillageRepository;

import java.util.List;

public class GetReportConsumption {
    private final ReportConsumptionRepository reportConsumptionRepository;
    private final VillageRepository villageRepository;

    public GetReportConsumption(
            ReportConsumptionRepository reportConsumptionRepository,
            VillageRepository villageRepository
    ) {
        this.reportConsumptionRepository = reportConsumptionRepository;
        this.villageRepository = villageRepository;
    }

    List<ReportConsumption> in(String duration) {
        LastHours lastHours = new LastHours(duration);
        List<Village> villages = villageRepository.all();
        return reportConsumptionRepository.getReportConsumptionBy(villages, lastHours);
    }
}

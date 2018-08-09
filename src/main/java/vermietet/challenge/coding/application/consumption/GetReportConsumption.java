package vermietet.challenge.coding.application.consumption;

import vermietet.challenge.coding.domain.consumption.LastHours;
import vermietet.challenge.coding.domain.consumption.ReportConsumption;
import vermietet.challenge.coding.domain.consumption.ReportConsumptionRepository;
import vermietet.challenge.coding.domain.village.Village;
import vermietet.challenge.coding.domain.village.VillageRepository;

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

    public List<ReportConsumption> in(String duration) {
        LastHours lastHours = new LastHours(duration);
        List<Village> villages = villageRepository.all();
        return reportConsumptionRepository.getReportConsumptionBy(villages, lastHours);
    }
}

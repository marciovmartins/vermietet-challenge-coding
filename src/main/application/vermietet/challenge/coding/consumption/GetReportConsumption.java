package vermietet.challenge.coding.consumption;

import vermietet.challenge.coding.village.Village;
import vermietet.challenge.coding.village.VillageRepository;

import java.util.List;

class GetReportConsumption {
    private final ReportConsumptionRepository reportConsumptionRepository;
    private final VillageRepository villageRepository;

    GetReportConsumption(
            ReportConsumptionRepository reportConsumptionRepository,
            VillageRepository villageRepository
    ) {
        this.reportConsumptionRepository = reportConsumptionRepository;
        this.villageRepository = villageRepository;
    }

    List<ReportConsumption> in(LastHours lastHours) {
        List<Village> villages = villageRepository.all();
        return reportConsumptionRepository.getReportConsumptionBy(villages, lastHours);
    }
}

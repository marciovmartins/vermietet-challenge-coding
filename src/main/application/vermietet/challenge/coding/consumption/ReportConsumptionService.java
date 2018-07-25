package vermietet.challenge.coding.consumption;

import vermietet.challenge.coding.village.Village;
import vermietet.challenge.coding.village.VillageRepository;

import java.util.List;

class ReportConsumptionService {
    private final ReportConsumptionRepository reportConsumptionRepository;
    private final VillageRepository villageRepository;

    ReportConsumptionService(
            ReportConsumptionRepository reportConsumptionRepository,
            VillageRepository villageRepository
    ) {
        this.reportConsumptionRepository = reportConsumptionRepository;
        this.villageRepository = villageRepository;
    }

    List<ReportConsumption> getReportConsumption(LastHours lastHours) { // TODO: change to Command GetReportConsumptionLastHours
        List<Village> villages = this.villageRepository.all();
        return this.reportConsumptionRepository.getReportConsumptionBy(villages, lastHours);
    }
}

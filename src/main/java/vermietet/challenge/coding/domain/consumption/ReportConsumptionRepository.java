package vermietet.challenge.coding.domain.consumption;

import vermietet.challenge.coding.domain.village.Village;

import java.util.List;

public interface ReportConsumptionRepository {
    List<ReportConsumption> getReportConsumptionBy(List<Village> villages, LastHours lastHours);
}

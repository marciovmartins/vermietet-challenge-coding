package vermietet.challenge.coding.consumption;

import vermietet.challenge.coding.village.Village;

import java.util.List;

public interface ReportConsumptionRepository {
    List<ReportConsumption> getReportConsumptionBy(List<Village> villages, LastHours lastHours);
}

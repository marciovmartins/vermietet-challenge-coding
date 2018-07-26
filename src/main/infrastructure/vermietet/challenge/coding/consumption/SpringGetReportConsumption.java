package vermietet.challenge.coding.consumption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vermietet.challenge.coding.village.VillageRepository;

@Service
public class SpringGetReportConsumption extends GetReportConsumption {
    @Autowired
    SpringGetReportConsumption(
            ReportConsumptionRepository reportConsumptionRepository,
            VillageRepository villageRepository
    ) {
        super(reportConsumptionRepository, villageRepository);
    }
}

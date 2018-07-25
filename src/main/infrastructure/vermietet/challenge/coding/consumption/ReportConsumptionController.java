package vermietet.challenge.coding.consumption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReportConsumptionController {
    private final ReportConsumptionService reportConsumptionService;

    @Autowired
    public ReportConsumptionController(ReportConsumptionService reportConsumptionService) {
        this.reportConsumptionService = reportConsumptionService;
    }

    @GetMapping("/consumption_report")
    public List<ReportConsumptionDTO> getConsumptionReport(
            @RequestParam(name = "duration", defaultValue = "24h", required = false) String pDuration
    ) { // TODO: tests need to be implemented.
        LastHours lastHours = getLastHours(pDuration);
        List<ReportConsumption> reports = this.reportConsumptionService.getReportConsumption(lastHours);

        return reports.stream().map(r -> new ReportConsumptionDTO(
                r.villageName(),
                r.consumption()
        )).collect(Collectors.toList());
    }

    private LastHours getLastHours(@RequestParam(name = "duration", defaultValue = "24h", required = false) String pDuration) {
        String durationSanitized = pDuration.replace("h", "");
        return new LastHours(Integer.parseInt(durationSanitized));
    }
}

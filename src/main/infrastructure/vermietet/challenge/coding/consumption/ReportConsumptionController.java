package vermietet.challenge.coding.consumption;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vermietet.challenge.coding.village.Village;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReportConsumptionController {
    private final GetReportConsumption getReportConsumption;

    @Autowired
    public ReportConsumptionController(GetReportConsumption getReportConsumption) {
        this.getReportConsumption = getReportConsumption;
    }

    @GetMapping("/consumption_report")
    public List<ReportConsumptionDTO> getConsumptionReport(
            @RequestParam(name = "duration", defaultValue = "24h", required = false) String pDuration
    ) { // TODO: tests need to be implemented.
        LastHours lastHours = getLastHours(pDuration);
        List<ReportConsumption> reports = this.getReportConsumption.in(lastHours);

        return reports.stream().map(r -> new ReportConsumptionDTO(
                r.villageName(),
                r.consumption()
        )).collect(Collectors.toList());
    }

    private LastHours getLastHours(
            @RequestParam(name = "duration", defaultValue = "24h", required = false) String pDuration
    ) {
        String durationSanitized = pDuration.replace("h", "");
        return new LastHours(Integer.parseInt(durationSanitized));
    }

    public static class ReportConsumptionDTO {
        @JsonProperty("village_name")
        private String villageName;
        @JsonProperty("consumption")
        private String consumption;

        ReportConsumptionDTO(Village.Name villageName, Consumption consumption) {
            this.villageName = villageName.toString();
            this.consumption = consumption.toString();
        }

        public String getVillageName() {
            return villageName;
        }

        public String getConsumption() {
            return consumption;
        }
    }
}

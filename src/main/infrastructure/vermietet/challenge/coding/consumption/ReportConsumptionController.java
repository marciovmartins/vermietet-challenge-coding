package vermietet.challenge.coding.consumption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@SuppressWarnings("unused")
public class ReportConsumptionController { // TODO: tests need to be implemented.
    private final GetReportConsumption getReportConsumption;

    @Autowired
    public ReportConsumptionController(GetReportConsumption getReportConsumption) {
        this.getReportConsumption = getReportConsumption;
    }

    @GetMapping("/consumption_report")
    public List<HashMap<String, String>> getConsumptionReport(
            @RequestParam(name = "duration", defaultValue = "24h", required = false) String duration
    ) {
        int sanitizedLastHours = getLastHours(duration);
        List<ReportConsumption> reports = getReportConsumption.in(sanitizedLastHours);

        return reports.stream().map(r -> new HashMap<String, String>() {{
            put("village_name", r.villageName().toString());
            put("consumption", r.consumption().toString());
        }}).collect(Collectors.toList());
    }

    private int getLastHours(String duration) {
        String durationSanitized = duration.replace("h", "");
        return Integer.parseInt(durationSanitized);
    }
}

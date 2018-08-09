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
public class SpringReportConsumptionController { // TODO: tests need to be implemented.
    private final GetReportConsumption getReportConsumption;

    @Autowired
    public SpringReportConsumptionController(GetReportConsumption getReportConsumption) {
        this.getReportConsumption = getReportConsumption;
    }

    @GetMapping("/consumption_report")
    public List<HashMap<String, Object>> getConsumptionReport(
            @RequestParam(name = "duration", defaultValue = "24h", required = false) String duration
    ) {
        return getReportConsumption.in(duration).stream()
                .map(r -> new HashMap<String, Object>() {{
                    put("village_name", r.villageName().toString());
                    put("consumption", Double.parseDouble(r.consumption().toString()));
                }})
                .collect(Collectors.toList());
    }
}

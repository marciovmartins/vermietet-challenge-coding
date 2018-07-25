package vermietet.challenge.coding.consumption;

import com.fasterxml.jackson.annotation.JsonProperty;
import vermietet.challenge.coding.village.Village;

public class ReportConsumptionDTO {
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
